package com.playground.tree;import java.util.*;

public class TreeTraversal {

    public static void main(String[] args) {
        Node root = createBinarySearchTree(new int[]{4,67,79,3,2,56,69,33,1,34,65,7,8});
        StringBuilder buffer = new StringBuilder();
        root.print(buffer, "", "");
        System.out.println(buffer);
        System.out.println("\npreOrder:  ");
        preOrder(root);
        System.out.println("\ninOrder:   ");
        inOrder(root);
        System.out.println("\npostOrder: ");
        postOrder(root);
        System.out.println("\nlevelOrder:");
        levelOrder(root);
        System.out.println("\nBreadth First search:");
        breadthFirstSearch(root, 56);
        System.out.println("\nmaxDepth:" + maxDepth(root));
        System.out.println("getLevelOfNode:" + getLevelOfNode(root, 33, 0));

    }

    private static Node createBinarySearchTree(int[] ints) {
        Node root = null;
        for (int i = 0; i < ints.length; i++) {
            root = createBinarySearchTree(root, ints[i]);
        }
        return root;
    }

    private static Node createBinarySearchTree(Node root, int val) {
        if (root == null) {
            return new Node(val);
        } else {
            if (val <= root.nodeId) {
                root.left = createBinarySearchTree(root.left, val);
            } else if (root.nodeId < val) {
                root.right = createBinarySearchTree(root.right, val);
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

    private static Node breadthFirstSearch(Node root, int valueToFind) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node polled = queue.poll();
            System.out.println("checking " + polled.nodeId);
            if (polled.nodeId == valueToFind) {
                System.out.println("Found " + polled.nodeId);
                return polled;
            }
            if (!polled.getChildren().isEmpty()) {
                polled.children.forEach(queue::offer);
            }
        }
        return null;
    }
}

class Node {
    int nodeId;
    Node left;
    Node right;
    List<Node> children = new ArrayList<>(2);

    public Node (int nodeId) {
        this.nodeId = nodeId;
    }
    public List<Node> getChildren() {
        if (left != null && !children.contains(left)) {
            children.add(left);
        }
        if (right != null && !children.contains(right)) {
            children.add(right);
        }
        return children;
    }

    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(nodeId);
        buffer.append('\n');
        for (Iterator<Node> it = getChildren().iterator(); it.hasNext();) {
            Node next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
