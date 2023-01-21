package BellmanFord;

import java.util.stream.IntStream;

/*
 * Ερώτηση: Τι θα συμβεί αν αλλάξουμε την τιμή από την ακμή E στην D σε -13?
 * Απάντηση: Θα έχουμε κύκλο με negative sum of edge weights, δηλαδή ένα negative cycle.
 * A cycle in a graph with a negative sum of edge weights is called a
 * negative weight cycle. This means that traversing the edges in the
 * cycle will lead to a decrease in the overall weight of the path.
 * An example of this can be seen in a graph containing vertices A, B, and C,
 * connected by edges with weights of -1, 2, and -1. Starting at vertex A and
 * following the edges to B, then C, and back to A results in a total weight of -1 + 2 + (-1) = 0,
 * which is an example of a negative weight cycle. Bellman Ford algorithm does not work with graphs
 * that contain negative weight cycles as it assumes that edge weights are non-negative. Negative
 * weight cycles can cause the algorithm to produce incorrect results or enter an infinite loop.
 * However, if the graph does not have
 * negative weight cycles, Bellman Ford algorithm can detect and find the shortest path.
 */

public class Main {

    /**
     * This code creates a graph with 5 vertices and edges between
     * them, creates a BellmanFord object with that graph and source vertex 0,
     * calculates the shortest path, and
     * finally calls the method printShortestPath to print
     * the shortest path from vertex 0 to vertex 3.
     * @param args
     */
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, -1); graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3); graph.addEdge(1, 4, 2); graph.addEdge(1, 3, 2);
        graph.addEdge(3, 2, 5); graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);
        BellmanFord bellmanFord = new BellmanFord(graph, 0);
        bellmanFord.shortestPath();
        int[] ancestors = bellmanFord.getAncestors();
        graph.printShortestPath(ancestors, 0, 3);
        printAllShortestPaths(graph, ancestors);
    }

    public static void printAllShortestPaths(Graph graph, int[] ancestors) {
        IntStream.range(0, graph.getNodes())
                .forEach(node -> graph.printAllShortestPathsForNodeX(ancestors, node));
    }



}