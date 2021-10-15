package pea.graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Graph {
    private static Graph INSTANCE;
    public int[][] graph;

    private Graph() {
    }

    public static Graph getInstance() {
       if (INSTANCE == null){
           INSTANCE = new Graph();
       }
       return INSTANCE;
    }

    public void show(){
        for (int [] row: graph){
            for(int elem: row){
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }


}
