/*
Programming Project 1 - Command Line Arguments with Error Handling.
Learning Objectives:

Implement a Java program that accepts command line arguments with appropriate error handling.
Write code that reads input data from a CSV file to construct a multidimensional array with appropriate error handling.
Demonstrate an understanding of functional decomposition by implementing separate methods for file I/O and array processing.

The starter code is provided for this project. You can download the file here - Project1.java Download Project1.java 

You will implement the methods to provide the functionality described in this document and in the comments in the starter code. 

All classes will be graded using a JUnit test suite, so the method headings for all methods need to match the test class exactly. 
Therefore, you are not to change any method headers or package statements.

Your source code file name should be Project1.java.  It must be located in the cmsc256 package as indicated in the starter code file.

 A sample input file is provided but your code should be flexible enough to run with a different data file. You can download the input file here - 
 500_Person_Age_Height_Weight.csv Download 500_Person_Age_Height_Weight.csv 

The Project1 program is to:

Read in the input file creating a 2-dimensional String array from the data contained in the lines of the file using the designated methods to provide the functionality
Implement the functionality listed for each method
Make sure your program is well documented - including the comment block header at the top of all of the source code files with your name, the course number and name, the project name, the program purpose. 
Be sure to include appropriate comments throughout your code, choose meaningful identifiers, and use indentation as shown in your textbook and in class.
*/
package cmsc256;  // do not remove or comment out this statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *  CMSC 256
 *  Project 1
 *  Miller, Logan
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) throws IOException {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
        String fileName = checkArgs(args);
        File file = getFile(fileName);
        String[][] fileInfo = readFile(file, 0);
        for(int i = 0; i < fileInfo.length; i++) {
            for (int j = 0; j < fileInfo[0].length; j++) {
                System.out.print(fileInfo[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public static String checkArgs(String[] argv) {
        String fileName;
        if(argv.length == 1){
            fileName = argv[0];
            return fileName;
        }
        else{
            fileName = promptForFileName();
            return fileName;
        }

    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public static String promptForFileName() {
      System.out.println("Enter a file name: ");
      Scanner scan = new Scanner(System.in);
      String fileName = scan.nextLine();
      scan.close();
      return fileName;
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @throws java.io.FileNotFoundException
     */
    public static File getFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return file;
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
    public static String[][] readFile(File file, int numRecords) throws IOException {
        Scanner scan = new Scanner(file);
        ArrayList<String> temp1 = new ArrayList<>();
        while(scan.hasNext()){
                temp1.add(scan.nextLine());
        }
        temp1.remove(0);
        String[][] fileInfo = new String[numRecords][3];
        for(int i = 0; i < numRecords; i++){
            String[] temp = temp1.get(i).split(",");
            for(int j = 0; j < 3; j++){
                if(!temp[j].equals(""))
                    fileInfo[i][j] = temp[j];
                else
                    throw new IOException();
            }
        }

        return fileInfo;
    }


    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public static int findTallest(String[][] db) {
        int max = 0;
        for(int i = 0; i < db.length; i++){ //might cause a problem
            if(Integer.parseInt(db[i][1]) > max){
                max = Integer.parseInt(db[i][1]);
            }
        }
        return max;
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public static String[] findLightestRecord(String[][] db) {
        int min = Integer.parseInt(db[0][2]);
        int index = 0;
        for(int i = 0;  i < db.length; i++){ //might cause a problem
            if(Integer.parseInt(db[i][2]) < min) {
                index = i;
            }
        }
        String[] output = new String[3];
        for(int i = 0; i < 3; i++){
            output[i] = db[index][i];
        }
        return output;
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
    public static double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
        double total = 0;
        double count = 0;
        for(int i = 0; i < db.length; i++){ //might cause error
            if(Integer.parseInt(db[i][0]) >= lowerBound && Integer.parseInt(db[i][0]) <= upperBound){
                total += Integer.parseInt(db[i][1]);
                count++;
            }
        }
        double average = total/count;
        if(!(count == 0))
        return average;
        else
            return 0.0;
    }
}
