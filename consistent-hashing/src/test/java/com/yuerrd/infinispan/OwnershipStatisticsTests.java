package com.yuerrd.infinispan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuerrd
 */
public class OwnershipStatisticsTests {

    List<Address> addresses;
    TestAddress A = new TestAddress(0, "A");
    TestAddress B = new TestAddress(1, "B");
    TestAddress C = new TestAddress(2, "C");
    TestAddress D = new TestAddress(3, "D");
    @Before
    public void inti() {

        addresses = Arrays.asList(A, B, C, D);
    }

    @Test
    public void decPrimaryOwnedTest() {
        OwnershipStatistics ownershipStatistics = new OwnershipStatistics(addresses);
        ownershipStatistics.incOwned(1);

        ownershipStatistics.incOwned(B);

        Assert.assertEquals(2, ownershipStatistics.sumOwned());
    }
}
