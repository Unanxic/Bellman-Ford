//Mpatila Dimitra 4127
package BellmanFord;

import java.util.Arrays;

public class BellmanFord {
    static int[] distances;
    private int[] ancestors;
    private int source;
    private Graph graph;

    public BellmanFord(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        int vertices = graph.getNodes();
        distances = new int[vertices];
        ancestors = new int[vertices];
    }

    public void shortestPath(){
        int nodes = graph.getNodes();
        for (int i=0; i < nodes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        Arrays.fill(ancestors, -1);
        distances[source] = 0;
        graph.relaxAllNodes(distances, ancestors);
        if (graph.detectNegativeCycle(graph.getEdges())) {
            return;
        }
    }

    public int[] getAncestors() {
        return ancestors;
    }
}
