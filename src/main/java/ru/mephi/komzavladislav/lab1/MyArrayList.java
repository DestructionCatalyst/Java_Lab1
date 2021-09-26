package ru.mephi.komzavladislav.lab1;

import java.util.Iterator;

public class MyArrayList implements MyList{
    private static final int defaultBufferSize = 8;

    private Object[] array;
    private int length;

    public MyArrayList(int bufferSize){
        array = new Object[bufferSize];
        length = 0;
    }

    public MyArrayList(){
        this(defaultBufferSize);
    }

    @Override
    public void add(Object item) {
        if (length == array.length)
            grow();

        array[length] = item;
        length++;
    }

    @Override
    public void add(Object item, int position) {
        position = Math.max(0, position);
        position = Math.min(position, length);

        if (length == array.length)
            grow();

        System.arraycopy(array, position, array, position + 1, length - position);

        array[position] = item;
        length++;
    }

    @Override
    public Object get(int position) {
        if(isPositionOutOfBounds(position))
            return null;

        return array[position];
    }

    @Override
    public Object remove(int position) {
        if (isPositionOutOfBounds(position))
            return null;

        Object toRemove = array[position];
        System.arraycopy(array, position + 1, array, position, length - position);

        length--;

        return toRemove;
    }

    @Override
    public Object set(Object item, int position) {
        position = Math.max(0, position);
        position = Math.min(position, length - 1);

        if(isEmpty())
            return null;

        Object toSet = array[position];
        array[position] = item;

        return toSet;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object item) {

        return index(item) >= 0;
    }

    @Override
    public int index(Object item) {

        for (int i = 0; i < length; i++) {
            if(item.equals(array[i]))
                return i;
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MyArrayList{[");
        for (int i = 0; i < length; i++) {
            builder.append(array[i]);
            if(i != length - 1)
                builder.append(", ");
        }
        builder.append("]}");
        return builder.toString();
    }

    private boolean isPositionOutOfBounds(int position){
        return position < 0 || position >= size();
    }

    private void grow(){
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, length);

        array = newArray;
    }

    @Override
    public Iterator iterator() {
        return new MyArrayIterator();
    }

    class MyArrayIterator implements Iterator{
        int index;

        public MyArrayIterator() {
            index = 0;
        }


        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Object next() {
            Object res = array[index];
            index++;
            return res;
        }
    }
}
