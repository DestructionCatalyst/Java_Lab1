package ru.mephi.komzavladislav.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class MyListTest {
    protected static final String oneItemListItem = "This is the only item in this list";

    protected MyList emptyList;
    protected MyList oneItemList;
    protected MyList tenItemsList;

    @BeforeEach
    abstract void setUp();

    @Test
    public void testEmptyListEmptiness(){
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testAddingToEmptyListNoPosition() {
        addAndGetWithAssertions(emptyList, 1);
    }

    @Test
    void testAddingToEmptyListWithPosition() {
        addAndGetWithAssertions(emptyList, 1, 10);
    }

    @Test
    void testAddingToEndOfList() {
        addAndGetWithAssertions(oneItemList, 2);
        assertEquals(oneItemListItem, oneItemList.get(0));

        addAndGetWithAssertions(tenItemsList, "Fish", 10);
        assertEquals(9, tenItemsList.get(9));
    }

    @Test
    void testAddingOverEndOfList() {
        addAndGetWithAssertions(oneItemList, 2, 100);
        assertEquals(oneItemListItem, oneItemList.get(0));

        addAndGetWithAssertions(tenItemsList, "Fish", 11);
        assertEquals(9, tenItemsList.get(9));
    }

    @Test
    void testAddingToStartOfList() {
        addAndGetWithAssertions(oneItemList, 2, 0);
        assertEquals(oneItemListItem, oneItemList.get(1));

        addAndGetWithAssertions(tenItemsList, "Fish", 0);
        assertEquals(0, tenItemsList.get(1));
    }

    @Test
    void testAddingOverStartOfList() {
        // Adding to the end of one item list
        addAndGetWithAssertions(oneItemList, 2, -1);
        assertEquals(oneItemListItem, oneItemList.get(1));

        // Adding string to the end of the list
        addAndGetWithAssertions(tenItemsList, "Fish", -100);
        assertEquals(0, tenItemsList.get(1));
    }

    @Test
    void testAddingToMiddleOfList() {
        addAndGetWithAssertions(tenItemsList, 0.5, 1);
        assertEquals(0, tenItemsList.get(0));
        assertEquals(1, tenItemsList.get(2));

        addAndGetWithAssertions(tenItemsList, 4.5, 6);
        assertEquals(4, tenItemsList.get(5));
        assertEquals(5, tenItemsList.get(7));

        addAndGetWithAssertions(tenItemsList, 8.5, 11);
        assertEquals(8, tenItemsList.get(10));
        assertEquals(9, tenItemsList.get(12));
    }

    @Test
    public void testGetOutOfBounds(){
        // Getting over the start of the list
        assertNull(tenItemsList.get(-100));
        // Getting over the end of the list
        assertNull(tenItemsList.get(100));
    }

    @Test
    public void testRemoveOutOfBounds(){
        assertNull(tenItemsList.remove(-100));
        assertNull(tenItemsList.remove(100));
    }

    @Test
    void testRemoveFromEmptyList(){
        assertNull(emptyList.remove(0));
        assertNull(emptyList.remove(1));
        assertNull(emptyList.remove(-1));

        assertEquals(0, emptyList.size());
    }

    @Test
    void testRemoveOnlyElement(){
        removeWithAssertions(oneItemList, 0);

        addAndGetWithAssertions(oneItemList, 1);
        addAndGetWithAssertions(oneItemList, 0, 0);
        assertEquals(1, oneItemList.get(1));
    }

    @Test
    void TestRemoveMultipleElements() {
        removeWithAssertions(tenItemsList, 5);
        assertEquals(4, tenItemsList.get(4));
        assertEquals(6, tenItemsList.get(5));

        removeWithAssertions(tenItemsList, 0);
        assertEquals(1, tenItemsList.get(0));

        assertEquals(8, tenItemsList.size());

        removeWithAssertions(tenItemsList, 7);
        assertEquals(8, tenItemsList.get(6));
    }

    @Test
    void testSetOnEmptyList() {
        assertNull(emptyList.set(0, 0));
        assertNull(emptyList.set(0, 1));
        assertNull(emptyList.set(0, -1));

        assertEquals(0, emptyList.size());
    }

    @Test
    void testSetOnOnlyElement() {
        setWithAssertions(oneItemList, 10, 0);
        setWithAssertions(oneItemList, "10", 10);
        setWithAssertions(oneItemList, new Date(), -10);
    }

    @Test
    void testSetMultipleElement() {
        setWithAssertions(tenItemsList, "Middle", 5);
        setWithAssertions(tenItemsList, "Start", 0);
        setWithAssertions(tenItemsList, "Negative", -10);
        setWithAssertions(tenItemsList, "End", 9);
        setWithAssertions(tenItemsList, "Overflow", 10);

    }

    @Test
    void testFindInSmallLists() {
        assertEquals(-1, emptyList.index(0));
        assertFalse(emptyList.contains(0));

        assertEquals(-1, oneItemList.index(0));
        assertFalse(oneItemList.contains(0));
        assertEquals(0, oneItemList.index(oneItemListItem));
        assertTrue(oneItemList.contains(oneItemListItem));
    }

    @Test
    void testFind() {
        assertEquals(-1, tenItemsList.index(10));
        assertFalse(tenItemsList.contains(10));

        assertEquals(0, tenItemsList.index(0));
        assertTrue(tenItemsList.contains(0));
        assertEquals(9, tenItemsList.index(9));
        assertTrue(tenItemsList.contains(9));
        assertEquals(5, tenItemsList.index(5));
        assertTrue(tenItemsList.contains(5));

        tenItemsList.set("String among ints", 5);
        assertEquals(5, tenItemsList.index("String among ints"));
        assertTrue(tenItemsList.contains("String among ints"));
    }

    @Test
    abstract void testToString();

    @Test
    void testIterator() {
        assertIterableEquals(new ArrayList(), emptyList);

        assertIterableEquals(Collections.singletonList(oneItemListItem), oneItemList);

        assertIterableEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), tenItemsList);
    }

    @Test
    void testNull() {
        addAndGetWithAssertions(tenItemsList, null, 0);
        addAndGetWithAssertions(tenItemsList, null);
        addAndGetWithAssertions(tenItemsList, null, 5);

        removeWithAssertions(tenItemsList, 5);
        removeWithAssertions(tenItemsList, 0);

    }

    protected void addAndGetWithAssertions(MyList list, Object item){
        int len = list.size();

        list.add(item);
        assertEquals(item, list.get(len));
        assertEquals(len + 1, list.size());
    }

    protected void addAndGetWithAssertions(MyList list, Object item, int position){
        int len = list.size();
        int truePosition = Math.max(0, Math.min(position, len));

        list.add(item, position);

        assertEquals(item, list.get(truePosition));
        assertEquals(len + 1, list.size());
    }

    private void removeWithAssertions(MyList list, int position){
        int len = list.size();
        Object toBeRemoved = list.get(position);

        Object removed = list.remove(position);

        assertEquals(toBeRemoved, removed);
        // The list length should be decreased when item is removed
        assertEquals(len - 1, list.size());
    }

    private void setWithAssertions(MyList list, Object item, int position){
        int truePosition = Math.max(0, Math.min(position, list.size() - 1));
        Object toBeReplaced = list.get(truePosition);

        Object replaced = list.set(item, position);

        assertEquals(toBeReplaced, replaced);
        assertEquals(item, list.get(truePosition));
    }

}
