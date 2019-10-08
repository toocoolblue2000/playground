package com.playground.tree.graph;
import java.util.*;
import java.util.stream.Collectors;

//https://www.baeldung.com/java-dijkstra
public class DijkstraShortestPath {

    public static void main(String[] args) {
        WeightedNode nodeA = new WeightedNode("A");
        WeightedNode nodeB = new WeightedNode("B");
        WeightedNode nodeC = new WeightedNode("C");
        WeightedNode nodeD = new WeightedNode("D");
        WeightedNode nodeE = new WeightedNode("E");
        WeightedNode nodeF = new WeightedNode("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = calculateShortestPath(graph, nodeA);
        System.out.println(graph);
    }


    public static Graph calculateShortestPath(Graph graph, WeightedNode source) {
        source.setDistance(0);

        Set<WeightedNode> settledNodes = new HashSet<>();
        Set<WeightedNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while(!unsettledNodes.isEmpty()) {
            WeightedNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<WeightedNode, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                WeightedNode adjacentNode = entry.getKey();
                Integer adjacentNodeDistance = entry.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, adjacentNodeDistance, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }

        return graph;
    }

    private static void calculateMinimumDistance(WeightedNode adjacentNode, Integer adjacentNodeDistance, WeightedNode currentNode) {
        int currentNodeDistance = currentNode.getDistance();
        if (currentNodeDistance + adjacentNodeDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(currentNodeDistance + adjacentNodeDistance);
            List<WeightedNode> list = new ArrayList<>(currentNode.getShortestPath());
            list.add(currentNode);
            adjacentNode.setShortestPath(list);
        }
    }

    private static WeightedNode getLowestDistanceNode(Set<WeightedNode> unsettledNodes) {
        WeightedNode lowestDistanceNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (WeightedNode node : unsettledNodes) {
            if (node.getDistance() < minDistance) {
                minDistance = node.getDistance();
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}

class Graph {
    private Set<WeightedNode> nodes = new HashSet<>();

    public void addNode(WeightedNode node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return String.format("Graph{ %s }", Arrays.deepToString(nodes.toArray()));
    }
}

class WeightedNode {
    private String name;
    private Map<WeightedNode, Integer> adjacentNodes = new HashMap<>();
    private List<WeightedNode> shortestPath = new ArrayList<>();
    private int distance = Integer.MAX_VALUE - 1;

    public WeightedNode(String name) {
        this.name = name;
    }

    public void addDestination(WeightedNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeightedNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<WeightedNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Map<WeightedNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<WeightedNode, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public String toString() {
        return String.format("Node :%s :: Distance: %d ::, shortest path %s", name, distance, shortestPath.stream().map(a -> a.name).collect(Collectors.toList())) + "\n";
    }
}
