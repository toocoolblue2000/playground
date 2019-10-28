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
        System.out.println("isValidBST :" + isValidBST(root));

        Node node = new Node(5);
        node.left = new Node(1);
        Node right = new Node(4);
        right.left = new Node(3);
        right.right = new Node(6);
        node.right = right;
        buffer = new StringBuilder();
        node.print(buffer, "", "");
        System.out.println(buffer);
        System.out.println("isValidBST :" + isValidBST(root));

        Node node1 = new Node(1);
        node1.left = new Node(7);
        node1.right = new Node(0);
        node1.left.left = new Node(7);
        node1.left.right = new Node(-8);
        System.out.println(maxLevelSum(node1));
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

    public static int maxLevelSum(Node root) {
        maxLevelSum(root, 1);
        int maxVal = Integer.MIN_VALUE;
        int level = 0;
        for (int i : map.keySet()) {
            if (map.get(i) > maxVal) {
                maxVal = map.get(i);
                level = i;
            }
        }
        return level;
    }
    private static Map<Integer, Integer> map = new HashMap<>();
    public static void maxLevelSum(Node rootNode, int level) {
        if (rootNode == null) {
            return;
        }

        map.put(level, map.getOrDefault(level, 0) + rootNode.nodeId);

        maxLevelSum(rootNode.left, level+1);
        maxLevelSum(rootNode.right, level+1);
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

    public static boolean isValidBST(Node root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(Node root, Integer lowerLimit, Integer upperLimit) {
        if (root == null) {
            return true;
        }
        if ((lowerLimit != null && root.nodeId <= lowerLimit) || (upperLimit != null && root.nodeId >= upperLimit)) {
            return false;
        }
        if (root.left != null && root.right != null && root.left.nodeId > root.right.nodeId) {
            return false;
        }

        return isValidBST(root.left, lowerLimit, root.nodeId) && isValidBST(root.right, root.nodeId, upperLimit);
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
