package com.playground.tree;/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */

public class AVLTree {

    private AVLNode root;

    /*
                y             x
               / \           / \
              x  t3    =>   t1  y
             / \               / \
            t1 t2             t2 t3
    */
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;
        x.right = y;
        y.left = t2;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        y.height = Math.max(y.left.height, y.right.height) + 1;

        return x;
    }

    /*
           x                y
          / \              / \
         t1  y     =>     x  t3
            / \          / \
           t2 t3        t1 t2
    */
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;
        y.left = x;
        x.right = t2;

        x.height = Math.max(x.left.height, x.right.height) + 1;
        y.height = Math.max(y.left.height, y.right.height) + 1;
        return y;
    }

    private int getHeightDiff(AVLNode node) {
        return node.left.height - node.right.height;
    }

    public AVLNode insert(AVLNode root, int nodeId) {
        if (root == null) {
            return new AVLNode(nodeId);
        }

        if (nodeId < root.nodeId) {
            root.left = insert(root.left, nodeId);
        } else if (nodeId > root.nodeId) {
            root.right = insert(root.right, nodeId);
        } else {
            return root;
        }

        root.height = Math.max(root.right.height, root.left.height) + 1;
        int heightDiff = getHeightDiff(root);

        //left left
        if (heightDiff > 1 && nodeId < root.left.nodeId) {
            return rotateRight(root);
        }

        //right right
        if (heightDiff < -1 && nodeId > root.right.nodeId) {
            return rotateLeft(root);
        }

        //left right
        if (heightDiff > 1 && nodeId > root.left.nodeId) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        //right left
        if (heightDiff < -1 && nodeId < root.right.nodeId) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }
}

class AVLNode {
    int nodeId;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int nodeId) {
        this.nodeId = nodeId;
        this.height = 1;
    }

}
