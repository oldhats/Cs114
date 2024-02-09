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
    private static boolean solve(char[][] t, int row, int col, char ch,
                                 boolean allowDiagonalNeighbors) {
        /**
         * Complete code here
         */

        //check base case ch = 'y', return true
        //determine next char
        // check if nextChar is already in an adjacent cell to (row,col), if it is
        // call solve() recursively  with row,col corresponding to the cell where next
        // is and ch should be nextChar, if hte result is true, return true, else return false
        t[row][col] = ch;
        char nextChar = (char) (ch+1);

        if (ch == 'y') {
            return true;
        }

        if (ch == 'z'){
            return false;
        }

        if (row < 4 && t[row + 1][col] != 'z') {
            if (t[row + 1][col] == nextChar) {
                if (solve(t, row + 1, col, nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
            }
        }

        if (row > 0 && row < 4 && t[row - 1][col] != 'z') {
            if (t[row - 1][col] == nextChar) {
                if (solve(t, row - 1, col, nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
            }
        }

        if (col < 4 && t[row][col + 1] != 'z') {
            if (t[row][col + 1] == nextChar) {
                if (solve(t, row, col + 1, nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
            }
        }

        if (col > 0 && col < 4 && t[row][col -1] != 'z') {
            if (t[row][col -1 ] == nextChar) {
                if (solve(t, row, col -1 , nextChar, allowDiagonalNeighbors)) {
                    return true;
                }
            }
        }



        // if nextChar is not in adjacent cell, (don't try to look for char in adjacent cell),
        // try to put nextChar in one of the free adjacent cells
        // cell say (x,y), t[x][y] = nextChar, call solve() recursively with row = x and col = y
        // and ch - nextChar, if result is true, return true, else t[x][y] = 'z'
        // repeat with another free adjacent cell


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

