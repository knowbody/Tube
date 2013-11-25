/**
 * Implementation of the linked list using
 * double linking and head and tail sentinels.
 */

package com.algo.structures;

public class LinkedList<E> {
	
	private Node<E> head; // Head sentinel of the list
	private Node<E> tail; // Tail sentinel of the list
	private int size; // Size of the list
	
	/**
	 * New empty list
	 */
	public LinkedList() {
		this.head = new Node<E>();
		this.tail = new Node<E>();
		head.next = tail;
		tail.prev = head;
	}
	
	/**
	 * Add new element at the end of the list
	 * 
	 * @param payload  Object to store
	 */
	public void add(E payload) {
		Node<E> last = tail.prev;
        Node<E> newNode = new Node<E>(payload, tail, last);
        tail.prev = newNode;
        last.next = newNode;
        size++;
	}
	
	/**
	 * Append list to the end of the list
	 * 
	 * @param list  List to append
	 */
	public void addAll(LinkedList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}
	
	/**
	 * Get element from the list on specific place
	 * 
	 * @return  Object Payload
	 */
	public E get(int place) {
		return getNode(place).payload;
	}
	
	/**
	 * Get node from the list on specific place
	 * 
	 * @return  Node
	 */
	private Node<E> getNode(int place) {
		Node<E> e = head;
		for (int i = 0; i <= place; i++) {
			e = e.next;
		}
		
		return e;
	}
	
	/**
	 * Remove node from the list on specific place
	 */
	public void remove(int place) {
		Node<E> e = getNode(place);
		e.prev.next = e.next; // Reseting previous node pointer to next
		e.next.prev = e.prev; // Reseting next node pointer to previous
		
		// Setting up nulls for garbage collector to do his job
		e.prev = e.next = null;
		e.payload = null;
	}
	
	/**
	 * Returns if size == 0
	 * 
	 * @return True if list is empty
	 */
	public Boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Returns size of the list
	 * 
	 * @return Size of the list
	 */
	public int size() {
		return size;
	}

}
