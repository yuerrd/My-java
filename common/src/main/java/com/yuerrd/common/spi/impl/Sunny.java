package com.yuerrd.common.spi.impl;

import com.yuerrd.common.spi.IWeather;
import org.kohsuke.MetaInfServices;

/**
 * @author yuerrd
 */
@MetaInfServices
public class Sunny implements IWeather {
    @Override
    public void status() {
        System.out.println("It's hot");
    }
}
