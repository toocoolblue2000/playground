package com.playground.tree;

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
        StringBuilder buf = new StringBuilder();
        node.print(buf, "", "");
        System.out.println(buf);
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
