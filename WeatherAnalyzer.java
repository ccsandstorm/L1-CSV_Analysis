import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class WeatherAnalyzer {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        // Main program logic
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        readCSV("weather_data.csv");

    }

    public static ___________ readCSV(String filename) {
        // Read and parse CSV file
        fileByteStream = new FileInputStream(filename);
        inFS = new Scanner(fileByteStream);
        while(inFS.hasNext()) { //while there are elements within the scanner...

        }
    }

    public static _______ extractNumericColumn(_____ data, int columnIndex) {
        // Extract and validate numeric data from specified column
    }

    public static void displayStatistics(double[] values, String columnName) {
        // Calculate and display all required statistics
    }
}