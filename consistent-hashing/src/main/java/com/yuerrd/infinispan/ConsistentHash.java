package com.yuerrd.infinispan;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * @author yuerrd
 */
public interface ConsistentHash {

    int getNumSegments();

    List<Address> getMembers();

    List<Address> locateOwnersForSegment(int segmentId);

    Address locatePrimaryOwnerForSegment(int segmentId);

    default boolean isSegmentLocalToNode(Address nodeAddress, int segmentId) {
        return locateOwnersForSegment(segmentId).contains(nodeAddress);
    }

    default boolean isReplicated() {
        // Returning true is only an optimization, so it's ok to return false by default.
        return false;
    }
    Set<Integer> getSegmentsForOwner(Address owner);

    Set<Integer> getPrimarySegmentsForOwner(Address owner);

    String getRoutingTableAsString();

    default ConsistentHash remapAddresses(UnaryOperator<Address> remapper) {
        throw new UnsupportedOperationException();
    }

    default Map<Address, Float> getCapacityFactors() {
        return null;
    }

}
