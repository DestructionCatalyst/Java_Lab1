package ru.mephi.komzavladislav.lab1;

import java.util.Objects;

public class MyMap {

    /*
     * 2. Реализовать словарь (map)
     * Операции
     * - Положить по ключу значение:          public void put(Object key, Object value);
     * - Получить по ключу:                   public Object get(Object key);
     * - Получить по ключу,                   public Object get(Object key, Object bydefault);
     * если значение null, тогда надо
     * вернуть значение по умолчанию,
     * которое задается вторым параметром.
     * Значение по умолчанию необходимо
     * сохранить.
     * - Удалить по ключу, возвращает текущее public Object remove(Object key);
     * значение
     * - Проверить наличие ключа:             public boolean keyContains(Object key);
     * - Получить список ключей:              public List getKeys();
     *  Получить список значений:            public List getValues();
     * - Получить список пар: ключ, значение: public List getEntries();
     * - Размер словаря:                      public int size();
     * - Пустой или нет:                      public boolean isEmpty();
     **/
    private static final int defaultBufferSize = 8;

    private MyList[] table;
    private int size;

    public MyMap(){
        table = new MyLinkedList[defaultBufferSize];
        size = 0;
    }

    public MyMap(int bufferSize){
        table = new MyLinkedList[bufferSize];
        size = 0;
    }

    public void put(Object key, Object value){

        if (((double) size )/ table.length > 0.9)
            grow();

        int arrayPosition = getArrayPosition(key);

        MyList sameHashList = table[arrayPosition];

        if (sameHashList == null){
            table[arrayPosition] = new MyLinkedList();
            sameHashList = table[arrayPosition];
        }

        boolean hasSameKey = false;

        for (Object o : sameHashList) {
            KeyValuePair curPair = (KeyValuePair) o;
            if (safeEquals(curPair.getKey(), key)) {
                curPair.setValue(value);
                hasSameKey = true;
            }
        }

        if(!hasSameKey) {
            sameHashList.add(new KeyValuePair(key, value));
            size++;
        }

    }

    public Object get(Object key){
        int arrayPosition = getArrayPosition(key);

        MyList sameHashList = table[arrayPosition];

        if(sameHashList == null)
            return null;

        for (Object o : sameHashList) {
            KeyValuePair curPair = (KeyValuePair) o;
            if (safeEquals(curPair.getKey(), key))
                return curPair.getValue();
        }

        return null;
    }

    public Object get(Object key, Object bydefault){
        if(!keyContains(key)){
            put(key, bydefault);
            return bydefault;
        }

        return get(key);
    }

    public Object remove(Object key){
        int arrayPosition = getArrayPosition(key);

        MyList sameHashList = table[arrayPosition];

        if(sameHashList == null)
            return null;

        // Find and remove pair with a given key
        // Using the fact that pairs with the same key are considered equal
        int positionToRemove = sameHashList.index(new KeyValuePair(key, null));


        if (positionToRemove == -1)
           return null;

        KeyValuePair removedPair = (KeyValuePair) sameHashList.remove(positionToRemove);

        if(sameHashList.isEmpty())
            table[arrayPosition] = null;

        size--;

        return removedPair.getValue();
    }

    public boolean keyContains(Object key){
        int arrayPosition = getArrayPosition(key);

        MyList sameHashList = table[arrayPosition];

        if(sameHashList == null)
            return false;

        for (Object o : sameHashList) {
            KeyValuePair curPair = (KeyValuePair) o;
            if (safeEquals(curPair.getKey(), key))
                return true;
        }

        return false;
    }

    public MyList getKeys(){
        return getFromPairs(KeyValuePair::getKey);
    }

    public MyList getValues(){
        return getFromPairs(KeyValuePair::getValue);
    }

    public MyList getEntries(){
        return getFromPairs(pair -> pair);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getArrayPosition(Object key) {
        if (key == null)
            return 0;

        return Math.abs(key.hashCode()) % table.length;
    }

    private static boolean safeEquals(Object o1, Object o2){

        if (o1 == null) {
            return o2 == null;
        }
        else if (o2 == null) {
            return false;
        }
        else return o1.equals(o2);
    }

    private void grow(){
        MyList[] newTable = new MyList[table.length * 2];
        System.arraycopy(table, 0, newTable, 0, table.length);

        table = newTable;
    }

    private MyList getFromPairs(LoopAction loopAction){
        MyList list = new MyArrayList();

        for (MyList sameHashList : table) {
            if (sameHashList == null)
                continue;

            for (Object pair: sameHashList) {
                KeyValuePair keyValuePair = (KeyValuePair) pair;

                list.add(loopAction.action(keyValuePair));
            }
        }

        return list;
    }

    interface LoopAction{
       Object action(KeyValuePair pair);
    }

    static class KeyValuePair{
        private final Object key;
        private Object value;

        public KeyValuePair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }


        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValuePair that = (KeyValuePair) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
