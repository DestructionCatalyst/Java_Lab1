package ru.mephi.komzavladislav.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyArrayListTest extends MyListTest{
    @BeforeEach
    void setUp() {
        emptyList = new MyArrayList();

        oneItemList = new MyArrayList();
        oneItemList.add(oneItemListItem);

        tenItemsList = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            tenItemsList.add(i);
        }
    }

    @Test
    void testToString() {
        assertEquals("MyArrayList{[]}",
                emptyList.toString());

        tenItemsList.set("Middle", 5);

        assertEquals("MyArrayList{[0, 1, 2, 3, 4, Middle, 6, 7, 8, 9]}",
                tenItemsList.toString());
    }

    @Test
    void testResize() {
        MyList list = new MyArrayList(1);

        for (int i = 0; i < 10; i++) {
            addAndGetWithAssertions(list, i);
        }

        for (int i = 0; i < 10; i++) {
            addAndGetWithAssertions(list, 5. + 0.1 * i, 6 + i);
        }

    }
}
