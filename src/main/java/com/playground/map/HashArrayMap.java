package com.playground.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashArrayMap<K, V> implements Map<K, V> {

    private static final int SIZE = 2^4;
    private HashEntry[] entries = new HashEntry[SIZE];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        return true;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % SIZE;
        HashEntry<K, V> entry = this.entries[index];
        while (!entry.key.equals(key)) {
            entry = entry.next;
        }
        return entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % SIZE;
        HashEntry<K, V> entry = this.entries[index];
        HashEntry<K, V> newHashEntry = new HashEntry<>(key, value);
        if (entry == null) {
            this.entries[index] = newHashEntry;
            return null;
        } else {
            while(entry != null) {
                if (entry.equals(newHashEntry)) {
                    V ret = entry.getValue();
                    entry = newHashEntry;
                    return ret;
                }
                entry = entry.next;
            }
            entry.next = newHashEntry;
        }

        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    class HashEntry<K, V> implements Entry<K, V>{
        private K key;
        private V value;
        private HashEntry<K, V> next;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HashEntry) {
                return ((HashEntry) o).key.equals(key) && ((HashEntry) o).value.equals(value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.hashCode();
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(HashEntry<K, V> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashArrayMap<>();
        map.put("Nivas", "good");
        map.put("nivas", "true");
        map.put("NIVAS", "1");

        System.out.println(map.get("Nivas"));
        System.out.println(map.get("nivas"));
        System.out.println(map.get("NIVAS"));
    }
}