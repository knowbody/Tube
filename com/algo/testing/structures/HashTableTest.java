package com.algo.testing.structures;

import com.algo.structures.HashTable;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class HashTableTest {

    private HashTable table;

    @Before
    public void beforeEachTest() throws Exception {
        table = new HashTable();
        table.put("String1", 10);
        table.put("String2", 20);
        table.put("String3", 30);
    }


    @Test
    public void testPut() throws Exception {
        table.put("String4", 40);
        assertEquals(table.get("String4"), 40);
    }


    @Test
    public void testGet() throws Exception {
        assertEquals(table.get("String2"), 20);
    }


    @Test(expected = NullPointerException.class)
    public void testRemove() throws Exception {
        table.remove("String3");
        table.get("String3");
    }


    @Test
    public void testGetByIndex() throws Exception {
        // We know that key 'String3' (object int 30)
        // should be mapped to index 6 when size is 10.
        assertEquals(table.getByIndex(6), 30);
    }


    @Test
    public void testGenerateHashCode() throws Exception {
        // We know that key 'String3' (object int 30)
        // should be mapped to index 6 when size is 10.
        assertEquals(table.generateHashCode("String3", 10), 6);
    }


    @Test
    public void testResizeIfNeeded() throws Exception {
        // Should double the size if inserting another 5
        // stations as load factor is higher than 0.75.
        // This is already testing Resize() method.
        table.put("String4", 10);
        table.put("String5", 20);
        table.put("String6", 30);
        table.put("String7", 10);
        table.put("String8", 10);

        assertEquals(table.getLength(), 20);
    }


    @Test
    public void testGetValues() throws Exception {
        @SuppressWarnings("unchecked")
        Collection<Integer> values = table.getValues();

        int i = 0;
        for (int value : values) {
            if (i == 0) {
                // We know that first key should be String3 (index6)
                // which have value 30
                assertEquals(value, 30);
            }
            i++;
        }
    }


    @Test
    public void testGetKeys() throws Exception {
        // We know that first key should be String3 (index6)
        String[] keys = table.getKeys();
        assertEquals(keys[0], "String3");
    }


    @Test
    public void testGetSize() throws Exception {
        assertEquals(table.getSize(), 3);
    }


    @Test
    public void testGetLength() throws Exception {
        assertEquals(table.getLength(), 10);
    }

}
