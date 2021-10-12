package ru.mephi.komzavladislav.lab2;

import java.util.Iterator;

public class MyGenericLinkedList<T> implements Iterable<T>{

    protected ListItem<T> head;
    protected ListItem<T> tail;
    protected int length;

    public MyGenericLinkedList(){
        head = null;
        tail = null;
        length = 0;
    }
    
    public void add(T item){
        ListItem<T> toInsert = new ListItem<>(item, null);
        if (tail != null)
            tail.setNext(toInsert);
        tail = toInsert;
        length++;

        if (head == null) // If was empty before adding
            head = tail;
    }
    
    public void add(T item, int position){
        if (isEmpty()){
            add(item);
            return;
        }

        position = Math.max(0, position);
        position = Math.min(position, length);

        ListItem<T> toInsert;

        if (position == 0){
            ListItem<T>afterInserted = head;
            toInsert = new ListItem<>(item, afterInserted);
            head = toInsert;
        }
        else {
            ListItem<T> prev = itemAtPosition(position - 1);

            ListItem<T> afterInserted = prev.getNext();
            toInsert = new ListItem<T>(item, afterInserted);
            prev.setNext(toInsert);
        }

        if (position == length)
            tail = toInsert;

        length++;
    }

    public T get(int position){
        if (isPositionOutOfBounds(position))
            return null;

        ListItem<T> toGet = itemAtPosition(position);
        return toGet.getValue();
    }

    public T remove(int position){
        if (isPositionOutOfBounds(position))
            return null;

        ListItem<T> prev = null;
        ListItem<T> toRemove;

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

    public T set(T item, int position){
        position = Math.max(0, position);
        position = Math.min(position, length - 1);

        if(isEmpty())
            return null;

        ListItem<T> toSet = itemAtPosition(position);
        T prevValue = toSet.getValue();
        toSet.setValue(item);

        return prevValue;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int size(){
        return length;
    }

    public boolean contains(T item){
        return index(item) >= 0;
    }

    public int index(T item){
        ListItem<T> tmp = head;
        for (int i = 0; i < length; i++){
            if(item.equals(tmp.getValue()))
                return i;
            tmp = tmp.getNext();
        }
        return -1;
    }

    private ListItem<T> itemAtPosition(int position){
        ListItem<T> tmp = head;
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
        StringBuilder builder = new StringBuilder("MyGenericLinkedList{[");
        ListItem<T> item = head;
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
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    class MyListIterator implements Iterator<T>{

        private ListItem<T> ptr;

        public MyListIterator() {
            this.ptr = head;
        }

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public T next() {
            T res = ptr.getValue();
            ptr = ptr.getNext();

            return res;
        }
    }

    static class ListItem<T>{
        private T value;
        private ListItem<T> next;

        public ListItem(T value, ListItem<T> next){
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public ListItem<T> getNext() {
            return next;
        }

        public void setNext(ListItem<T> next) {
            this.next = next;
        }
    }
}
