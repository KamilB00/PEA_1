package pea.algorithms;

public class TSP {


    int elementsNumber;
    int factorial;
    int[][] permutations;
    int index = 0;

    public TSP(int elementsNumber){
        this.elementsNumber = elementsNumber;
        this.factorial = possibleCircuitsNumber(elementsNumber);
        this.permutations = new int[factorial][0];
    }

    public void printAllRecursive(int n, int[] elements){
        if(n == 1){
            int [] temp = new int[elementsNumber];
            for(int i=0; i<elementsNumber; i++){
                temp[i] = elements[i];
            }
            addPermutation(temp, index++);

        } else {
            for(int i = 0; i< n-1; i++){
                printAllRecursive(n-1,elements);
                if(n%2 == 0){
                    swap(elements, i, n-1);
                }
                else{
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n-1, elements);
        }
    }

    private void swap(int[] input, int a, int b){
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private void addPermutation(int[] input, int index){
        permutations[index] = input;
    }

    public int possibleCircuitsNumber(int n){
        if(n<= 2){
            return n;
        }
        return n * possibleCircuitsNumber(n-1);
    }





}
