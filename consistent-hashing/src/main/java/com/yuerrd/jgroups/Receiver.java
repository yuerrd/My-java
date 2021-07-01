package com.yuerrd.jgroups;

import com.yuerrd.jgroups.util.MessageBatch;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yuerrd
 */
public interface Receiver {

    default void receive(Message msg) {

    }

    default void receive(MessageBatch batch) {
        for (Message msg : batch) {
            try {
                receive(msg);
            } catch (Throwable t) {
            }
        }
    }

    default void viewAccepted(View new_view) {
    }

    default void block() {
    }

    default void unblock() {
    }

    default void getState(OutputStream output) throws Exception {
        throw new UnsupportedOperationException("getState() needs to be overridden by applications");
    }

    default void setState(InputStream input) throws Exception {
        throw new UnsupportedOperationException("setState() needs to be overridden by applications");
    }

}
