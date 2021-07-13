package com.yuerrd.common.spi.impl;

import com.yuerrd.common.spi.Car;
import com.yuerrd.common.spi.MyAnnotation;

/**
 * @author yuerrd
 */
@MyAnnotation
public class Bus implements Car {
    @Override
    public void name() {
        System.out.println("Bus");
    }
}
