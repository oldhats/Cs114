package edu.njit.cs114;


import java.awt.*;

/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 * Modified by Ravi Varadarajan for CS 114
 **/
public class Maze {

    public static Color PATH = Color.green;
    public static Color BACKGROUND = Color.white;
    public static Color NON_BACKGROUND = Color.red;
    public static Color TEMPORARY = Color.black;

    /** The maze */
    private TwoDimGrid maze;

    private int cnt;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath(int startCol, int startRow, int destCol, int destRow) {
//        cnt = 0; // keeps track of order of cell visits
        return findMazePathAux(startCol, startRow, destCol, destRow);
    }

    /**
     * Attempts to find a maze path recursively through point (x, y) to destination cell (destCol,destRow)
     *
     * @pre Possible path cells are initially in NON_BACKGROUND color
     * @post If a path is found, all cells on it are set to the PATH color; all
     *       cells that were visited but are not on the path are in the TEMPORARY
     *       color.
     * @param x
     *            The x-coordinate of current point
     * @param y
     *            The y-coordinate of current point
     * @param destCol
     *            The x-coordinate of destination cell
     * @param destRow
     *            The y-coordinate of destination cell
     * @return If a path through (x, y) is found, true; otherwise, false
     *         If a path is found, color the cells in the path with PATH color
     *         Also number cells in the order in which they are visited
     */
    public boolean findMazePathAux(int x, int y, int destCol, int destRow) {
        if (y >= maze.getNRows() || x >= maze.getNCols() || x < 0 || y < 0) {
            // no path from (x,y) to destination
            return false;
        }

        // checks if cell is destination cell if so adds 1 to the count and sets the label to the count also colors the cell with PATH
        if ((x == destCol) && (y == destRow)) {
            cnt += 1;
            maze.setLabel(x,y,""+cnt);
            maze.recolor(x, y, PATH);
            return true;
        }


        // check if this cell has been visited before
        if (maze.getColor(x,y) == TEMPORARY || maze.getColor(x,y) == BACKGROUND) {
            return false;
        }

        //adds 1 to the count and sets the label to the count also colors the cell with TEMPORARY
        cnt+= 1;
        maze.setLabel(x,y,""+cnt);
        maze.recolor(x, y, TEMPORARY);

        // checks if there is a path from the current cell to the destination cell by checking neighboring cells
        if (findMazePath(x - 1, y, destCol, destRow) ||
                findMazePath(x + 1, y, destCol, destRow) ||
                findMazePath(x, y - 1, destCol, destRow) ||
                findMazePath(x, y + 1, destCol,destRow)) {
            maze.recolor(x, y, PATH);
            return true;
        }






        /*
         * To BE COMPLETED (base and recursion cases)
         *
         * check if (x,y) is (destCol,destRow),color (x,y) with PATH (use maze.recolor()),
         * also label the cell with the next cnt values (increment cnt, maze.setLabel(x,y, ""+cnt)
         * and return true
         *
         * if (x,y) is colored TEMPORARY or BACKGROUND (use method maze.getColor()), return false
         *
         * color (x,y) with TEMPORARY (do before recursive call) also must set label with next cnt value
         *
         * make a recursive call findMazePathAux() to find if there is a path from (x-1,y) to destination
         * and check the result, if it is true, return true after changing color of (x-1,y) to PATH
         * if it is false, try other neighbors (x+1,y), (x,y-1), and (x,y+1) the same way
         *
         * return false if all recursive calls return false
         *
         *
         */

        return false;
    }

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void resetMaze() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
        maze.clearLabels();
    }

    public void restoreMaze() {
        maze.recolor(PATH, NON_BACKGROUND);
        maze.recolor(TEMPORARY, NON_BACKGROUND);
        maze.clearLabels();
    }
}
