package com.yuerrd.jgroups;

/**
 * @author yuerrd
 */
public interface Address extends Comparable<Address> {
    // flags used for marshalling
    byte NULL = 1 << 0;
    byte UUID_ADDR = 1 << 1;
    byte SITE_UUID = 1 << 2;
    byte SITE_MASTER = 1 << 3;
    byte IP_ADDR = 1 << 4;
}
