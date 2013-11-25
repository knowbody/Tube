package com.algo.structures;

public class Node<E> {
	
	public E payload; // Object containing this node
	public Node<E> next; // Link to next node
	public Node<E> prev; // Link to previous node
	
	/**
	 * New empty node
	 */
	public Node() {
		this(null, null, null);
	}
	
	/**
	 * New node with specified parameters
	 * 
	 * @param pay Object within this node
	 * @param next Link to next node
	 * @param prev Link to previous node
	 */
	public Node(E pay, Node<E> next, Node<E> prev) {
		this.next = next;
		this.prev = prev;
		this.payload = pay;
	}
	
}
