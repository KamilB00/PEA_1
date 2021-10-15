package pea.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphGenerator {

    public static int[][] generateMatrix(int size) {

        int min = 10;
        int max = 100;

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = (int) Math.floor(Math.random() * (max - min + 1) + min);
                }
            }
        }
        return matrix;
    }

    public static int[][] getMatrixFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        int size = scanner.nextInt();
        int [][] matrix = new int[size][size];

        for(int i = 0; i<size; i++){
            for(int j = 0; j< size; j++){
                if(scanner.hasNextInt()){
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }
        return matrix;
    }
}
