package pea.algorithms.BranchAndBound;

import java.util.List;

public class Node {

    int id;
    Node parent;
    int[][] reducedMatrix;
    int cost;
    List<Integer> unvisited;

    public Node(int id, Node parent, int[][] reducedMatrix, int cost, List<Integer> unvisited){
        this.id = id;
        this.parent = parent;
        this.reducedMatrix = reducedMatrix;
        this.cost = cost;
        this.unvisited = unvisited;
    }

    public int getId() {
        return id;
    }

    public Node getParent() {
        return parent;
    }

    public int[][] getReducedMatrix() {
        return reducedMatrix;
    }

    public int getCost() {
        return cost;
    }

    public List<Integer> getUnvisited() {
        return unvisited;
    }
}
