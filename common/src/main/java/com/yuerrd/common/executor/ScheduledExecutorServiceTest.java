package com.yuerrd.common.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yuerrd
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run "+ System.currentTimeMillis() +" thread name " + Thread.currentThread().getName());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}
