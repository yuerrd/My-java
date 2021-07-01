package com.yuerrd.jgroups.util;

import com.yuerrd.jgroups.Message;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author yuerrd
 */
public class MessageBatch implements Iterable<Message> {
    @Override
    public Iterator<Message> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Message> action) {

    }

    @Override
    public Spliterator<Message> spliterator() {
        return null;
    }
}
