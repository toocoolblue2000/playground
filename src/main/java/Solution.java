import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            GraphNode node1 = map.getOrDefault(x, new GraphNode(x));
            GraphNode node2 = map.getOrDefault(y, new GraphNode(y));
            node1.addAdjacentNodes(node2);
            node2.addAdjacentNodes(node1);
            map.put(x, node1);
            map.put(y, node2);
        }

        LinkedHashMap<Integer, GraphNode> sortedMap =
                map.entrySet().stream().
                        sorted(Map.Entry.comparingByValue()).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        List<Integer> citiesRoute = new ArrayList<>();
        GraphNode node = null;
        for (Map.Entry<Integer, GraphNode> entry : sortedMap.entrySet()) {
            if (entry.getValue().getAdjacentNodes().size() > 1) {
                node = entry.getValue();
                break;
            }
        }

        while (node != null) {
            citiesRoute.add(node.getCity());
            sortedMap.remove(node);
            for (Map.Entry<Integer, GraphNode> entry : sortedMap.entrySet()) {
                entry.getValue().removeAdjacentNodes(node);
            }
            if (!node.getAdjacentNodes().isEmpty()) {
                node = node.getAdjacentNodes().toArray(new GraphNode[0])[0];
            } else {
                node = null;
            }
        }
        System.out.println(citiesRoute.size());
        for (Integer i: citiesRoute) {
            System.out.print(i + " ");
        }
    }
}

class GraphNode implements Comparable<GraphNode> {
    private int city;
    private Set<GraphNode> adjacentNodes = new HashSet<>();

    public GraphNode(int city) {
        this.city = city;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public Set<GraphNode> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNodes(GraphNode adjacentNode) {
        this.adjacentNodes.add(adjacentNode);
    }

    public void removeAdjacentNodes(GraphNode adjacentNode) {
        this.adjacentNodes.remove(adjacentNode);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof GraphNode) && this.city == ((GraphNode)obj).city;
    }

    @Override
    public int compareTo(GraphNode node) {
        if (this == node) {
            return 0;
        } else if (this.getAdjacentNodes().size() < node.getAdjacentNodes().size()) {
            return -1;
        } else if (this.getAdjacentNodes().size() == node.getAdjacentNodes().size()) {
            return 0;
        } else if (this.getAdjacentNodes().size() > node.getAdjacentNodes().size()) {
            return 1;
        }

        return 0;
    }
}