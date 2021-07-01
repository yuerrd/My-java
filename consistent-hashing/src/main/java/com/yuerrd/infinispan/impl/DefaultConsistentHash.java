package com.yuerrd.infinispan.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.yuerrd.infinispan.Address;
import com.yuerrd.infinispan.ConsistentHash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * @author yuerrd
 */
public class DefaultConsistentHash extends AbstractConsistentHash {


    private final int numOwners;

    private final List<Address>[] segmentOwners;


    public DefaultConsistentHash(int numOwners, int numSegments, List<Address> members,
                                 Map<Address, Float> capacityFactors, List<Address>[] segmentOwners) {
        super(numSegments, members, capacityFactors);
        if (numOwners < 1) {
            throw new IllegalArgumentException("The number of owners must be strictly positive");
        }
        this.numOwners = numOwners;
        this.segmentOwners = new List[numSegments];
        for (int s = 0; s < numSegments; s++) {
            if (segmentOwners[s] == null || segmentOwners[s].isEmpty()) {
                throw new IllegalArgumentException("Segment owner list cannot be null or empty");
            }
            this.segmentOwners[s] = ImmutableList.copyOf(segmentOwners[s]);
        }
    }


    @Override
    public int getNumSegments() {
        return segmentOwners.length;
    }

    @Override
    public Set<Integer> getSegmentsForOwner(Address owner) {
        if (owner == null) {
            throw new IllegalArgumentException("owner cannot be null");
        }
        if (!members.contains(owner)) {
            throw new IllegalArgumentException("Node " + owner + " is not a member");
        }

        Set<Integer> segments = new HashSet<>();
        for (int segment = 0; segment < segmentOwners.length; segment++) {
            if (segmentOwners[segment].contains(owner)) {
                segments.add(segment);
            }
        }
        return ImmutableSet.copyOf(segments);
    }

    @Override
    public Set<Integer> getPrimarySegmentsForOwner(Address owner) {
        if (owner == null) {
            throw new IllegalArgumentException("owner cannot be null");
        }
        if (!members.contains(owner)) {
            throw new IllegalArgumentException("Node " + owner + " is not a member");
        }

        Set<Integer> segments = new HashSet<>();
        for (int segment = 0; segment < segmentOwners.length; segment++) {
            if (owner.equals(segmentOwners[segment].get(0))) {
                segments.add(segment);
            }
        }
        return ImmutableSet.copyOf(segments);
    }

    @Override
    public List<Address> locateOwnersForSegment(int segmentId) {
        return segmentOwners[segmentId];
    }


    @Override
    public Address locatePrimaryOwnerForSegment(int segmentId) {
        return segmentOwners[segmentId].get(0);
    }

    public int getNumOwners() {
        return numOwners;
    }


    @Override
    public boolean isSegmentLocalToNode(Address nodeAddress, int segmentId) {
        return segmentOwners[segmentId].contains(nodeAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultConsistentHash that = (DefaultConsistentHash) o;

        if (numOwners != that.numOwners) return false;
        if (segmentOwners.length != that.segmentOwners.length) return false;
        if (!members.equals(that.members)) return false;
        for (int i = 0; i < segmentOwners.length; i++) {
            if (!segmentOwners[i].equals(that.segmentOwners[i]))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DefaultConsistentHash{");
        sb.append("ns=").append(segmentOwners.length);
        sb.append(", owners = (").append(members.size()).append(")[");
        boolean first = true;
        for (Address a : members) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(a);
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public String getRoutingTableAsString() {
        StringBuilder sb = new StringBuilder();
        for (Address a : members) {
            if (sb.length() > 0) {
                sb.append("\n  ");
            }
            Set<Integer> primarySegments = getPrimarySegmentsForOwner(a);
            sb.append(a).append(" primary: ").append(primarySegments);
            Set<Integer> backupSegments = getSegmentsForOwner(a);
            backupSegments.removeAll(primarySegments);
            sb.append(", backup: ").append(backupSegments);
        }
        return sb.toString();
    }

    public DefaultConsistentHash union(DefaultConsistentHash dch2) {
        checkSameHashAndSegments(dch2);
        if (numOwners != dch2.getNumOwners()) {
            throw new IllegalArgumentException("The consistent hash objects must have the same number of owners");
        }

        List<Address> unionMembers = new ArrayList<>(this.members);
        mergeLists(unionMembers, dch2.getMembers());

        List<Address>[] unionSegmentOwners = new List[segmentOwners.length];
        for (int i = 0; i < segmentOwners.length; i++) {
            unionSegmentOwners[i] = new ArrayList<>(locateOwnersForSegment(i));
            mergeLists(unionSegmentOwners[i], dch2.locateOwnersForSegment(i));
        }

        Map<Address, Float> unionCapacityFactors = unionCapacityFactors(dch2);
        return new DefaultConsistentHash(numOwners, unionSegmentOwners.length, unionMembers, unionCapacityFactors, unionSegmentOwners);
    }

    @Override
    public ConsistentHash remapAddresses(UnaryOperator<Address> remapper) {
        List<Address> remappedMembers = remapMembers(remapper);
        if (remappedMembers == null) {
            return null;
        }
        Map<Address, Float> remappedCapacityFactors = remapCapacityFactors(remapper);
        List<Address>[] remappedSegmentOwners = new List[segmentOwners.length];
        for(int i=0; i < segmentOwners.length; i++) {
            List<Address> remappedOwners = new ArrayList<>(segmentOwners[i].size());
            for (Address address : segmentOwners[i]) {
                remappedOwners.add(remapper.apply(address));
            }
            remappedSegmentOwners[i] = remappedOwners;
        }

        return new DefaultConsistentHash(this.numOwners, this.segmentOwners.length, remappedMembers,
                remappedCapacityFactors, remappedSegmentOwners);
    }
}
