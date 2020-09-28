package com.yuerrd.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Field field = MyObj.class.getDeclaredField("objField");
        long offset = unsafe.objectFieldOffset(field);
        MyObj myObj = new MyObj();
        myObj.objField = 1;
        unsafe.compareAndSwapInt(myObj, offset, 0, 2);
        System.out.println("1.\t" + (myObj.objField == 2));
        unsafe.compareAndSwapInt(myObj, offset, 1, 2);
        System.out.println("2.\t" + (myObj.objField == 2));


    }

}
