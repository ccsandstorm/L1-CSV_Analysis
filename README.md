# L1-CSV_Analysis
For this lab, create a Java program that reads data from a Comma Separated Value (CSV) file and performs basic statistical analysis on a specified numeric column of daily weather data for a city.

-----------------------------------------

Overview
For this lab, create a Java program that reads data from a Comma Separated Value (CSV) file and performs basic statistical analysis on a specified numeric column of daily weather data for a city.

Learning Objectives

    Read and parse CSV files in Java
    Handle file I/O exceptions properly
    Convert string data to numeric types
    Calculate basic statistics (average, minimum, maximum)
    Practice good programming practices with proper error handling

Grade Levels and Specifications

All the following specifications must be met to get a C:
Grade C (70 points):

    Program successfully reads the provided weather_data.csv file from the command line (java WeatherAnalyzer weather_data.csv)
    Correctly parses CSV data (handles commas as delimiters)
    Skips the header row when processing data
    Converts at least one numeric column to double values
    Handles FileNotFoundException appropriately with user-friendly error message
    Uses proper try-catch blocks around file operations
    Calculates and displays the average of a numeric column
    Program compiles without errors
    Program runs to completion without crashing

Grade B (80 points):
Complete all the requirements for a C and

    Calculates and displays average, minimum, and maximum values
    Displays total count of data points processed
    Formats numeric output appropriately (e.g., temperature to 1 decimal place with a °F/°C label and precipitation to 2 decimal places with “in”/“mm”)
    Prompts user to select which column from the CSV file to analyze (e.g., high temp, low temp, humidity, wind speed, precipitation)
    Identifies and skips rows with non-numeric data in the selected column
    Reports how many rows were skipped due to invalid data
    The program works correctly even with some invalid data present

Grade A (90 points)
Complete all the requirements for a B and

    Handles multiple exception types (IOException, NumberFormatException, etc.)
    Works with any properly formatted CSV file (not just the provided sample data)
    Code is organized into appropriate methods with clear responsibilities
    Methods have appropriate parameters and return types
    Follows Java naming conventions and coding standards.
    Calculates additional statistics: median and standard deviation
    Shows statistics with appropriate precision (2-3 decimal places)

Extra Credit

    Automatically detects and displays available numeric columns in the CSV file (2 points)
    Handles CSV files with different numbers of columns (2 points)
    Implements data filtering (e.g., analyze only days above/below a threshold, such as “days over 85°F”) (2 points)
    Provides option to save results to an output file and writes the output to a file (2 points)
    Implements proper object-oriented design (consider creating a WeatherData class) (2 points)
    Includes unit tests for key methods (at least 5 test methods) (2 points)

