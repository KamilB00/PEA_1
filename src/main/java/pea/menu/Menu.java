package pea.menu;

import pea.algorithms.TSP;
import pea.file.FileHandler;
import pea.graph.Graph;
import pea.graph.GraphGenerator;
import java.io.IOException;
import java.util.Scanner;


public class Menu {

    public Menu() throws IOException {
        mainWindow();
    }

    public void mainWindow() throws IOException {

        switch (choice()) {
            case 1: {
                String filePath = "/Users/kamilbonkowski/Downloads/instances/m12.atsp";
                Graph.getInstance().setGraph(GraphGenerator.getMatrixFromFile(filePath));
                mainWindow();
                break;
            }
            case 2: {
                System.out.println("Number of vertexes: ");
                Scanner scanner = new Scanner(System.in);
                int numberOfVertexes = scanner.nextInt();
                Graph.getInstance().setGraph(GraphGenerator.generateMatrix(numberOfVertexes));
                mainWindow();
                break;
            }
            case 3: {
                int size = Graph.getInstance().graph.length;
                int [] vertexArray = Graph.getInstance().generateVertexes(size);

               long sum = 0;

                for(int i = 0; i< 100 ; i++) {
                    long start = System.nanoTime();
                    TSP tsp = new TSP(size);
                    tsp.vertexesPermutation(size, vertexArray);
                    //tsp.showMinCircuit();
                    long finish = System.nanoTime();
                    sum+= finish - start;
                }

                FileHandler.executionTime.add(sum/100);
                FileHandler.graphSize.add(size);
                FileHandler.convertToCSV();

                mainWindow();
                break;
            }
            case 4: {
                Graph.getInstance().show();
                mainWindow();
                break;
            }
            case 0: {
                System.exit(1);
                break;
            }
        }
    }

    private int choice() {
        return choiceMessage(toString(), 5);
    }

    public int choiceMessage(String content, int choiceLimit) {
        int choice;
        System.out.println(content);

        Scanner scanner = new Scanner(System.in);

        do {
           choice  = scanner.nextInt();
        }while(choice < 0 || choice > choiceLimit);

        return choice;
    }

    @Override
    public String toString() {
        String menu;
        menu =  "1. Read graph from file \n" +
                "2. Generate graph \n" +
                "3. Run TSP Brute-Force \n" +
                "4. Show current graph \n" +
                "0. Exit";
        return menu;
    }
}

