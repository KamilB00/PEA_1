package pea.menu;

import pea.algorithms.TSP;
import pea.graph.Graph;
import pea.graph.GraphGenerator;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Menu {

    public Menu() throws FileNotFoundException {
        mainWindow();
    }

    public void mainWindow() throws FileNotFoundException {

        switch (choice()) {
            case 1: {
                String filePath = "/Users/kamilbonkowski/Downloads/instances/tsp_test.txt";
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
            }
            case 3: {
                int[] vertexes = {0,1,2};
                TSP tsp = new TSP(vertexes.length);
                tsp.printAllRecursive(vertexes.length,vertexes);
                break;
            }
            case 4: {
                Graph.getInstance().show();
                mainWindow();
                break;
            }
            case 0: {
                System.exit(1);
            }
        }
    }

    public int choice() {
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

