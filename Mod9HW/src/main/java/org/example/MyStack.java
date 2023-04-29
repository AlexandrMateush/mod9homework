package org.example;

import java.util.EmptyStackException;

public class MyStack<E> {
    private int size;
    private Object[] stackArray;

    public MyStack() {
        stackArray = new Object[0];
    }

    public void push(E value) {
        Object[] buffer = new Object[size + 1];
        System.arraycopy(stackArray, 0, buffer, 0, size);
        buffer[size] = value;
        stackArray = buffer;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Object[] buffer = new Object[size - 1];
        System.arraycopy(stackArray, 0, buffer, 0, index);
        System.arraycopy(stackArray, index + 1, buffer, index, size - index - 1);
        stackArray = buffer;
        size--;
    }

    public void clear() {
        stackArray = new Object[0];
        size = 0;
    }

    public int size() {
        return size;
    }

    public  E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (E) stackArray[size - 1];
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object[] buffer = new Object[size - 1];
        System.arraycopy(stackArray, 0, buffer, 0, size - 1);
        Object result = stackArray[size - 1];
        stackArray = buffer;
        size--;
        return (E) result;
    }
}
