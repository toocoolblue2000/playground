package com.playground.tree;/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {

    public static void main(String[] args) {
        Node root = createBST(new int[]{4,67,79,3,2,56,69,33,1,34,65,7,8});
        System.out.println("\npreOrder:  ");
        preOrder(root);
        System.out.println("\ninOrder:   ");
        inOrder(root);
        System.out.println("\npostOrder: ");
        postOrder(root);
        System.out.println("\nlevelOrder:");
        levelOrder(root);
        System.out.println("\nmaxDepth:" + maxDepth(root));
        System.out.println("getLevelOfNode:" + getLevelOfNode(root, 33, 0));

    }

    private static Node createBST(int[] ints) {
        Node root = null;
        for (int i = 0; i < ints.length; i++) {
            root = createBST(root, ints[i]);
        }
        return root;
    }

    private static Node createBST(Node root, int val) {
        if (root == null) {
            return new Node(val);
        } else {
            if (val <= root.nodeId) {
                root.left = createBST(root.left, val);
            } else if (root.nodeId < val) {
                root.right = createBST(root.right, val);
            }
        }
        return root;
    }

    private static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.nodeId + " ");
    }

    private static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.nodeId + " ");
        inOrder(root.right);
    }

    private static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.nodeId + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.nodeId + " ");
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }
    }

    private static int maxDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            if (lDepth > rDepth) {
                return (lDepth + 1);

            } else {
                return (rDepth + 1);
            }
        }
    }

    private static int getLevelOfNode(Node root, int key, int level) {
        if (root == null) {
            return 0;
        }
        if (root.nodeId == key) {
            return level;
        }

        int result = getLevelOfNode(root.left, key, level + 1);
        if (result != 0) {
            return result;
        }
        result = getLevelOfNode(root.right, key, level + 1);

        return result;
    }
}

class Node {
    int nodeId;
    Node left;
    Node right;
    public Node (int nodeId) {
        this.nodeId = nodeId;
    }
}
