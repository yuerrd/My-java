package com.yuerrd.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuerrd
 */
public class TestMain {
    public static void main(String[] args) {
        Map<String, String> runningCaches = Collections.synchronizedMap(new HashMap<>());
        System.out.println(runningCaches.put("number1","1"));
    }
}
