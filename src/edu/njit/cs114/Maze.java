package edu.njit.cs114;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    private static class Cell {
        public final int col, row;
        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
        public boolean equals(Object obj) {
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell other = (Cell) obj;
            return this.row == other.row && this.col == other.col;
        }

        public String toString() {
            return "(" + col + "," + row + ")";
        }
    }

    private static boolean inStack(Stack<Cell> pathStack, int col, int row) {
        Cell target = new Cell(col, row);
        return pathStack.contains(target);
    }

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath(int startCol, int startRow, int destCol, int destRow) {
        cnt = 0; // keeps track of order of cell visits
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
        return true;
    }

    /* Wrapper method for finding shortest path **/
    public boolean findMazeShortestPath(int startCol, int startRow, int destCol, int destRow) {
        Stack<Cell> pathStack = new Stack<>();
        pathStack.push(new Cell(startCol,startRow));
        ArrayList<Cell> shortestPath = findMazeShortestPathAux(startCol, startRow,
                destCol, destRow, pathStack);
        if (!shortestPath.isEmpty()) {
            /**
             * TO BE COMPLETED (color shortest path green with labels indicating
             *    the order of cells in this path)
             */
            int cnt = 0;

            for(Cell cell: shortestPath){
                cnt++;
                String label = String.valueOf(cnt);
                maze.recolor(cell.col,cell.row,PATH);
                maze.setLabel(cell.col,cell.row, label);
            }


            return true;
        } else {
            return false;
        }
    }

    /**
     * Attempts to find shortest path through point (x, y).
     *
     * @pre Possible path cells are initially in NON_BACKGROUND color
     * @post If a path is found, all cells on it are set to the PATH color; all
     *       cells that were visited but are not on the path are in the TEMPORARY
     *       color.
     * @param x
     *            The x-coordinate of current point
     * @param y
     *            The y-coordinate of current point
     * @param pathStack
     *            Stack that contains the current path found so far
     * @return If a path through (x, y) is found return shortest path; otherwise, return empty path
     */
    public ArrayList<Cell> findMazeShortestPathAux(int x, int y, int destCol, int destRow,
                                                   Stack<Cell> pathStack) {
        if (y >= maze.getNRows() || x >= maze.getNCols() || x < 0 || y < 0 ||
                maze.getColor(x, y) == BACKGROUND) {
            return new ArrayList<>();
        }
        /**
         * TO BE COMPLETED (base case)
         */

        if (x == destCol && y == destRow) {
            return new ArrayList<>(pathStack);
        }

        maze.recolor(x, y, TEMPORARY);
        ArrayList<Cell> shortestPath = new ArrayList<>();
        /**
         * To BE COMPLETED (recursion cases)
         */


        if (!inStack(pathStack,x+1, y)) {
            pathStack.push(new Cell (x+1,y));
            ArrayList<Cell> neighborShortestPath = findMazeShortestPathAux(x+1,y,destCol,destRow,pathStack);
            if (!neighborShortestPath.isEmpty()) {
                if (shortestPath.isEmpty() || (shortestPath.size() > neighborShortestPath.size())){
                    shortestPath = neighborShortestPath;

                }
            }
            pathStack.pop();
        }

        if (!inStack(pathStack,x-1, y)) {
            pathStack.push(new Cell (x-1,y));
            ArrayList<Cell> neighborShortestPath = findMazeShortestPathAux(x-1,y,destCol,destRow,pathStack);
            if (!neighborShortestPath.isEmpty()) {
                if (shortestPath.isEmpty() || (shortestPath.size() > neighborShortestPath.size())){
                    shortestPath = neighborShortestPath;

                }
            }
            pathStack.pop();
        }

        if (!inStack(pathStack,x, y + 1)) {
            pathStack.push(new Cell (x,y + 1));
            ArrayList<Cell> neighborShortestPath = findMazeShortestPathAux(x,y + 1,destCol,destRow,pathStack);
            if (!neighborShortestPath.isEmpty()) {
                if (shortestPath.isEmpty() || (shortestPath.size() > neighborShortestPath.size())){
                    shortestPath = neighborShortestPath;

                }
            }
            pathStack.pop();
        }

        if (!inStack(pathStack,x, y - 1)) {
            pathStack.push(new Cell (x,y - 1));
            ArrayList<Cell> neighborShortestPath = findMazeShortestPathAux(x,y - 1,destCol,destRow,pathStack);
            if (!neighborShortestPath.isEmpty()) {
                if (shortestPath.isEmpty() || (shortestPath.size() > neighborShortestPath.size())){
                    shortestPath = neighborShortestPath;

                }
            }
            pathStack.pop();
        }

        return shortestPath;

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
