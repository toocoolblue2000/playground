package com.playground.tree;

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
        int heightDiff = root.left.height - root.right.height;

        /*
         left left

                 z                                      y
                / \                                   /   \
               y   T4      Rotate Right (root)       x      z
              / \          - - - - - - - - ->      /  \    /  \
             x   T3                               T1  T2  T3  T4
            / \
          T1   T2
        */
        if (heightDiff > 1 && nodeId < root.left.nodeId) {
            return rotateRight(root);
        }

        /*
        right right

          z                                    y
         /  \                                /   \
        T1   y     Rotate Left (root)       z      x
            /  \   - - - - - - - - - ->    / \    / \
           T2   x                         T1  T2 T3  T4
               / \
             T3  T4
        */
        if (heightDiff < -1 && nodeId > root.right.nodeId) {
            return rotateLeft(root);
        }

        /*
        left right
             z                               z                              x
            / \                            /   \                           /  \
           y   T4  Rotate Left (y)        x    T4  Rotate Right(root)    y      z
          / \      - - - - - - - - ->    /  \      - - - - - - - ->     / \    / \
        T1   x                          y    T3                       T1  T2 T3  T4
            / \                        / \
          T2   T3                    T1   T2
        */
        if (heightDiff > 1 && nodeId > root.left.nodeId) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        /*right left
               z                            z                                 x
              / \                          / \                               /  \
            T1   y   Rotate Right (y)    T1   x      Left Rotate(root)     z      y
                / \  - - - - - - - - ->     /  \   - - - - - - - - - - >  / \    / \
               x   T4                      T2   y                        T1  T2  T3  T4
              / \                              /  \
            T2   T3                           T3   T4
         */
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
