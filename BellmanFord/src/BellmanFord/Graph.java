//Mpatila Dimitra 4127
package BellmanFord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Graph {
    private int nodes;
    private List<Edge> edges;

    public Graph(int nodes) {
        this.nodes = nodes;
        edges = new ArrayList();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public int getNodes() {
        return nodes;
    }

    public void relaxAllNodes(int[] distances, int[] predecessors) {
        int vertices = this.nodes;
        List<Edge> edges = this.edges;
        IntStream.range(1, vertices)
                .forEach(i -> edges.stream()
                        .forEach(edge -> {
                            int u = edge.getSource();
                            int v = edge.getDestination();
                            int weight = edge.getWeight();
                            if (distances[v] > distances[u] + weight) {
                                distances[v] = distances[u] + weight;
                                predecessors[v] = u;
                            }
                        }));
    }


    /**
     * A variable iteration is added to keep track of the number of iterations and a variable
     * isRelaxed is used to keep track of whether a relaxation occurred during the current iteration.
     * The while loop continues to run as long as either the number of iterations is less than the number
     * of vertices or a relaxation occurred during the current iteration. If the while loop completes the number
     * of iterations equal to the number of vertices and isRelaxed is true, then a negative cycle was detected
     * and we print the corresponding error message and return true. Otherwise, return false.
     * @return
     */
    public boolean detectNegativeCycle(List<Edge> edges) {
        return edges.stream()
                .anyMatch(edge -> {
                    int u = edge.getSource();
                    int v = edge.getDestination();
                    int weight = edge.getWeight();
                    if (BellmanFord.distances[v] > BellmanFord.distances[u] + weight) {
                        System.out.println("Negative cycle detected!");
                        return true;
                    }
                    return false;
                });
    }


    /**
     * This method takes in three arguments: an array of predecessors, the source vertex,
     * and the destination vertex. It starts at the destination vertex and follows
     * the predecessors array to trace the shortest path back to the source vertex.
     * It stores the path in an ArrayList and reverses the order of the path so it goes
     * from source to destination. If there is no path from the source to the destination,
     * it will print "No path from source to destination".
     * The printShortestPath() method can be called by passing in the predecessors
     * array from the BellmanFord class, the source vertex, and the destination vertex.
     *
     * You can use this method as follows:
     * @param ancestors
     * @param source
     * @param destination
     */
    public void printShortestPath(int[] ancestors, int source, int destination) {
        System.out.println("\nShortest path " + source + " -> " + destination);
        if (ancestors[destination] == -1) {
            if (destination != source) {
                System.out.println("No path from source to destination");
            }
            return;
        }
        List<Integer> path = new ArrayList();
        int current = destination;
        while (current != source) {
            try {
                path.add(current);
                current = ancestors[current];
            } catch (Exception e) {
                System.out.println("No path from " + source + " to " + destination);
                return;
            }
        }
        path.add(source);
        Collections.reverse(path);
        System.out.println("Shortest path from source to destination: " + path);
    }


    /**
     *  ancestors: the product of the Bellman-Ford algorithm,
     *  Iterate through all the nodes in the graph,
     *  - ignore the root node - and print the shortest path
     *  from the root node to the current node
     * @param ancestors
     * @param source
     */
    public void printAllShortestPathsForNodeX(int[] ancestors, int source) {
        IntStream.range(0, ancestors.length)
                .filter(i -> i != source)
                .forEach(i -> {
                    System.out.println("\nShortest path " + source + " -> " + i);
                    if (ancestors[i] == -1) {
                        System.out.println("No path from source to node " + i);
                        return;
                    }
                    List<Integer> path = new ArrayList<>();
                    int current = i;
                    while (current != source) {
                        try {
                            path.add(current);
                            current = ancestors[current];
                        } catch (Exception e) {
                            System.out.println("No path from " + source + " to " + i);
                            return;
                        }
                    }
                    path.add(source);
                    Collections.reverse(path);
                    System.out.println("Shortest path from source to vertex " + i + ": " + path);
                });
    }

    public List<Edge> getEdges() {
        return edges;
    }
}