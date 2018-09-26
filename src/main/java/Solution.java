import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Node {
    Node(int nodeId) {
        this.nodeId = nodeId;
    }
    int nodeId;
    Set<Node> connectedNodes = new HashSet<>();
}

public class Solution {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib <= c_road) {
            return n * c_lib;
        }
        final int roadsPerLibrary = c_lib / c_road;
        Map<Integer, Node> nodesMap = new HashMap<>();
        List<Node> allNodes = new ArrayList<>();
        Set<Node> hasLibrary = new HashSet<>();
        Set<Node> hasRoadAccess = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            nodesMap.put(i, new Node(i));
        }
        for (int[] road : cities) {
            Node city1 = nodesMap.get(road[0]);
            Node city2 = nodesMap.get(road[1]);
            city1.connectedNodes.add(city2);
            city2.connectedNodes.add(city1);
        }
        allNodes.addAll(nodesMap.values());
        Collections.sort(allNodes, Comparator.comparingInt(o -> o.connectedNodes.size()));

        int roads = 0;

        while (!allNodes.isEmpty()) {
            Node node = allNodes.remove(0);
            hasLibrary.add(node);
            int rpl = roadsPerLibrary;
            Queue<Node> queue = new LinkedList<>();
            do {
                for (Node connectedNode : node.connectedNodes) {
                    if (hasLibrary.contains(connectedNode) || hasRoadAccess.contains(connectedNode)) {
                        continue;
                    }
                    queue.offer(connectedNode);
                    hasRoadAccess.add(connectedNode);
                    allNodes.remove(connectedNode);
                    roads++;
                }
            } while (rpl-->0 && !queue.isEmpty());
        }


        return (hasLibrary.size() * c_lib) + (roads * c_road);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            System.out.println(String.valueOf(result));
        }

        scanner.close();
    }
}
