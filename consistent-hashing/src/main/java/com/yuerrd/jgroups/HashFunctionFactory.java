package com.yuerrd.jgroups;

/**
 * @author yuerrd
 */
public interface HashFunctionFactory<K> {
    HashFunction<K> create();
}
