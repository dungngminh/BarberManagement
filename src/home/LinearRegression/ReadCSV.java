package home.LinearRegression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
    public ArrayList<ArrayList<Double>> ReadFromCSV () throws FileNotFoundException {
        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Double> benefits = new ArrayList<>();
        String filePath = "src/complete.csv";
        File file = new File(filePath);
        Scanner inputStream;
        try {
            inputStream = new Scanner(file);
            double
                    cus = -1, price = -1 ;
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                cus = Double.valueOf(values[0].replaceAll("[^\\d]+", ""));
                price = Double.valueOf(values[1].replaceAll("[^\\d]+", ""));
                nums.add(cus);
                benefits.add(price);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<Double>> results = new ArrayList<>();
        results.add(nums); results.add(benefits);
        return results;
    }
}
