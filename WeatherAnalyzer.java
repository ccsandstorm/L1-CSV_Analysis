import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays; // AI Recommendation: Added for standard array operations

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException {
        // Main program logic

        //get filename
        Scanner sc = new Scanner(System.in);
        String inFile = "";


        //AI Recommendation: Support command-line argument reading as required by grade C specs
        if(args.length > 0) {
            inFile = args[0];
        } else {
            System.out.println("Hello, User...");
            System.out.println("Enter the data filename you wish to access: ");
            inFile = sc.nextLine();
        }
        //End of AI recommendation block


        String[][] data = readCSV(inFile);


        //AI Recommendation: Handle null return from readCSV when a file is not found
        if (data == null || data.length <= 1) {
            System.out.println("Could not load dataset or dataset is empty. Program terminating.");
            sc.close();
            return;
        }
        //end of AI rec block


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


            //AI Rec: Prevent InputMismatchException if user enters non-int input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                sc.next();
                continue;
            }
            //end of AI block


            int choice = sc.nextInt();

            if(choice == 6) {
                System.out.println("Exiting program");
                break;
            }

            String colName = "";

            //AI: fixed off-by-one index bug (choice 1 maps to csv index 1 assuming column 0 is Date)
            int colIndex = choice;

            if(choice == 1) {
                colName = "High Temp";
                colIndex = 1;
            } else if(choice == 2) {
                colName = "Low Temp";
                colIndex = 2;
            } else if(choice == 3) {
                colName = "Humidity";
                colIndex = 3;
            } else if(choice == 4) {
                colName = "Wind Speed";
                colIndex = 4;
            } else if(choice == 5) {
                colName = "Precipitation";
                colIndex = 5;
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
        } catch (Exception e) {
            // AI Recommendation: Catch general IOExceptions for robust error handling
            System.out.println("Error reading file: " + e.getMessage());
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

        // AI Recommendation: Check for empty dataset to avoid division by zero (NaN results)
        if (values == null || values.length == 0) {
            System.out.println("No valid numeric data found for column: " + columnName);
            return;
        }
        //end of AI

        // AI Recommendation: Replaced bubble sort with built-in Arrays utilities
        double[] sortedValues = Arrays.copyOf(values, values.length);
        Arrays.sort(sortedValues);
        //end of AI

        double sum = 0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            if (values[i] < min) {
                min = values[i];
            }
            if (values[i] > max) {
                max = values[i];
            }
        }
        
        double avg = sum / values.length;

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
        double stDev = 0;
        if (sortedValues.length > 1) { // AI Recommendation: Ensure sample count > 1 for sample standard deviation
            double sigma = 0;
            for (int i = 0; i < sortedValues.length; i++) {
                sigma += Math.pow((sortedValues[i] - avg), 2);
            }
            double sample = sigma / (sortedValues.length - 1);
            stDev = Math.sqrt(sample);
        }

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