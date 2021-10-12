package ru.mephi.komzavladislav.lab2;

import ru.mephi.komzavladislav.lab1.MyLinkedList;

/**
 * A linked list that is guaranteed to be sorted
 *
 * @param <T> Type of list items
 */
public class MySortedLinkedList<T extends Comparable<T>> extends MyGenericLinkedList<T> {

    @Override
    public void add(T item) {
        if (head == null) {
            super.add(item);
            return;
        }

        // For O(1) appending
        if (item.compareTo(tail.getValue()) > 0) {
            super.add(item);
            return;
        }

        ListItem<T> prev = head;
        ListItem<T> ptr = head;
        while (item.compareTo(ptr.getValue()) > 0){
            prev = ptr;
            ptr = ptr.getNext();
        }

        ListItem<T> toInsert;

        if (ptr == head){
            ListItem<T> afterInserted = head;
            toInsert = new ListItem<T>(item, afterInserted);
            head = toInsert;
        }
        else {
            toInsert = new ListItem<T>(item, ptr);
            prev.setNext(toInsert);
        }
        length++;
    }

    @Override
    public void add(T item, int position) {
        throw new UnsupportedOperationException("This is a sorted list, you can't select position to insert");
    }

    @Override
    public T set(T item, int position) {
        throw new UnsupportedOperationException("This is a sorted list, you can't change items, only add and remove");
    }

    public static <E extends Comparable<E>> MySortedLinkedList<E> mergeSortedLists(MySortedLinkedList<E> list1,
                                                                                    MySortedLinkedList<E> list2){
        MySortedLinkedList<E> resultList = new MySortedLinkedList<>();

        ListItem<E> ptr1 = list1.head;
        ListItem<E> ptr2 = list2.head;

        while (ptr1 != null && ptr2 != null){
            E val1 = ptr1.getValue();
            E val2 = ptr2.getValue();
            if(val1.compareTo(val2) > 0){
                resultList.add(val2);
                ptr2 = ptr2.getNext();
            }
            else{// Item from list1 <= item from list2
                resultList.add(val1);
                ptr1 = ptr1.getNext();
            }
        }
        ListItem<E> leftovers;
        if (ptr1 != null) {
            leftovers = ptr1;
        }
        else if (ptr2 != null) {
            leftovers = ptr2;
        }
        else
            return resultList;

        while (leftovers != null){
            resultList.add(leftovers.getValue());
            leftovers = leftovers.getNext();
        }

        return resultList;
    }
    
}
