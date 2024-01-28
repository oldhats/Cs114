package edu.njit.cs114;
import java.util.Arrays;
/**
 * Author: Marquise Phillips
 * Date created: 01/19/2024
 */
public class ArrayStudy {
    public static double[][] manipulate(double[][] a, double fraction) {
// Perform step (a)
        double[][] a1 = extract(a, fraction);
        System.out.println("Content of array after step (a) for fraction " +
                fraction + " :");
        printArray(a1);
// Perform stp (b)
        double[][] a2 = replace(a1);
        System.out.println("Content of array after step (b) for fraction " +
                fraction + " :");
        printArray(a2);
        return a2;
    }

    // prints 2D array
    private static void printArray(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(String.format("%.2f", a[i][j]) + ",");
            }
            System.out.println();
        }
    }

    private static double[][] extract(double[][] a, double fraction) {
        if (a.length == 0) {
            return a;
        }
        int fractionNumCols = 0;
        int noOfNegativesInRow = 0;
        int noOfRowsRemoved = 0;
        boolean[] removeRow = new boolean[a.length];
// Loop through each row of a to check if number of negatives is at least halfCol
        for (int i = 0; i < a.length; i++) {
            fractionNumCols = (int) Math.ceil(a[i].length * fraction);
            noOfNegativesInRow = 0;
/**
 * To be completed
 * Loop through each column of the i-th row to update
 noOfNegativesInRow
 * If noOfNegativesInRow is at least fractionNumCols, set
 removeRow[i] to true
 * and also update noOfRowsRemoved
 */
//Loop through 'a' array and checks if the current index is a negative number
//also checks if negative numbers are more than half of a row and flags that row for removal if so
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] < 0) {
                    noOfNegativesInRow++;
                }
                if (noOfNegativesInRow == fractionNumCols || noOfNegativesInRow > fractionNumCols) {
                    removeRow[i] = true;
                    noOfRowsRemoved++;
                    break;
                }
            }
        }
// allocate a new array
        double[][] b = new double[a.length - noOfRowsRemoved][];
        int validRowIndex = 0;
/**
 * To be completed
 * Loop through each row i of a and if removeRow[i] is false, add it to b
 * using Arrays.copyOf() function
 */
        for (int i = 0; i < a.length; i++) {
            if (!removeRow[i]) {
                // Copy the entire row from a to the corresponding row in b
                b[validRowIndex++] = Arrays.copyOf(a[i], a[i].length);
            }
        }

        return b;
    }

    private static double[][] replace(double[][] b) {
        if (b.length == 0) {
            return b;
        }
        double columnSum = 0.0; //Column sum
        double columnAverage = 0.0;//Column average
        int numNonNegatives = 0; // number of positives in the column
//Loop through each column j to replace negative with average of positives in the same column
        for (int j = 0; j < b[0].length; j++) {
            columnSum = 0.0;
            numNonNegatives = 0;
            for (int i = 0; i < b.length; i++) {
                //Adds all nonnegative numbers to columnSum while also keeping track of how many
                if (b[i][j] >= 0) {
                    columnSum += b[i][j];
                    numNonNegatives++;
                }
            }

/**
 * To be completed
 * First find columnSum and numNonNegatives using non-negative values
 of
 * the j-th column of b.
 * This is done by looping through each row of the same column
 */


            columnAverage = numNonNegatives > 0 ? columnSum / numNonNegatives : 0;
/**
 * To be completed
 * Now replace all negative numbers of j-th column of b with the
 column average
 */

//Loop through each column and replace all negative values with column average
        for (int i = 0; i < b.length; i++) {
            if (b[i][j] < 0) {
                b[i][j] = columnAverage;
            }
        }


        }

            return b;
        }

        //-----------------------------------------------------------------
//
//-----------------------------------------------------------------
        public static void main (String[]args){
            double[][] a = {{-1, 4, 3, 2, -3, 2}, {-2, 3, 5, -4, 0, 1}, {-1, -3, -4, 1, -1, 0}, {-1, 2, -
                    3, 6, 5, 3}, {-3, 2, -3, -5, 0, 0}}; //A 5 x 6 Dimension;
            System.out.println("Printing input array...");
            printArray(a);
            double[][] b = manipulate(a, 0.5);
            b = manipulate(a, 0.6);
            System.out.println("Printing original array...");
            printArray(a);
        }
    }
