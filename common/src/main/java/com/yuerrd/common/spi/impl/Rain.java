package com.yuerrd.common.spi.impl;

import com.yuerrd.common.spi.IWeather;
import org.kohsuke.MetaInfServices;

/**
 * @author yuerrd
 */
@MetaInfServices
public class Rain implements IWeather {
    @Override
    public void status() {
        System.out.println("pleasantly cool");
    }
}
