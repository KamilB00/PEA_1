package pea.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<Integer> graphSize = new ArrayList<>();
    public static List<Long> executionTime = new ArrayList<>();

    public static void convertToCSV() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("tsp_result.csv", true));

       StringBuilder output = new StringBuilder();

        for (int i=0; i< executionTime.size(); i++){
        output.append(graphSize.get(i).toString()).append(";").append(executionTime.get(i).toString()).append("\n");
        }

        System.out.println(output);

        writer.write(String.valueOf(output));
    }

}
