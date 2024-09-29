package cmsc256;  // do not remove or comment out this statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 1
 *  [your last name], [your first name]
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {

    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
      
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {

    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {

    }

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
    }
}
