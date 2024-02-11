package edu.njit.cs114;

import java.util.Scanner;

/**
 * Author: Ravi Varadarajan
 * Date created: 2/2/2024
 */
public class AtoY {

    public static void printTable(char[][] t) {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j)
                System.out.print(t[i][j]);
            System.out.println();
        }
    }

    /**
     * Check if we can fill characters in the grid starting from ch in the cell (row,col)
     * The grid may already have some characters which should not be changed
     *
     * @param t                      grid
     * @param row
     * @param col
     * @param ch
     * @param allowDiagonalNeighbors
     * @return true if characters can be filled in else false
     */
private static boolean solve(char[][] t, int row, int col, char ch, boolean allowDiagonalNeighbors) {
    // Complete code here
    // Base case
    // If the character is 'y' then return true
    t[row][col] = ch;
    char nextChar = (char) (ch + 1);

    if (ch == 'y') {
        return true;
    }
    // Recursive case

    int[] rowOffsets = {-1, 0, 1, 0, -1, -1, 1, 1};
    int[] colOffsets = {0, 1, 0, -1, -1, 1, -1, 1};
    int maxOffset = allowDiagonalNeighbors ? 8 : 4;
    // Check if the next character is present in the 8 directions
    // If it is present then call the solve function recursively
    for (int i = 0; i < maxOffset; i++) {
        int newRow = row + rowOffsets[i];
        int newCol = col + colOffsets[i];

        // Check if the new row and column are within the bounds of the grid
        if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5) {
            if (t[newRow][newCol] == nextChar) {
                if (solve(t, newRow, newCol, nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
            } else if (t[newRow][newCol] == 'z') {
                t[newRow][newCol] = nextChar;
                if (solve(t, newRow, newCol, nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
                t[newRow][newCol] = 'z';
            }
        }
    }

    return false;
}



    public static boolean solve(char[][] t, boolean allowDiagonalNeighbors) {
        int row = -1, col = -1;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] == 'a') {
                    row = i;
                    col = j;
                }
            }
        }
        if (row >= 0 && col >= 0) {
            return solve(t, row, col, 'a', allowDiagonalNeighbors);
        } else {
            // try every free position for starting with 'a'
            /**
             * Complete code here
             */
            for (int i = 0; i < t.length; i++) {
                for (int j = 0; j < t[i].length; j++) {
                    if (t[i][j] == 'z') {
                        t[i][j] = 'a';
                        if (solve(t, i, j, 'a', allowDiagonalNeighbors)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }





    public static void main (String[]args){
        System.out.println("Enter 5 rows of lower-case letters a to z below. Note z indicates empty cell");
        Scanner sc = new Scanner(System.in);
        char[][] tbl = new char[5][5];
        String inp;
        for (int i = 0; i < 5; ++i) {
            inp = sc.next();
            for (int j = 0; j < 5; ++j) {
                tbl[i][j] = inp.charAt(j);
            }
        }
        System.out.println("Are diagonal neighbors included ? (true or false)");
        if (solve(tbl, sc.nextBoolean())) {
            System.out.println("Printing the solution...");
            printTable(tbl);
        } else {
            System.out.println("There is no solution");
        }
    }
}

