package com.yuerrd.concurrent;

import com.yuerrd.concurrent.locks.MyAbstractQueuedSynchronizer;

import java.util.concurrent.TimeUnit;

/**
 * @author yuerrd
 */
public class MyCountDownLatch {
    private static final class Sync extends MyAbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (; ; ) {
                int c = getState();
                if (c == 0) {
                    return false;
                }
                int nextc = c - 1;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }
    }

    private final Sync sync;

    public MyCountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        this.sync = new MyCountDownLatch.Sync(count);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public boolean await(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    public void countDown() {
        sync.releaseShared(1);
    }

    public long getCount() {
        return sync.getCount();
    }

    @Override
    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}
