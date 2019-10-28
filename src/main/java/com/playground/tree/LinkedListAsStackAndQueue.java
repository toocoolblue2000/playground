package com.playground.tree;


import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListAsStackAndQueue<T> implements LinkList<T> {

    public static void main(String[] args) {
        StackLinkList<Integer> stack = new LinkedListAsStackAndQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.top());
        assertEquals(4, stack.size());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.top());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.pop());
        assertNull(stack.pop());

        QueueLinkList<Integer> queue = new LinkedListAsStackAndQueue<>();
        queue.enque(1);
        queue.enque(2);
        queue.enque(3);
        queue.enque(4);
        assertEquals(4, queue.size());
        assertEquals(1, queue.peek());
        assertEquals(1, queue.deque());
        assertEquals(2, queue.peek());
        assertEquals(2, queue.deque());
        assertEquals(2, queue.size());
        assertEquals(3, queue.deque());
        assertEquals(4, queue.deque());
        assertNull(queue.deque());
    }

    int size = 0;
    private LinkNode<T> rootNode;
    private LinkNode<T> currNode;

    @Override
    public void addLast(T value) {
        LinkNode<T> linkNode = new LinkNode<>(value);
        if (rootNode == null) {
            rootNode = linkNode;
        } else {
            linkNode.prev = currNode;
            currNode.next = linkNode;
        }
        currNode = linkNode;
        size++;
    }

    @Override
    public void addFirst(T value) {
        LinkNode<T> linkNode = new LinkNode<>(value);
        if (currNode == null) {
            currNode = linkNode;
        } else {
            rootNode.prev = linkNode;
        }
        linkNode.next = rootNode;
        rootNode = linkNode;
        size++;
    }

    @Override
    public T removeFirst() {
        if (rootNode == null) {
            return null;
        } else {
            size--;
            LinkNode<T> tempNode = rootNode;
            rootNode = rootNode.next;
            return tempNode.value;
        }
    }

    @Override
    public T removeLast() {
        if (currNode == null) {
            return null;
        } else {
            size--;
            LinkNode<T> linkNode = currNode;
            currNode = currNode.prev;
            return linkNode.value;
        }
    }

    @Override
    public T peekFirst() {
        if (rootNode == null) {
            return null;
        } else {
            return rootNode.value;
        }
    }

    @Override
    public T peekLast() {
        if (currNode == null) {
            return null;
        } else {
            return currNode.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private class LinkNode<T> {
        public LinkNode(T value) {
            this.value = value;
        }
        T value;
        LinkNode<T> next;
        LinkNode<T> prev;
    }
}

interface LinkList<T> extends QueueLinkList<T>, StackLinkList<T> {
    void addLast(T value);
    void addFirst(T value);
    T removeFirst();
    T removeLast();
    T peekFirst();
    T peekLast();

    @Override
    default T deque() {
        return removeLast();
    }

    @Override
    default void enque(T i) {
        addFirst(i);
    }

    @Override
    default T peek() {
        return peekLast();
    }

    @Override
    default void push(T i) {
        addFirst(i);
    }

    @Override
    default T pop() {
        return removeFirst();
    }

    @Override
    default T top() {
        return peekFirst();
    }
}

interface QueueLinkList<T> {
    T deque();
    void enque(T i);
    T peek();
    int size();
}

interface StackLinkList<T> {
    T pop();
    void push(T i);
    T top();
    int size();
}
