package pea.graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Graph {
    private static Graph INSTANCE;
    public int [][] graph;

    private Graph() {
    }

    public static Graph getInstance() {
       if (INSTANCE == null){
           INSTANCE = new Graph();
       }
       return INSTANCE;
    }
    public int [] generateVertexes(int size){
        int [] vertexArray = new int[size];
        for(int i=0; i<size; i++){
            vertexArray[i] = i;
        }
        return vertexArray;
    }

    public void show(){
        for (int [] row: graph){
            for(int  elem: row){
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }


}
