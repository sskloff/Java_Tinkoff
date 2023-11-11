package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class Task8<T> implements Iterator<T> {
    Stack<T> elements = new Stack<>();

    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    @Override
    public T next() {
        return elements.pop();
    }

    public Task8(Collection<T> collection) {
        for (T el : collection) {
            elements.push(el);
        }
    }
}
