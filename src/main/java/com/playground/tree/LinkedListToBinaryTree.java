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


        LinkedNode list1 = new LinkedNode(1);
        list1.next = new LinkedNode(2);
        list1.next.next = new LinkedNode(4);

        LinkedNode list2 = new LinkedNode(1);
        list2.next = new LinkedNode(3);
        list2.next.next = new LinkedNode(4);

        LinkedNode out = mergeTwoLists(list1, list2);
        System.out.println(out);
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

    public static LinkedNode mergeTwoLists(LinkedNode l1, LinkedNode l2) {
        if (l1.next == null) return l2;
        if (l2.next == null) return l1;
        LinkedNode root = new LinkedNode(0);
        LinkedNode curr = root;

        while (true) {
            if (l1 == null) {
                curr.next = l2;
                break;
            }
            if (l2 == null) {
                curr.next = l1;
                break;
            }
            if (l1.value < l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return root.next;
    }
}
