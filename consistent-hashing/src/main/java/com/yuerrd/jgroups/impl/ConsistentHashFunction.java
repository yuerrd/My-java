package com.yuerrd.jgroups.impl;

import com.yuerrd.jgroups.Address;
import com.yuerrd.jgroups.HashFunction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yuerrd
 */
public class ConsistentHashFunction<K> implements HashFunction<K> {
    private final SortedMap<Short, Address> nodes = new TreeMap<>();

    /**
     * must be > max number of nodes in a cluster and a power of 2
     */
    private final static int HASH_SPACE = 2048;
    /**
     * to better spread the node out across the space
     */
    private final static int FACTOR = 3737;

    @Override
    public List<Address> hash(K key, short replicationCount) {
        int index = Math.abs(key.hashCode() & (HASH_SPACE - 1));

        Set<Address> results = new LinkedHashSet<>();

        SortedMap<Short, Address> tailmap = nodes.tailMap((short) index);
        int count = 0;

        for (Map.Entry<Short, Address> entry : tailmap.entrySet()) {
            Address val = entry.getValue();
            results.add(val);
            if (++count >= replicationCount)
                break;
        }

        if (count < replicationCount) {
            for (Map.Entry<Short, Address> entry : nodes.entrySet()) {
                Address val = entry.getValue();
                results.add(val);
                if (++count >= replicationCount)
                    break;
            }
        }
        return new ArrayList<>(results);
    }

    @Override
    public void installNodes(List<Address> newNodes) {
        nodes.clear();
        for (Address node : newNodes) {
            int hash = Math.abs((node.hashCode() * FACTOR) & (HASH_SPACE - 1));
            for (int i = hash; i < hash + HASH_SPACE; i++) {
                short new_index = (short) (i & (HASH_SPACE - 1));
                if (!nodes.containsKey(new_index)) {
                    nodes.put(new_index, node);
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder("node mappings:\n");
        for (Map.Entry<Short, Address> entry : nodes.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue()).append("\n");
        }
        System.out.println(sb);
    }
}
