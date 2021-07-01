package com.yuerrd.infinispan.impl;

import com.yuerrd.infinispan.Address;
import com.yuerrd.infinispan.ConsistentHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * @author yuerrd
 */
public abstract class AbstractConsistentHash implements ConsistentHash {
    protected final List<Address> members;
    protected final float[] capacityFactors;

    protected AbstractConsistentHash(int numSegments, List<Address> members, Map<Address, Float> capacityFactors) {
        if (numSegments < 1) {
            throw new IllegalArgumentException("The number of segments must be strictly positive");
        }

        this.members = new ArrayList<>(members);
        if (capacityFactors == null) {
            this.capacityFactors = null;
        } else {
            this.capacityFactors = new float[members.size()];
            for (int i = 0; i < this.capacityFactors.length; i++) {
                this.capacityFactors[i] = capacityFactors.get(members.get(i));
            }
        }
    }

    protected AbstractConsistentHash(int numSegments, List<Address> members, float[] capacityFactors) {
        if (numSegments < 1) {
            throw new IllegalArgumentException("The number of segments must be strictly positive");
        }

        this.members = members;
        this.capacityFactors = capacityFactors;
    }

    @Override
    public List<Address> getMembers() {
        return members;
    }

    protected static void mergeLists(List<Address> dest, List<Address> src) {
        for (Address node : src) {
            if (!dest.contains(node)) {
                dest.add(node);
            }
        }
    }

    static HashMap<Address, Integer> getMemberIndexMap(List<Address> members) {
        HashMap<Address, Integer> memberIndexes = new HashMap<>(members.size());
        for (int i = 0; i < members.size(); i++) {
            memberIndexes.put(members.get(i), i);
        }
        return memberIndexes;
    }

    @Override
    public Map<Address, Float> getCapacityFactors() {
        if (capacityFactors == null) {
            return null;
        }

        Map<Address, Float> capacityFactorsMap = new HashMap<>(members.size());
        for (int i = 0; i < members.size(); i++) {
            capacityFactorsMap.put(members.get(i), capacityFactors[i]);
        }
        return capacityFactorsMap;
    }

    protected Map<Address, Float> unionCapacityFactors(AbstractConsistentHash ch2) {
        Map<Address, Float> unionCapacityFactors = null;
        if (this.capacityFactors != null || ch2.capacityFactors != null) {
            unionCapacityFactors = new HashMap<>();
            if (this.capacityFactors != null) {
                unionCapacityFactors.putAll(this.getCapacityFactors());
            } else {
                for (Address node : this.members) {
                    unionCapacityFactors.put(node, 1.0f);
                }
            }
            if (ch2.capacityFactors != null) {
                unionCapacityFactors.putAll(ch2.getCapacityFactors());
            } else {
                for (Address node : ch2.members) {
                    unionCapacityFactors.put(node, 1.0f);
                }
            }
        }
        return unionCapacityFactors;
    }

    protected void checkSameHashAndSegments(AbstractConsistentHash dch2) {
        int numSegments = getNumSegments();
        if (numSegments != dch2.getNumSegments()) {
            throw new IllegalArgumentException("The consistent hash objects must have the same number of segments");
        }
    }

    protected Map<Address, Float> remapCapacityFactors(UnaryOperator<Address> remapper) {
        Map<Address, Float> remappedCapacityFactors = null;
        if (capacityFactors != null) {
            remappedCapacityFactors = new HashMap<>(members.size());
            for (int i = 0; i < members.size(); i++) {
                remappedCapacityFactors.put(remapper.apply(members.get(i)), capacityFactors[i]);
            }
        }
        return remappedCapacityFactors;
    }

    protected List<Address> remapMembers(UnaryOperator<Address> remapper) {
        List<Address> remappedMembers = new ArrayList<>(members.size());
        for (Iterator<Address> i = members.iterator(); i.hasNext(); ) {
            Address a = remapper.apply(i.next());
            if (a == null) {
                return null;
            }
            remappedMembers.add(a);
        }
        return remappedMembers;
    }
}
