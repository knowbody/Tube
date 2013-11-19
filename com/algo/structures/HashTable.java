/**
 * Implementation of the hash table using 
 * dynamic array of buckets and linear probing.
 * 
 * Load factor: 75%
 * Initial Capacity: 10;
 */

package com.algo.structures;

import java.util.Collection;

public class HashTable {
	private double maxLoadFactor = 0.75;
	private int size;
	private Bucket[] data;
	
	/**
	 * New hash table with initial capacity 10
	 */
	public HashTable() {
		this(10);
	}
	
	/**
	 * New hash table with the preferred initial capacity
	 * 
	 * @param initCapacity
	 */
	public HashTable(int initCapacity) {
		data = new Bucket[initCapacity];
	}
	
	/**
	 * Insert new element to the bucket specified by key.
	 * If key matches the already inserted it will only 
	 * replace the bucket with the one specified in request.
	 * 
	 * @param key  String key
	 * @param el  Element to store
	 * @return
	 */
	public void put(String key, Object el) {
		resizeIfNeeded(size + 1);
		
		int index = hashCode(key, data.length);
		boolean update = false;
		
		String s = el.toString();
		// Start linear probing
		while (data[index] != null) {
			if (!s.equalsIgnoreCase(data[index].getObject().toString())) {
				index = (index == (data.length) - 1) ? 0 : index + 1;
			} else {
				update = true;
				break;
			}
		}
		
		// If this is not only update we are increasing size
		if (!update) {
			size++;
		}
		
		data[index] = new Bucket(key, el);
	}
	
	/**
	 * Get element by key
	 * 
	 * @param key  String key
	 * @return  Requested object from key
	 */
	public Object get(String key) {
		int index = hashCode(key, data.length);

		// Start linear probing
		while (!key.equalsIgnoreCase(data[index].getKey())) {
			index = (index == (data.length) - 1) ? 0 : index + 1;
		}
		
		return data[index].getObject();
	}	
	
	/**
	 * Remove key and value from table
	 * 
	 * @param key  String key
	 * @return  Requested object from key
	 */
	public void remove(String key) {
		int index = hashCode(key, data.length);

		// Start linear probing
		while (!key.equalsIgnoreCase(data[index].getKey())) {
			index = (index == (data.length) - 1) ? 0 : index + 1;
		}
		
		data[index] = new Bucket();
	}
	
	/**
	 * Get element by index
	 * 
	 * @param index  Index
	 * @return  Requested object from index
	 */
	public Object getByIndex(int index) {
		if (data[index].getObject() != null) {
			return data[index].getObject();
		} else {
			return null;
		}
	}
	
	/**
	 * Creating hash code for the specific the key.
	 * 
	 * @param key  String key
	 * @return  Hash code
	 */
	public int hashCode(String key, int length) {
		int hash = Math.abs(key.hashCode());
		return hash % length;
	}
	
	/**
	 * Resize data size if required based on the load factor.
	 * Always double the size.
	 * 
	 * @param newSize  Required size
	 */
	public void resizeIfNeeded(int newSize) {
		double loadFactor = (double) newSize / data.length;
		
		if (loadFactor > maxLoadFactor) {
			resize();
		}
	}
	
	/**
	 * 
	 */
	public void resize() {
		int length = data.length;
		Bucket[] newTable = new Bucket[length * 2];
		
		for (int i = 0; i < length; i++) {
			if (data[i] != null) {
				int index = hashCode(data[i].getKey(), length * 2);
				// Start probing
				while (newTable[index] != null) {
					index = (index == (length * 2) - 1) ? 0 : index + 1;
				}
				// Assigning values to new bucket
				newTable[index] = new Bucket(data[i].getKey(), data[i].getObject());
			}
		}
		data = newTable;
	}
	
	/**
	 * Get all values from the table
	 * 
	 * @return  Object array of all values in the buckets
	 */
	public Collection getValues() {
		Object[] values = new Object[size];
		
		int j = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				values[j] = data[i].getObject();
				j++;
			}
		}
		return java.util.Arrays.asList(values);
	}
	
	/**
	 * Get all keys from the table
	 * 
	 * @return  Object array of all values in the buckets
	 */
	public String[] getKeys() {
		String[] keys = new String[size];
		
		int j = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				keys[j] = data[i].getKey();
				j++;
			}
		}
		return keys;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int getLength() {
		return data.length;
	}
}
