package pea.menu;




public class Menu {


    @Override
    public String toString() {
        String menu;
        menu =  "1. Read graph from file \n" +
                "2. Generate graph \n" +
                "3. Run TSP Brute-Force \n" +
                "4. Exit";
        return menu;
    }
}

