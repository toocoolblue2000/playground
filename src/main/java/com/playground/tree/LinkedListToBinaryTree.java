/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.tree;

import java.util.LinkedList;

class LinkedNode {
    public LinkedNode(int value) {
        this.value = value;
    }
    int value;
    LinkedNode next;
    LinkedNode prev;
}

public class LinkedListToBinaryTree {
    public static void main(String[] args) {
        LinkedNode head = new LinkedNode(0);
        LinkedNode curr = head;
        LinkedNode prev = null;
        int length = 0;
        for (int i = 1; i <= 10; i++) {
            curr.next = new LinkedNode(i);
            curr.prev = prev;
            prev = curr;
            curr = curr.next;
            length++;
        }
        Node node = sortedLinkedListToBST(head, length);
        System.out.println(node);
    }

    private static Node sortedLinkedListToBST(LinkedNode head, int length){
        if(length <= 0){
            return null;
        }
        LinkedNode mid = head;
        for (int i = 0; i < length / 2; i++) {
            mid = mid.next;
        }
        Node root = new Node(mid.value);
        root.left = sortedLinkedListToBST(head,length/2);
        root.right = sortedLinkedListToBST(mid.next,length - length / 2 - 1);
        return root;
    }
}
