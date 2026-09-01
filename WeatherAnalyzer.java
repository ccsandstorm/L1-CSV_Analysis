import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException {
        // Main program logic

        String[][] data = readCSV("weather_data.csv");

        //need to ask what data we want extracted!!!
        double[] extractedData = extractNumericColumn(data, 1);

    }

    public static String[][] readCSV(String filename) throws IOException {
        // Read and parse CSV file

        FileInputStream fileByteStream = new FileInputStream(filename); //open file
        Scanner inFS = new Scanner(fileByteStream); 

        ArrayList<String[]> contents = new ArrayList<>();

        while(inFS.hasNextLine()) { //while there are elements within the scanner...
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

        ArrayList<Double> validValues = new ArrayList<>(); //ArrayList to hold specified data
        for(int i = 1; i < data.length; i++) { //loop skipping header row
            try {
                if(columnIndex < data[i].length) { //validating arguments
                    String value = data[i][columnIndex]; //converting column to string
                    double numericValue = Double.parseDouble(value); //converting column string to double
                    validValues.add(numericValue); //adding double to arraylist
                }
            }
            catch (NumberFormatException e) { //error handling for invalid data entries
                //do I need to put something here or can I just skip?
            }
        }

        double[] result = new double[validValues.size()];
        for(int i = 0; i < validValues.size(); i++) {
            result[i] = validValues.get(i); //placing validated data into double array
        }

        return result;
    }

    public static void displayStatistics(double[] values, String columnName) {
        // Calculate and display all required statistics

        //arithmetic calculations
        //display stats
    }
}