package com.playground.tree.graph;

import java.util.*;
/*

Approach: breadth first search
maintain a map to keep a map of explored nodes
 */
public class CloneGraph {


    public static void main(String[] args) {
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);
        g1.neighbours.add(g2);g2.neighbours.add(g1);
        g1.neighbours.add(g3);g3.neighbours.add(g1);
        g2.neighbours.add(g2);
        g3.neighbours.add(g4);g4.neighbours.add(g3);
        GraphNode clone = cloneGraph(g1);
        System.out.println(clone);
    }

    // returns the reference of new cloned source node
    public static GraphNode cloneGraph(GraphNode source) {
        Queue<GraphNode> q = new LinkedList<>();
        q.add(source);

        // An HashMap to keep track of all the
        // nodes which have already been created
        Map<GraphNode, GraphNode> masterCloneMap = new HashMap<>();

        //Put the node into the HashMap
        masterCloneMap.put(source, new GraphNode(source.label));

        while (!q.isEmpty()) {
            // Get the front node from the queue
            // and then visit all its neighbours
            GraphNode u = q.poll();

            // Get corresponding Cloned Graph Node
            GraphNode rootCloneNode = masterCloneMap.get(u);
            if (u.neighbours != null) {
                Set<GraphNode> neighbours = u.neighbours;
                for (GraphNode masterNode : neighbours) {
                    // Get the corresponding cloned node
                    // If the node is not cloned then we will
                    // simply get a null
                    GraphNode neighborCloneNodeFromMap = masterCloneMap.get(masterNode);

                    // Check if this node has already been created
                    if (neighborCloneNodeFromMap == null) {
                        q.add(masterNode);

                        // If not then create a new Node and
                        // put into the HashMap
                        neighborCloneNodeFromMap = new GraphNode(masterNode.label);
                        masterCloneMap.put(masterNode, neighborCloneNodeFromMap);
                    }

                    // add the 'neighborCloneNodeFromMap' to neighbour
                    // vector of the neighborCloneNodeFromMap
                    rootCloneNode.neighbours.add(neighborCloneNodeFromMap);
                }
            }
        }

        // Return the reference of cloned source Node
        return masterCloneMap.get(source);
    }
}

class GraphNode {
    int label;
    Set<GraphNode> neighbours = new HashSet<>();

    public GraphNode(int label) {
        this.label = label;
    }
}
