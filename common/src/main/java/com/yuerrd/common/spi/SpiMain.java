package com.yuerrd.common.spi;

import java.util.ServiceLoader;

/**
 * @author yuerrd
 */
public class SpiMain {

    public static void main(String[] args) {
        ServiceLoader<IWeather> iWeathers = ServiceLoader.load(IWeather.class);
        for (IWeather iWeather : iWeathers) {
            iWeather.status();
        }
    }
}
