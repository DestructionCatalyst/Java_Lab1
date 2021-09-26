package ru.mephi.komzavladislav.lab1;

import java.util.Iterator;

public class MyLinkedList implements MyList {

    private ListItem head;
    private ListItem tail;
    private int length;

    public MyLinkedList(){
        head = null;
        tail = null;
        length = 0;
    }

    @Override
    public void add(Object item){
        ListItem toInsert = new ListItem(item, null);
        if (tail != null)
            tail.setNext(toInsert);
        tail = toInsert;
        length++;

        if (head == null) // If was empty before adding
            head = tail;
    }

    @Override
    public void add(Object item, int position){
        if (isEmpty()){
            add(item);
            return;
        }

        position = Math.max(0, position);
        position = Math.min(position, length);

        ListItem toInsert;

        if (position == 0){
            ListItem afterInserted = head;
            toInsert = new ListItem(item, afterInserted);
            head = toInsert;
        }
        else {
            ListItem prev = itemAtPosition(position - 1);

            ListItem afterInserted = prev.getNext();
            toInsert = new ListItem(item, afterInserted);
            prev.setNext(toInsert);
        }

        if (position == length)
            tail = toInsert;

        length++;
    }

    @Override
    public Object get(int position){
        if (isPositionOutOfBounds(position))
            return null;

        ListItem toGet = itemAtPosition(position);
        return toGet.getValue();
    }

    @Override
    public Object remove(int position){
        if (isPositionOutOfBounds(position))
            return null;

        ListItem prev = null;
        ListItem toRemove;

        if (position == 0){
            toRemove = head;
            head = head.getNext();
        }
        else {
            prev = itemAtPosition(position - 1);
            toRemove = prev.getNext();

            prev.setNext(toRemove.getNext());
        }

        if (position == length - 1){
            tail = prev;
        }

        length--;

        return toRemove.getValue();
    }

    @Override
    public Object set(Object item, int position){
        position = Math.max(0, position);
        position = Math.min(position, length - 1);

        if(isEmpty())
            return null;

        ListItem toSet = itemAtPosition(position);
        Object prevValue = toSet.getValue();
        toSet.setValue(item);

        return prevValue;
    }

    @Override
    public boolean isEmpty(){
        return length == 0;
    }

    @Override
    public int size(){
        return length;
    }

    @Override
    public boolean contains(Object item){
        return index(item) >= 0;
    }

    @Override
    public int index(Object item){
        ListItem tmp = head;
        for (int i = 0; i < length; i++){
            if(item.equals(tmp.getValue()))
                return i;
            tmp = tmp.getNext();
        }
        return -1;
    }

    private ListItem itemAtPosition(int position){
        ListItem tmp = head;
        for (int i = 0; i < position; i++){
            tmp = tmp.getNext();
        }

        return tmp;
    }

    private boolean isPositionOutOfBounds(int position){
        return position < 0 || position >= size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MyLinkedList{[");
        ListItem item = head;
        while (item != null){
            builder.append(item.getValue());
            if(item.getNext() != null)
                builder.append(", ");
            item = item.getNext();
        }
        builder.append("]}");
        return builder.toString();
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator();
    }

    class MyListIterator implements Iterator{

        private ListItem ptr;

        public MyListIterator() {
            this.ptr = head;
        }

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public Object next() {
            Object res = ptr.getValue();
            ptr = ptr.getNext();

            return res;
        }
    }

    static class ListItem{
        private Object value;
        private ListItem next;

        public ListItem(Object value, ListItem next){
            this.value = value;
            this.next = next;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public ListItem getNext() {
            return next;
        }

        public void setNext(ListItem next) {
            this.next = next;
        }
    }
}
