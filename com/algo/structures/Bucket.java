/**
 * Implementation of the Hash Table bucket
 */

package com.algo.structures;

public class Bucket {
	private String key; // Key
	private Object payload; // Bucket payload
	
	/**
	 * New null object
	 */
	public Bucket() {
		this("", null);
	}
	
	/**
	 * New bucket object with predefined key and payload
	 * 
	 * @param key  Bucket key
	 * @param o  Bucket payload object
	 */
	public Bucket(String key, Object o) {
		this.key = key;
		this.payload = o;
	}
	
	/**
	 * Get bucket key
	 * 
	 * @return Bucket key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Get bucket payload
	 * 
	 * @return  Object payload
	 */
	public Object getObject() {
		return this.payload;
	}
}
