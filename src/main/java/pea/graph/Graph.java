package pea.graph;

public class Graph {
    private static Graph graphInstance;
    public int[][] graph;

    private Graph(int[][] graph) {
        this.graph = graph;
    }

    public static Graph getInstance(int[][] graph) {
        if (graphInstance == null) {
            graphInstance = new Graph(graph);
        }
        return graphInstance;
    }


}
