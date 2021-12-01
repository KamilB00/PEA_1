package pea.algorithms.BranchAndBound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchAndBound {

   public void travelingSalesman(int [][] adj){
        long start = System.nanoTime();
        int size = adj.length;
        int[][] matrix = Arrays.stream(adj).map(int[]::clone).toArray(int[][]::new);
        List<Integer> vertexes = new ArrayList<>();
        for(int i=0;i<size;i++){
            vertexes.add(i);
        }
        List<Integer> unvisited = vertexes.subList(1,size);
        int reductionCost = reduceMatrix(matrix);
        Node node = new Node(0,null,matrix,reductionCost,unvisited);
        SpaceStateTree tree = new SpaceStateTree(node);

        while(!(unvisited.isEmpty())){

            int unvisitedSize = unvisited.size();
            for(int i=0; i < unvisitedSize ; i++){

                int[][] tmp = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);

                markMatrix(tmp, node.getId(), unvisited.get(i));
                int cost =  reduceMatrix(tmp) + node.getCost() + matrix[node.getId()][unvisited.get(i)];

                List<Integer> newUnvisited = new ArrayList<>(unvisited);
                newUnvisited.remove(i);
                Node newNode = new Node(unvisited.get(i),node,tmp,cost,newUnvisited);
                tree.addNode(newNode);
            }

            tree.removeNode(node);
            node = tree.getMinLeaf();
            unvisited = node.getUnvisited();
            matrix = node.getReducedMatrix();
        }
       long finish = System.nanoTime();

       System.out.println(finish - start);
       //showPath(node);
    }

    void showPath(Node node){
        System.out.println("Cost: "+ node.getCost());
        int tab [] = new int[node.getReducedMatrix().length+1];
        for(int i=0;i<node.getReducedMatrix().length-1; i++){
            tab[node.getReducedMatrix().length-1-i] = node.id;
            node = node.parent;
        }
        tab[0] = 0;
        tab[node.getReducedMatrix().length] = 0;

        for(int i=0;i<node.getReducedMatrix().length+1;i++){
            System.out.print(tab[i]+"\t");
        }
        System.out.println();
    }

    int reduceRows(int [][] matrix){
        int reductionCost = 0;
        int size = matrix.length;

        for(int i =0; i<size;i++){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<size; j++){
                int value = matrix[i][j];
                if(value < min && value >= 0){
                    min = value;
                }
            }
            if(min != Integer.MAX_VALUE){
                reductionCost +=min;
            }
            for(int j =0; j<size; j++){
                int value = matrix[i][j];
                if(value > 0 && min < Integer.MAX_VALUE){
                    matrix[i][j] = value - min;
                }
            }
        }
        return reductionCost;
    }

    int reduceColumns(int [][] matrix){
        int reductionCost = 0;
        int size = matrix.length;

        for(int i =0; i<size;i++){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<size; j++){
                int value = matrix[j][i];
                if(value < min && value >= 0){
                    min = value;
                }
            }
            if(min != Integer.MAX_VALUE) {
                reductionCost += min;
            }
            for(int j =0; j<size; j++){
                int value = matrix[j][i];
                if(value > 0 && min < Integer.MAX_VALUE){
                    matrix[j][i] = value - min;
                }
            }
        }
        return reductionCost;
    }

    int reduceMatrix(int [][] matrix){
        return reduceRows(matrix) + reduceColumns(matrix);
    }

    void markMatrix(int [][] matrix, int row, int column){
        markRow(matrix, row);
        markColumn(matrix,column);
        matrix[column][0] = -1;
    }

    void markRow(int [][] matrix, int row){
        for(int i=0;i<matrix.length; i++){
            matrix[row][i] = -1;
        }
    }

    void markColumn(int [][] matrix, int column){
        for(int i=0;i<matrix.length; i++){
            matrix[i][column] = -1;
        }

    }

}
