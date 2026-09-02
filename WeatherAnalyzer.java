import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.lang.Math;

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException {
        // Main program logic

        //get filename
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, User...");
        System.out.println("Enter the data filename you wish to access: ");
        String inFile = sc.nextLine();

        String[][] data = readCSV(inFile);

        //get selection
        while(true) {
            System.out.println("\nChoose which data to display: ");
            System.out.println("1. High Temp");
            System.out.println("2. Low Temp");
            System.out.println("3. Humidity");
            System.out.println("4. Wind Speed");
            System.out.println("5. Precipitation");
            System.out.println("6. Quit program");

            System.out.print("\nEnter choice: ");
            int choice = sc.nextInt();

            if(choice == 6) {
                System.out.println("Exiting program");
                break;
            }

            String colName = "";
            int colIndex = choice;

            if(choice == 1) {
                colName = "High Temp";
            } else if(choice == 2) {
                colName = "Low Temp";
            } else if(choice == 3) {
                colName = "Humidity";
            } else if(choice == 4) {
                colName = "Wind Speed";
            } else if(choice == 5) {
                colName = "Precipitation";
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            //call other functions
            double[] extractedData = extractNumericColumn(data, colIndex);
            displayStatistics(extractedData, colName);
        }
        sc.close();   
    }

    public static String[][] readCSV(String filename) {
        // Read and parse CSV file

        try (Scanner inFS = new Scanner(new FileInputStream(filename))) {

            ArrayList<String[]> contents = new ArrayList<>();

            while(inFS.hasNextLine()) { //while there are elements within the scanner...
                String line = inFS.nextLine();
                String[] lineData = line.split(","); //split line into columns and save
                contents.add(lineData); //add CSV line to ArrayList
            }

            String[][] contentsData = new String[contents.size()][]; //2d array creation

            for(int i = 0; i < contents.size(); i++) { //convert ArrayList to 2d array
                contentsData[i] = contents.get(i);
            }

            return contentsData;

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename); //friendly error message
            return null;
        }

    }

    public static double[] extractNumericColumn(String[][] data, int columnIndex) {
        // Extract and validate numeric data from specified column

        ArrayList<Double> validValues = new ArrayList<>(); //ArrayList to hold specified data
        int invalidCount = 0; //holding invalid value count

        for(int i = 1; i < data.length; i++) { //loop skipping header row
            try {
                if(columnIndex < data[i].length) { //validating arguments
                    String value = data[i][columnIndex]; //converting column to string
                    double numericValue = Double.parseDouble(value); //converting column string to double
                    validValues.add(numericValue); //adding double to arraylist
                }
            }
            catch (NumberFormatException e) { //error handling for invalid data entries
                ++invalidCount; //inc invalid value count
            }
        }

        double[] result = new double[validValues.size()];
        for(int i = 0; i < validValues.size(); i++) {
            result[i] = validValues.get(i); //placing validated data into double array
        }

        System.out.println("\nRows skipped due to invalid data: " + invalidCount);
        return result;
    }

    public static void displayStatistics(double[] values, String columnName) {
        // Calculate and display all required statistics

        double[] sortedValues = new double[values.length];

        for(int i = 0; i < values.length; i++) { //copy array
            sortedValues[i] = values[i];
        }

        for(int i = 0; i < sortedValues.length; i++) { //sorting from min to max
            for(int j = 0; j < sortedValues.length -1 - i; j++) {
                if(sortedValues[j] > sortedValues[j+1]) {
                    double temp = sortedValues[j];
                    sortedValues[j] = sortedValues[j+1];
                    sortedValues[j+1] = temp;
                }
            }
        }

        double sum = 0;
        double avg;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for(int i = 0; i < values.length; i++) {
            sum += values[i];
            if(values[i] < min) { //minimum calculations
                min = values[i];
            }
            if(values[i] > max) { //maximum calculations
                max = values[i];
            }

        }
        avg = sum/values.length; //average calculation

        //median calculation
        double median;
        if(sortedValues.length%2 == 0) {
            double val1 = sortedValues[(sortedValues.length/2) -1];
            double val2 = sortedValues[sortedValues.length/2];
            median = (val1 + val2)/2;
        } else {
            median = sortedValues[sortedValues.length/2];
        }

        //standard deviation calculation
        double stDev;
        double sigma = 0;
        for(int i = 0; i < sortedValues.length; i++) {
            sigma += ((sortedValues[i] - avg)*(sortedValues[i] - avg));
        }
        double sample = sigma/(sortedValues.length -1);
        stDev = Math.sqrt(sample);

        //fix formatting
        String format;
        String unit;
        if(columnName.contains("Temp")) {
            format = "%.1f";
            unit = "°F";
        } else if(columnName.equals("Precipitation")) {
            format = "%.2f";
            unit = " in";
        } else if(columnName.equals("Humidity")) {
            format = "%.1f";
            unit = "%";
        } else if(columnName.equals("Wind Speed")) {
            format = "%.1f";
            unit = " mph";
        } else {
            format = "%.2f";
            unit = "";
        }

        System.out.printf("\nAverage " + columnName + " using " + sortedValues.length + " data points: " + format + "%s\n", avg, unit);
        System.out.printf("Minimum " + columnName + " using " + sortedValues.length + " data points: " + format + "%s\n", min, unit);
        System.out.printf("Maximum " + columnName + " using " + sortedValues.length + " data points: " + format + "%s\n", max, unit);
        System.out.printf("Median " + columnName + " using " + sortedValues.length + " data points: " + format + "%s\n", median, unit);
        System.out.printf("Standard deviation " + columnName + " using " + sortedValues.length + " data points: " + format + "%s\n", stDev, unit);
    }
}