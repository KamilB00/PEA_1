package pea.algorithms.BranchAndBound;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SpaceStateTree {

    Map<Node, Integer> nodes = new HashMap<>();

    public SpaceStateTree(Node node) {
        nodes.put(node, node.cost);
    }

    public void addNode(Node node) {
        nodes.put(node, node.cost);
    }

    public Node getMinLeaf() {
        return nodes.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).get();
    }

    public void removeNode(Node node){
        nodes.remove(node);
    }
}
