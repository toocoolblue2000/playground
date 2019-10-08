
package com.playground.design;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUCache {

    private Map<Integer, LruCacheNode> map = new HashMap<>();
    private Map<Integer, Integer> DATABASE = new HashMap<Integer, Integer>(){
        @Override
        public Integer get(Object key) {
            try {
                System.out.println("Calling database wait time 1000ms");
                Thread.sleep(1000);
                put((Integer) key, ((Integer) key * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return super.get(key);
        }
    };
    private LruCacheNode start;
    private LruCacheNode end;

    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        LruCacheNode node = map.get(key);
        if (node != null) {
            return node.value;
        } else {
            Integer integer = DATABASE.get(key);
            set(key, integer);
            return integer;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            LruCacheNode node = map.get(key);
            node.value = value;
            removeFromCache(node);
            addToTopOfCache(node);
        } else {
            LruCacheNode node = new LruCacheNode();
            node.key = key;
            node.value = value;
            if (map.size() >= capacity) {
                map.remove(end.key);
                removeFromCache(end);
                addToTopOfCache(node);
            } else {
                addToTopOfCache(node);
            }
            map.put(key, node);
        }
    }

    class LruCacheNode {
        int key;
        int value;
        LruCacheNode left;
        LruCacheNode right;
    }

    private void addToTopOfCache(LruCacheNode node) {
        node.left = null;
        node.right = start;
        if (start != null) {
            start.left = node;
        }
        start = node;
        if (end == null) {
            end = start;
        }
    }

    private void removeFromCache(LruCacheNode node) {
        if (node.left != null) {
            node.left.right = node.right;
        } else {
            start = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            end = node.left;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set(2, 1);
        lruCache.set(2, 2);
        assertEquals(lruCache.get(2), 2);
        lruCache.set(1, 1);
        lruCache.set(4, 1);
        assertEquals(lruCache.get(2), 2000);
        assertEquals(lruCache.get(2), 2000);
    }
}
