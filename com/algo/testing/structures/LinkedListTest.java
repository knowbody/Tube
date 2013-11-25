package com.algo.testing.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.algo.structures.LinkedList;

public class LinkedListTest {

	private LinkedList<String> ll = new LinkedList<String>();
	
	@Before
	public void beforeEachTest() throws Exception {
		ll = new LinkedList<String>();
		ll.add("String1");
		ll.add("String2");
		ll.add("String3");
	}
	
	@Test
	public void testAdd() throws Exception {
		ll.add("String4");
		
		// Test size is increased after add
		assertEquals(ll.size(), 4);

		// Test element is present
		assertEquals(ll.get(3), "String4");
	}

	@Test
	public void testAddAll() throws Exception {
		LinkedList<String> l2 = new LinkedList<String>();
		l2.add("String4");
		l2.add("String5");
		l2.add("String6");
		
		ll.addAll(l2);
		
		// Test size is increased after addAll
		assertEquals(ll.size(), 6);
		
		// Test old elements are present
		assertEquals(ll.get(0), "String1");
		assertEquals(ll.get(1), "String2");
		assertEquals(ll.get(2), "String3");
		
		// Test new elements are present
		assertEquals(ll.get(3), "String4");
		assertEquals(ll.get(4), "String5");
		assertEquals(ll.get(5), "String6");
		
		// Test no more than needed elements are present
		assertEquals(ll.get(6), null);
	}

	@Test
	public void testGet() throws Exception {
		assertEquals(ll.get(1), "String2");
	}

	@Test
	public void testRemove() throws Exception {
		ll.remove(1);
		// Test element is not present anymore
		assertEquals(ll.get(0), "String1");
		assertEquals(ll.get(1), "String3");
		
		// Test size is decreased
		assertEquals(ll.size(), 2);
	}

	@Test
	public void testIsEmpty() throws Exception {
		ll = new LinkedList<String>();
		assertTrue(ll.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		assertEquals(ll.size(), 3);
	}

}
