package pea.algorithms;

import java.util.*;

public class TSPHeldKarp {

    private static int INFINITY = Integer.MAX_VALUE;

    private static class EndNodeWithSetOfNodesToVisit {
        int currentVertex;
        Set<Integer> vertexSet;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EndNodeWithSetOfNodesToVisit index = (EndNodeWithSetOfNodesToVisit) o;

            if (currentVertex != index.currentVertex) return false;
            return Objects.equals(vertexSet, index.vertexSet);
        }

        @Override
        public int hashCode() {
            int result = currentVertex;
            result = 22 * result + (vertexSet != null ? vertexSet.hashCode() : 0);
            return result;
        }

        private static EndNodeWithSetOfNodesToVisit createIndex(int vertex, Set<Integer> vertexSet) {
            EndNodeWithSetOfNodesToVisit i = new EndNodeWithSetOfNodesToVisit();
            i.currentVertex = vertex;
            i.vertexSet = vertexSet;
            return i;
        }
    }

    private static class SetSizeComparator implements Comparator<Set<Integer>> {
        @Override
        public int compare(Set<Integer> o1, Set<Integer> o2) {
            return o1.size() - o2.size();
        }
    }

    public int minCost(int[][] graph) {

        Map<EndNodeWithSetOfNodesToVisit, Integer> mapOfEndNodeWithSetOfNotesToVisitAndMinPath = new HashMap<>();
        Map<EndNodeWithSetOfNodesToVisit, Integer> mapOfIndexAndParent = new HashMap<>();

        List<Set<Integer>> allSets = generateCombination(graph.length - 1);

        for (Set<Integer> set : allSets) {
            for (int currentVertex = 1; currentVertex < graph.length; currentVertex++) {
                if (set.contains(currentVertex)) {
                    continue;
                }
                EndNodeWithSetOfNodesToVisit index = EndNodeWithSetOfNodesToVisit.createIndex(currentVertex, set);
                int minCost = INFINITY;
                int minPrevVertex = 0;

                Set<Integer> copySet = new HashSet<>(set);
                for (int prevVertex : set) {
                    int edgeWeight = graph[prevVertex][currentVertex] + getEdgeWeight(copySet, prevVertex, mapOfEndNodeWithSetOfNotesToVisitAndMinPath);
                    if (edgeWeight < minCost) {
                        minCost = edgeWeight;
                        minPrevVertex = prevVertex;
                    }
                }

                if (set.size() == 0) {
                    minCost = graph[0][currentVertex];
                }
                mapOfEndNodeWithSetOfNotesToVisitAndMinPath.put(index, minCost);
                mapOfIndexAndParent.put(index, minPrevVertex);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < graph.length; i++) {
            set.add(i);
        }
        int min = Integer.MAX_VALUE;
        int prevVertex = -1;


        Set<Integer> copySet = new HashSet<>(set);
        for (int i : set) {
            int cost = graph[i][0] + getEdgeWeight(copySet, i, mapOfEndNodeWithSetOfNotesToVisitAndMinPath);
            if (cost < min) {
                min = cost;
                prevVertex = i;
            }
        }

        mapOfIndexAndParent.put(EndNodeWithSetOfNodesToVisit.createIndex(0, set), prevVertex);
        printTour(mapOfIndexAndParent, graph.length);
        return min;
    }

    private void printTour(Map<EndNodeWithSetOfNodesToVisit, Integer> parent, int totalVertices) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalVertices; i++) {
            set.add(i);
        }
        Integer start = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (true) {
            stack.push(start);
            set.remove(start);
            start = parent.get(EndNodeWithSetOfNodesToVisit.createIndex(start, set));
            if (start == null) {
                break;
            }
        }


        System.out.println("\nShortest circuit: ");
        stack.forEach(v -> System.out.print(v.toString()+ " "));
        System.out.println();
    }

    private int getEdgeWeight(Set<Integer> set, int prevVertex, Map<EndNodeWithSetOfNodesToVisit, Integer> mapOfEndNodeWithSetOfNotesToVisitAndMinPath) {
        set.remove(prevVertex);
        EndNodeWithSetOfNodesToVisit endNodeWithSetOfNodesToVisit = EndNodeWithSetOfNodesToVisit.createIndex(prevVertex, set);
        int edgeWeight = mapOfEndNodeWithSetOfNotesToVisitAndMinPath.get(endNodeWithSetOfNodesToVisit);
        set.add(prevVertex);
        return edgeWeight;
    }

    private List<Set<Integer>> generateCombination(int n) {
        int [] input = new int[n];
        for (int i = 0; i < input.length; i++) {
            input[i] = i + 1;
        }
        List<Set<Integer>> allSets = new ArrayList<>();
        int [] result = new int[input.length];
        generateCombination(input, 0, 0, allSets, result);
        Collections.sort(allSets, new SetSizeComparator());
        return allSets;
    }

    private void generateCombination(int [] input, int start, int pos, List<Set<Integer>> allSets, int []result) {
        if (pos == input.length) {
            return;
        }
        Set<Integer> set = createSet(result, pos);
        allSets.add(set);
        for (int i = start; i < input.length; i++) {
            result[pos] = input[i];
            generateCombination(input, i + 1, pos + 1, allSets, result);
        }
    }

    private static Set<Integer> createSet(int [] input, int pos) {
        if (pos == 0) {
            return new HashSet<>();
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pos; i++) {
            set.add(input[i]);
        }
        return set;
    }
}
