package org.example;

import java.util.LinkedList;

public class MyLinkedList<E> {
    private Node<E> tail;
    private Node<E> head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> nodeToRemove = getNode(index);
        if (nodeToRemove == head) {
            head = nodeToRemove.next;
        } else if (nodeToRemove == tail) {
            tail = nodeToRemove.prev;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        size--;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).value;
    }

    private Node<E> getNode(int index) {
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
    }



}
