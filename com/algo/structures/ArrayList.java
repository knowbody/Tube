package com.algo.structures;

public class ArrayList<E> {
    private Object[] data; // Array for storing
    private int size; // Number of elements in array

    /**
     * New list with initial length 5
     */
    public ArrayList() {
        this(5);
    }

    /**
     * New list with preferred size
     *
     * @param initialSize
     */
    public ArrayList(int initialSize) {
        this.data = new Object[initialSize];
    }

    /**
     * Add new element to array.
     *
     * @param e Object to add
     * @return
     */
    public void add(E e) {
        resizeIfNeeded(size + 1);
        data[size] = e;
        size++;
    }


    /**
     * Get element from array based on index.
     *
     * @param index Index on which to get array
     * @return Object
     */
    public Object get(int index) {
        return data[index];
    }


    /**
     * Set element on the specific index.
     *
     * @param index Index on which to set element
     * @param e     Element to set
     * @return
     */
    public void set(int index, E e) {
        data[index] = e;
    }

    /**
     * Remove element from the list
     *
     * @param index Index on which to set element
     * @param e     Element to set
     * @return
     */
    public void remove(int index) {
        data[index] = null;
    }


    /**
     * Resize array if necessary.
     * Always doubles the size.
     *
     * @param newSize Desired capacity
     */
    public void resizeIfNeeded(int newSize) {
        if (data.length < newSize) {
            resize();
        }
    }

    /**
     * Creating new array with double size and copying data
     */
    public void resize() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return data.length;
    }
}
