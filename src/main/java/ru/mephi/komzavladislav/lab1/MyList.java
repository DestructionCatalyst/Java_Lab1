package ru.mephi.komzavladislav.lab1;

import java.util.Iterator;

public interface MyList extends Iterable {

    /*
     * Сделать класс List
     * Реализовать функции addValue(Object) -> void
     * add(Object, int) -> void
     * remove(int) -> Object
     * get(int) -> Object
     * set(Object, int) -> Object
     * size() -> int
     * isEmpty() -> boolean
     * contains(Object) -> boolean
     * index(Object) -> int
     * ind < 0 => ind = 0
     * ind >= size => ind = size - для add
     * в set - вместо последнего элемента
     * Для get и remove если выходит за зону возвращаем null
     * */

    void add(Object item);

    void add(Object item, int position);

    Object get(int position);

    Object remove(int position);

    Object set(Object item, int position);

    boolean isEmpty();

    int size();

    boolean contains(Object item);

    int index(Object item);
}
