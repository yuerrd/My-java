package com.yuerrd.infinispan;

import com.yuerrd.infinispan.impl.DefaultConsistentHash;
import com.yuerrd.infinispan.impl.DefaultConsistentHashFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuerrd
 */
public class TestConsistentHash {

    public static void main(String[] args) {
        ConsistentHashFactory<DefaultConsistentHash> chf = new DefaultConsistentHashFactory();
        TestAddress A = new TestAddress(0, "A");
        TestAddress B = new TestAddress(1, "B");
        TestAddress C = new TestAddress(2, "C");
        TestAddress D = new TestAddress(3, "D");
        Map<Address, Float> cf = new HashMap<>();
        cf.put(A, 1f);
        cf.put(B, 1f);
        cf.put(C, 1f);
        cf.put(D, 100f);
        DefaultConsistentHash ch1 = chf.create(2, 60, Arrays.asList(A), cf);





        System.out.println("------");
        DefaultConsistentHash ch2 = chf.updateMembers(ch1, Arrays.asList(A, B), cf);
        ch2 = chf.rebalance(ch2);
        System.out.println("------");
//        DefaultConsistentHash ch3 = chf.updateMembers(ch2, Arrays.asList(A, B, C), cf);
//        ch3 = chf.rebalance(ch3);
//        System.out.println("------");
//        DefaultConsistentHash ch4 = chf.updateMembers(ch3, Arrays.asList(A, B, C, D), cf);
//        ch4 = chf.rebalance(ch4);
//        System.out.println("------");
    }
}
