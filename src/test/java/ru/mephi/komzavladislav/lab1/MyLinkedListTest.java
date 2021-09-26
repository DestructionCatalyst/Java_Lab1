package ru.mephi.komzavladislav.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest extends MyListTest{

    @BeforeEach
    void setUp() {
        emptyList = new MyLinkedList();

        oneItemList = new MyLinkedList();
        oneItemList.add(oneItemListItem);

        tenItemsList = new MyLinkedList();
        for (int i = 0; i < 10; i++) {
            tenItemsList.add(i);
        }
    }

    @Test
    void testToString() {
        assertEquals("MyLinkedList{[]}",
                emptyList.toString());

        tenItemsList.set("Middle", 5);

        assertEquals("MyLinkedList{[0, 1, 2, 3, 4, Middle, 6, 7, 8, 9]}",
                tenItemsList.toString());
    }

    @Test
    void testListOfLists() {
        MyLinkedList listOfLists = new MyLinkedList();
        listOfLists.add(tenItemsList);
        listOfLists.add(emptyList, 0);
        listOfLists.add(oneItemList, 1);

        ((MyLinkedList)listOfLists.get(0)).add(0);
        assertEquals(3, ((MyLinkedList)listOfLists.get(2)).get(3));

    }
}