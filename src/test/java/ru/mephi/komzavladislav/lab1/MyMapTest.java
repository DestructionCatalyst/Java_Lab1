package ru.mephi.komzavladislav.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMapTest {

    MyMap map;

    @BeforeEach
    void setUp() {
        map = new MyMap();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        map.put("Five", 5);
        map.put("Six", 6);
        map.put("Seven", 7);
        map.put("Eight", 8);
        map.put("Nine", 9);
        map.put("Ten", 10);
    }

    @Test
    void testGetPut() {
        assertFalse(map.isEmpty());

        putAndGetWithAssertions(map,"Eleven", 11);
        putAndGetWithAssertions(map,"Twelve", 12);
        putAndGetWithAssertions(map,"One half", 0.5);
        putAndGetWithAssertions(map,"Three", "III");

        assertNull(map.get("Hundred"));

    }

    @Test
    void testGetDefault() {
        assertEquals(5, map.get("Five", "V"));
        assertEquals("L", map.get("Fifty", "L"));
        assertEquals("L", map.get("Fifty"));

    }

    @Test
    void testRemove(){
        MyMap emptyMap = new MyMap();
        assertNull(emptyMap.remove(1));

        assertEquals(5, map.remove("Five"));
        assertNull(map.get("Five"));

        assertEquals(10, map.remove("Ten"));
        assertNull(map.get("Ten"));

        putAndGetWithAssertions(map, "Five", "V");

        for (int i = 0; i < 10; i++) {
            assertNull(map.remove(i));
        }

    }

    @Test
    void testKeyContains() {
        assertTrue(map.keyContains("Six"));
        assertFalse(map.keyContains("Sixty"));

        putAndGetWithAssertions(map, "Eleven", "XI");
        assertTrue(map.keyContains("Eleven"));

        map.remove("Four");
        assertFalse(map.keyContains("Four"));

        for (int i = 0; i < 10; i++) {
            assertFalse(map.keyContains(i));
        }
    }

    @Test
    void testGetKeys() {

        MyList expected = new MyArrayList();
        expected.add("One");
        expected.add("Two");
        expected.add("Three");
        expected.add("Four");
        expected.add("Five");
        expected.add("Six");
        expected.add("Seven");
        expected.add("Eight");
        expected.add("Nine");
        expected.add("Ten");

        MyList actual = map.getKeys();

        assertMyListEqualsIgnoreOrder(expected, actual);

        map.remove("Four"); expected.remove(expected.index("Four"));
        map.remove("Five"); expected.remove(expected.index("Five"));
        map.remove("Nine"); expected.remove(expected.index("Nine"));

        actual = map.getKeys();

        assertMyListEqualsIgnoreOrder(expected, actual);
    }

    @Test
    void testGetValues() {

        MyList expected = new MyArrayList();
        for (int i = 1; i <= 10; i++) {
            expected.add(i);
        }

        MyList actual = map.getValues();

        assertMyListEqualsIgnoreOrder(expected, actual);

        map.remove("Four"); expected.remove(expected.index(4));
        map.remove("Five"); expected.remove(expected.index(5));
        map.remove("Nine"); expected.remove(expected.index(9));

        actual = map.getValues();

        assertMyListEqualsIgnoreOrder(expected, actual);
    }

    @Test
    void testGetEntries() {
        MyList expected = new MyArrayList();
        expected.add(new MyMap.KeyValuePair("One", 1));
        expected.add(new MyMap.KeyValuePair("Two", 2));
        expected.add(new MyMap.KeyValuePair("Three", 3));
        expected.add(new MyMap.KeyValuePair("Four", 4));
        expected.add(new MyMap.KeyValuePair("Five", 5));
        expected.add(new MyMap.KeyValuePair("Six", 6));
        expected.add(new MyMap.KeyValuePair("Seven", 7));
        expected.add(new MyMap.KeyValuePair("Eight", 8));
        expected.add(new MyMap.KeyValuePair("Nine", 9));
        expected.add(new MyMap.KeyValuePair("Ten", 10));

        MyList actual = map.getEntries();

        assertMyListEqualsIgnoreOrder(expected, actual);

        map.remove("Four"); expected.remove(expected.index(new MyMap.KeyValuePair("Four", null)));
        map.remove("Five"); expected.remove(expected.index(new MyMap.KeyValuePair("Five", null)));
        map.remove("Nine"); expected.remove(expected.index(new MyMap.KeyValuePair("Nine", null)));

        actual = map.getEntries();

        assertMyListEqualsIgnoreOrder(expected, actual);
    }

    @Test
    void testNull() {
        map.put(null, null);
        assertNull(map.get(null, 0));

        map.put(null, "Null");
        assertEquals("Null", map.get(null));

        map.put(null, null);
        assertNull(map.get(null, 0));
    }

    private void putAndGetWithAssertions(MyMap map, Object key, Object value){
        int len = map.size();

        if(map.get(key) == null)
            len++;

        map.put(key, value);

        assertEquals(value, map.get(key));
        assertEquals(len, map.size());
    }

    private void assertMyListEqualsIgnoreOrder(MyList expected, MyList actual){
        assertEquals(expected.size(), actual.size());
                //actual.containsAll(second) && second.containsAll(first)
        for (Object item : expected) {
            assertTrue(actual.contains(item));
        }
        for (Object item : actual) {
            assertTrue(expected.contains(item));
        }

    }


}
