package org.example;

import java.util.Arrays;

public class MyArrayList<E> {
    private static Object[] array;
    private int size ;

    public MyArrayList() {
        this.array = new Object[1];
        this.size = 0;
    }

    public void add(E value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    private void grow() {
        Object[] newData = new Object[array.length + 1];
        System.arraycopy(array, 0, newData, 0, array.length);
        array = newData;
    }

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add("Pasha");
        myArrayList.add(25);
        myArrayList.add(58);
        myArrayList.add(65);
        myArrayList.add(85);
        System.out.println(Arrays.toString(array));
        myArrayList.remove(2);
        System.out.println(Arrays.toString(array));
        myArrayList.clear();
        System.out.println("Arrays.toString = " + Arrays.toString(array));
        myArrayList.add(277);
        System.out.println(myArrayList.get(0));
        myArrayList.add(365);
        myArrayList.remove(0);
        System.out.println(Arrays.toString(array));


    }
}