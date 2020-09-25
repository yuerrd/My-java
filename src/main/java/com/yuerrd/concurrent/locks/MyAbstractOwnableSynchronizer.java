package com.yuerrd.concurrent.locks;

import java.io.Serializable;

public abstract class MyAbstractOwnableSynchronizer implements Serializable {

    private static final long serialVersionUID = 1L;

    protected MyAbstractOwnableSynchronizer() {
    }

    private transient Thread exclusiveOwnerThread;


    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
