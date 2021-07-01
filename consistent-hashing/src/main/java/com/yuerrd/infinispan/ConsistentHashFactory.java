package com.yuerrd.infinispan;

import net.openhft.hashing.LongHashFunction;

import java.util.List;
import java.util.Map;

/**
 * @author yuerrd
 */
public interface ConsistentHashFactory<CH extends ConsistentHash> {



    default CH create(LongHashFunction hashFunction, int numOwners, int numSegments, List<Address> members,
                      Map<Address, Float> capacityFactors) {
        return create(numOwners, numSegments, members, capacityFactors);
    }

    CH create(int numOwners, int numSegments, List<Address> members, Map<Address, Float> capacityFactors);

    CH updateMembers(CH baseCH, List<Address> newMembers, Map<Address, Float> capacityFactors);

    CH rebalance(CH baseCH);

    CH union(CH ch1, CH ch2);
}
