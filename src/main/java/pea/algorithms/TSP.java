package pea.algorithms;

import pea.graph.Graph;

public class TSP {

    int  elementsNumber;
    int [][] graph = Graph.getInstance().getGraph();
    int [] minCircuit;
    int minCircuitValue = Integer.MAX_VALUE;

    public TSP(int elementsNumber) {
        this.elementsNumber = elementsNumber;
        this.minCircuit = new int [(int)elementsNumber];
    }

    public void vertexesPermutation(int n, int[] elements) {

        if (n == 1) {

           isCircuitMin(elements);

        } else {
            for (int i = 0; i < n - 1; i++) {
                vertexesPermutation(n - 1, elements);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            vertexesPermutation(n - 1, elements);
        }
    }

    private void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }


    private void isCircuitMin(int[] permutations) {
        int sum = 0;
        int startingNode;

        startingNode = permutations[0];

        for(int i=0;i<permutations.length-1;i++){
            sum += graph[permutations[i]][permutations[i+1]];
        }
        sum += graph[permutations[permutations.length-1]][startingNode];

        if(sum < minCircuitValue){
            minCircuitValue = sum;
           for(int i=0;i<permutations.length;i++){
               minCircuit[i] = permutations[i];
           }
        }
    }


    public void showMinCircuit() {
            System.out.println("Shortest circuit value = "+ minCircuitValue);

        for (int i=0; i<elementsNumber; i++){
            System.out.print( minCircuit[i] + " ");
        }
        System.out.print(minCircuit[0]);
        System.out.println();
    }

}
