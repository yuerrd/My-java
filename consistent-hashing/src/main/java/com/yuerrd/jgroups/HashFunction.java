package com.yuerrd.jgroups;

import java.util.List;

/**
 * @author yuerrd
 */
public interface HashFunction<K> {

    List<Address> hash(K key, short replicationCount);

    void installNodes(List<Address> nodes);

}
