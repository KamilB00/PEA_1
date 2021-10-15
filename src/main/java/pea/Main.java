package pea;

import pea.graph.Graph;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Graph graphGenerator = new Graph();

       int [][] ar = graphGenerator.getMatrixFromFile("/Users/kamilbonkowski/Downloads/instances/tsp_test.txt");
       int [][] ar2 = graphGenerator.generateMatrix(9);

       for (int [] a: ar2){
          for(int elem: a){
              System.out.print(elem + " ");
          }
           System.out.println();
       }


    }
}
