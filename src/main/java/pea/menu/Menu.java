package pea.menu;

import pea.algorithms.BranchAndBound.BranchAndBound;
import pea.algorithms.BruteForce.TSP;
import pea.algorithms.HeldKarp.TSPHeldKarp;
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
                System.out.print("Enter file name: ");
                Scanner scan = new Scanner(System.in);
                String path = scan.nextLine();
                String filePath = "/Users/kamilbonkowski/Downloads/instancje/"+path;
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

                long start = System.nanoTime();
                TSP tsp = new TSP(size);
                tsp.vertexesPermutation(size, vertexArray);
                long finish = System.nanoTime();
                System.out.println(finish-start);
                tsp.showMinCircuit();

                mainWindow();
                break;
            }
            case 4:
            {
                long start = System.nanoTime();
                TSPHeldKarp dp = new TSPHeldKarp();
                long finish = System.nanoTime();
                System.out.println(finish-start);

                System.out.println(dp.minCost(Graph.getInstance().getGraph()));
                mainWindow();
                break;
            }
            case 5: {
                int[][] graph = Graph.getInstance().getGraph();
                BranchAndBound bNb = new BranchAndBound();
                bNb.travelingSalesman(graph);
                mainWindow();
                break;
            }
            case 6: {
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
        return choiceMessage(toString(), 6);
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
                "4. Run TSP Held-Karp \n"+
                "5. Run BranchAndBound \n"+
                "6. Show current graph \n" +
                "0. Exit";
        return menu;
    }
}

