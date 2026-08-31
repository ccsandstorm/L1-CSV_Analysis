import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException {
        // Main program logic
        readCSV("weather_data.csv");

    }

    public static String [][] readCSV(String filename) throws IOException {
        // Read and parse CSV file

        FileInputStream fileByteStream = new FileInputStream(filename); //open file
        Scanner inFS = new Scanner(fileByteStream); 

        while(inFS.hasNextLine()) { //while there are elements within the scanner...
            //read each line of the CSV
            System.out.println(inFS.nextLine());
        }
        //split line into columns
        //store the rows (String [])
        //store the rows (ArrayList<String[]>)
        //convert ArrayList -> String[][] data

        inFS.close();
        return null;
    }

    public static double[] extractNumericColumn(String[][] data, int columnIndex) {
        // Extract and validate numeric data from specified column

        //look at the specified column using int columnIndex
        //go through every row
        //get the value fro, that column
        //convert from String -> double
        //validate data
        //put valid numbers into a double[]
        //return double[] values

        return null;
    }

    public static void displayStatistics(double[] values, String columnName) {
        // Calculate and display all required statistics

        //arithmetic calculations
        //display stats
    }
}