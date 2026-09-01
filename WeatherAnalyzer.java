import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException {
        // Main program logic
        String[][] data = readCSV("weather_data.csv");

    }

    public static String[][] readCSV(String filename) throws IOException {
        // Read and parse CSV file

        FileInputStream fileByteStream = new FileInputStream(filename); //open file
        Scanner inFS = new Scanner(fileByteStream); 

        ArrayList<String[]> contents = new ArrayList<>();

        while(inFS.hasNextLine()) { //while there are elements within the scanner...
            // System.out.println(inFS.nextLine()); //test - print each line of the CSV
            String line = inFS.nextLine();
            String[] lineData = line.split(","); //split line into columns and save
            contents.add(lineData); //add CSV line to ArrayList

        }

        inFS.close(); //close file

        String[][] contentsData = new String[contents.size()][]; //2d array creation

        for(int i = 0; i < contents.size(); i++) { //convert ArrayList to 2d array
            contentsData[i] = contents.get(i);
        }

        return contentsData;
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