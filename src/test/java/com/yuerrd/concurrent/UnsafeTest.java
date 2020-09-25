package com.yuerrd.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    public static void main(String[] args) {

        final Field theUnsafe;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            System.out.println(unsafe.getClass().getClassLoader());
            System.out.println(unsafe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
