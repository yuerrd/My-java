package com.yuerrd.jgroups;

import com.yuerrd.jgroups.impl.ConsistentHashFunction;
import org.junit.Test;

/**
 * @author yuerrd
 */
public class ConsistentHashFunctionTest {

    @Test
    public void test1() {
        HashFunctionFactory<String> hash_function_factory = ConsistentHashFunction::new;
    }
}
