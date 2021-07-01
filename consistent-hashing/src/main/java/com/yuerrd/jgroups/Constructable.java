package com.yuerrd.jgroups;

import java.util.function.Supplier;

/**
 * @author yuerrd
 */
public interface Constructable<T> {
    Supplier<? extends T> create();
}
