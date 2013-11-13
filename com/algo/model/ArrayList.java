package com.algo.model;

public class ArrayList<E> {
	
	private Object[] data; // Array for storing
	private int size; // Number of elements in array
	
	/**
	 * New list with initial length 20
	 */
	public ArrayList() {
		this(20);
	}
	
	/**
	 * New list with preferred size
	 * @param initialSize
	 */
	public ArrayList(int initialSize) {
		super();
		this.data = new Object[initialSize];
	}
	
	/**
	 * Add new element to array.
	 *  
	 * @param e  Object to add
	 * @return 
	 */
	public boolean add(E e) {
		resizeIfNeeded(size + 1);
		data[size] = e;
		size++;
		return true;
	}
	 
	
	/**
	 * Get element from array based on index.
	 * 
	 * @param index  Index on which to get array
	 * @return Object
	 */
	public Object get(int index) {
		return data[index];
	}
	
	
	/**
	 * Set element on the specific index.
	 * 
	 * @param index  Index on which to set element
	 * @param e  Element to set
	 * @return
	 */
	public boolean set(int index, E e) {
	    data[index] = e;
		return true;
	}
	
	
	/**
	 * Resize array if necessary.
	 * Always doubles the size.
	 * 
	 * @param newCapacity Desired capacity
	 */
	public void resizeIfNeeded(int newSize) {
		if (data.length < newSize) {
			// Creating new array with double size and copying data
			Object[] newData = new Object[size * 2];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
	}
	
	
	public int getSize() {
		return size;
	}
}
