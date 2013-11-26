package com.algo.testing.structures;

import com.algo.structures.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {

    private ArrayList<String> al;

    @Before
    public void beforeEachTest() throws Exception {
        al = new ArrayList<String>();
        al.add("String1");
        al.add("String2");
        al.add("String3");

    }

    @Test
    public void testAdd() throws Exception {
        al.add("String4");

        // Test size is increased after add
        assertEquals(al.getSize(), 4);

        // Test element is present
        assertEquals(al.get(3), "String4");
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(al.get(2), "String3");
    }

    @Test
    public void testSet() throws Exception {
        al.set(1, "StringNew");
        assertEquals(al.get(1), "StringNew");
    }

    @Test
    public void testRemove() throws Exception {
        al.remove(0);
        assertEquals(al.get(0), null);
    }

    @Test
    public void testResizeIfNeeded() throws Exception {
        // After adding 3 elements it should resize
        // This is testing testing also Resize() method
        al.add("String4");
        al.add("String5");
        al.add("String6");

        assertEquals(al.getLength(), 10);
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals(al.getSize(), 3);
    }

    @Test
    public void testGetLength() throws Exception {
        assertEquals(al.getLength(), 5);
    }

}
