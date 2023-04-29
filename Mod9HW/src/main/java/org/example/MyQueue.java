package org.example;

public class MyQueue<E> {
    private NodeQ first;
    private NodeQ last;

    public void add(E value) {
        NodeQ newNode = new NodeQ(value);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public void clear() {
        first = null;
        last = null;
    }

    public int size() {
        int count = 0;
        NodeQ current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Object peek() {
        if (first == null) {
            return null;
        } else {
            return first.value;
        }
    }

    public Object poll() {
        if (first == null) {
            return null;
        } else {
            Object value = first.value;
            first = first.next;
            if (first == null) {
                last = null;
            }
            return value;
        }
    }
    private class NodeQ {
        Object value;
        NodeQ next;

        public NodeQ(Object value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        MyQueue<Object> myQueue = new MyQueue<>();

    }

}
