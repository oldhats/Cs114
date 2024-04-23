package edu.njit.cs114;

import java.util.*;

/**
 * Author: Ravi Varadarajan
 * Date created: 4/21/2024
 */
public class GraphSearch {

    public static final int VISITED = 1;
    public static final int UNVISITED = 0;

    public static void printDfsTreeEdges(Graph g, List<Graph.Edge> treeEdges) {
        int lastVertex = -1;
        for (Graph.Edge edge : treeEdges) {
            if (edge.from != lastVertex) {
                if (lastVertex >= 0) {
                    System.out.println();
                }
                System.out.print(edge.from + " ---> " + edge.to);
            } else {
                System.out.print(" ---> " + edge.to);
            }
            lastVertex = edge.to;
        }
        System.out.println();
    }

    public static void printBfsTreeEdges(Graph g, List<Graph.Edge> treeEdges) {
        int fromVertex = -1;
        for (Graph.Edge edge : treeEdges) {
            if (edge.from != fromVertex) {
                if (fromVertex >= 0) {
                    System.out.println();
                }
                System.out.print(edge.from + "(" + g.getMark(edge.from) +
                        ")" + " ---> " +
                        edge.to + "(" + g.getMark(edge.to) + ")");
            } else {
                System.out.print("," + edge.to+ "(" + g.getMark(edge.to) + ")");
            }
            fromVertex = edge.from;
        }
        System.out.println();
    }


    public static void graphTraverseBFS(Graph g) {
        System.out.println("breadth-first search of graph..");
        for (int v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        for (int v = 0; v < g.numVertices(); v++) {
            if (g.getMark(v) == UNVISITED) {
                System.out.println("Start vertex : "+v);
                LinkedList<Graph.Edge> treeEdges = bfs(g, v, -1);
                printBfsTreeEdges(g, treeEdges);
            }
        }
    }

    public static void graphTraverseDFS(Graph g) {
        System.out.println("depth-first search of graph..");
        for (int v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        for (int v = 0; v < g.numVertices(); v++) {
            if (g.getMark(v) == UNVISITED) {
                System.out.println("Start vertex : "+v);
                LinkedList<Graph.Edge> treeEdges = dfs(g, v, -1);
                printDfsTreeEdges(g, treeEdges);
            }
        }
    }

    // do a DFS starting from vertex start and optionally ending at vertex end if end >=0
    public static LinkedList<Graph.Edge> dfs(Graph g, int v, int end) {
        g.setMark(v,VISITED);
        Iterator<Graph.Edge> outEdgeIter = g.getOutgoingEdges(v);
        // search-tree edges rooted at v
        LinkedList<Graph.Edge> subTreeEdges = new LinkedList<>();
        /**
         * Complete code here lab assignment
         */

        while(outEdgeIter.hasNext()){
            Graph.Edge edge = outEdgeIter.next();
            if (g.getMark(edge.to) == UNVISITED){
                subTreeEdges.add(edge);

                if (edge.to == end){
                    return subTreeEdges;
                }

                subTreeEdges.addAll(dfs(g,edge.to,end));
                }
            }




        return subTreeEdges;
    }

    // do a BFS starting from vertex start and optionally ending at vertex end if end >=0
    public static LinkedList<Graph.Edge> bfs(Graph g, int start, int end) {
        Queue<Integer> vertexQueue = new LinkedList<Integer>();
        vertexQueue.add(start);
        g.setMark(start,1);
        LinkedList<Graph.Edge> treeEdges = new LinkedList<>();
        while (!vertexQueue.isEmpty()) {
            /**
             * Complete code here lab assignment
             */

            int removedVertex = vertexQueue.remove();
            Iterator <Graph.Edge> removedVertexIterator = g.getOutgoingEdges(removedVertex);
            while (removedVertexIterator.hasNext()) {
                Graph.Edge edge = removedVertexIterator.next();
                if (g.getMark(edge.to) == UNVISITED){
                    g.setMark(edge.to, g.getMark(edge.from) + 1);

                    vertexQueue.add(edge.to);
                    treeEdges.add(edge);
                }

                if (edge.to == removedVertex) {
                    return treeEdges;
                }

            }


        }
        return treeEdges;
    }

    /**
     * Find a path in the graph from fromVertex to toVertex using BFS or DFS
     * @param g
     * @param fromVertex
     * @param toVertex
     * @param isBfs use BFS if set to true else use DFS
     * @return
     */
    public static List<Integer> graphPath(Graph g, int fromVertex, int toVertex,
                                          boolean isBfs) {
        for (int v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        LinkedList<Integer> pathVertices = new LinkedList<>();
        LinkedList<Graph.Edge> treeEdges = isBfs ? bfs(g, fromVertex, toVertex) :
                dfs(g, fromVertex, toVertex);
        // get edges in the path starting from toVertex
        Iterator<Graph.Edge> edgeIter = treeEdges.descendingIterator();
        /**
         * Complete code here for lab assignment
         */


        while (edgeIter.hasNext()){
            Graph.Edge edge = edgeIter.next();
            pathVertices.add(edge.to);
            if (edge.to == fromVertex){
                break;
            }

        }

        return pathVertices;
    }

    /**
     * (complete it for homework assignment)
     * Returns true if a cycle exists in undirected graph
     * @param g undirected graph
     * @return
     */
    public static boolean cycleExists(Graph g) {
        return false;
    }


    /**
     * (complete it for homework assignment)
     * Returns true if a simple cycle with odd number of edges exists in undirected graph
     * @param g undirected graph
     * @return
     */
    public static boolean oddCycleExists(Graph g) {
        return false;
    }

    /**
     * Find the diameter (length of longest path) in the tree
     * @param tree (undirected graph which is a tree)
     * @return
     */
    public static int diameter(Graph tree) {
        /**
         * Complete code for the homework
         */
        return 0;
    }

    /**
     * Does the directed graph have a cycle of directed edges ? (Extra credit)
     * @param g
     * @return
     */
    public static boolean cycleExistsDirect(Graph g) {
        return false;
    }


    public static void main(String [] args) throws Exception {
        Graph g = new AdjListGraph(8, true);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,0);
        g.addEdge(3,2);
        g.addEdge(4,3);
        g.addEdge(4,6);
        g.addEdge(5,7);
        g.addEdge(6,5);
        g.addEdge(7,5);
        g.addEdge(7,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
        //System.out.println("Directed cycle exists in g ? " + cycleExistsDirect(g));
        g = new AdjListGraph(8, false);
        g.addEdge(0,1);
        g.addEdge(1,3);
        g.addEdge(3,2);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(5,7);
        g.addEdge(4,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
        g = new AdjListGraph(8, false);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(3,2);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(6,5);
        g.addEdge(5,7);
        g.addEdge(7,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        System.out.println("Odd cycle exists in g ? " + oddCycleExists(g));
//        g = new AdjListGraph(7, false);
//        g.addEdge(0,1);
//        g.addEdge(0,2);
//        g.addEdge(0,3);
//        g.addEdge(2,4);
//        g.addEdge(2,5);
//        g.addEdge(3,6);
//        System.out.println(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        System.out.println("Diameter of g = " + diameter(g));
//        assert diameter(g) == 4;
//        g = new AdjListGraph(7, false);
//        g.addEdge(0,1);
//        g.addEdge(1,2);
//        g.addEdge(2,3);
//        g.addEdge(3,0);
//        g.addEdge(3,4);
//        g.addEdge(4,5);
//        g.addEdge(5,6);
//        g.addEdge(6,3);
//        System.out.println(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        System.out.println("Odd cycle exists in g ? " + oddCycleExists(g));
    }


}
