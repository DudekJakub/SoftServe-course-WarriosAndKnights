package org.study.practice;

import java.util.Iterator;

public interface InfGenerator<T> extends Iterable<T>, Iterator<T> {

    @Override
    default Iterator<T> iterator() {
        return this;
    }

    @Override
    default boolean hasNext() {
        return true;
    }
}
