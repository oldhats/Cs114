package edu.njit.cs114;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.*;

/**
 * Author: Ravi Varadarajan
 * Date created: 4/10/2024
 */
public class HuffmanTreeCoder {

    private static final char INTERNAL_NODE_CHAR = (char) 0;
    private HuffmanTreeNode root;
    private final Comparator<HuffmanTreeNode> nodeComparator;
    private Map<Character,String> charCodes = new HashMap<>();

    public static class HuffmanTreeNodeData {

        private final double weight; /* stores frequency */
        private final char ch;

        public HuffmanTreeNodeData(double weight, char ch) {
            this.weight = weight;
            this.ch = ch;
        }

        public double getWeight() {
            return weight;
        }

        public char getCh() {
            return ch;
        }

        public String toString() {
            return weight + "," + ch;
        }
    }

    public static class HuffmanTreeNode implements BinTreeNode<HuffmanTreeNodeData> {

        private HuffmanTreeNodeData nodeData;
        private final HuffmanTreeNode left, right;


        public HuffmanTreeNode(double weight, char ch, HuffmanTreeNode left,
                               HuffmanTreeNode right) {
            this.nodeData = new HuffmanTreeNodeData(weight, ch);
            this.left = left;
            this.right = right;
        }

        // used by leaf node which represents a character
        public HuffmanTreeNode(double weight, char ch) {
            /** Complete code for lab assignment **/

            // use this(weight,ch,null,null)
            this(weight,ch,null,null);
        }

        // used by internal node
        public HuffmanTreeNode(double weight,HuffmanTreeNode left, HuffmanTreeNode right) {
            /** Complete code for lab assignment **/

            // this(weight,Internal_node_char,left,right);

            this(weight,INTERNAL_NODE_CHAR,left,right);
        }

        public Double getWeight() {
            return nodeData.weight;
        }

        public Character getChar() {
            return nodeData.ch;
        }

        @Override
        public HuffmanTreeNodeData element() {
            return nodeData;
        }

        @Override
        public HuffmanTreeNodeData setElement(HuffmanTreeNodeData element) {
            HuffmanTreeNodeData oldValue = nodeData;
            this.nodeData = new HuffmanTreeNodeData(element.weight, element.ch);
            return oldValue;
        }

        @Override
        public HuffmanTreeNode leftChild() {
            return left;
        }

        @Override
        public HuffmanTreeNode rightChild() {
            return right;
        }

        @Override
        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int balanceFactor() {
            return 0;
        }
    }

    public HuffmanTreeCoder(Comparator<HuffmanTreeNode> comp, Map<Character,Double> freqMap) {
        this.nodeComparator = comp;
        buildTree(freqMap);
    }

    public HuffmanTreeCoder(Map<Character,Double> freqMap) {
        this(new HuffmanNodeComparator(), freqMap);
    }

    public static class HuffmanNodeComparator implements Comparator<HuffmanTreeNode> {
        @Override
        public int compare(HuffmanTreeNode t1, HuffmanTreeNode t2) {
            if (t1 == null || t2 == null) {
                throw new IllegalArgumentException("Nodes to be compared cannot be null");
            }
            return Double.compare(t1.getWeight(), t2.getWeight());
        }
    }

    /**
     * This procedure must be recursive to get full credit
     * Extract codes for the characters in the Huffman subtree
     * rooted at node and add them to the charcodes map
     * @param node
     * @param prefix to be added to before every char code constructed from the subtree
     */
    private void encodeChars(HuffmanTreeNode node, String prefix) {
        /**
         * Complete code for lab assignment
         */

        if (node == null){
            return;
        }

        if (node.isLeaf()){
            charCodes.put(node.getChar(), prefix);
        }

        encodeChars(node.left, prefix + "0");
        encodeChars(node.right, prefix + "1");

    }



    public void buildTree(Map<Character,Double> freqMap) {
        PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue<HuffmanTreeNode>(this.nodeComparator);
        /**
         * complete code here for lab assignment
         */

        for(Map.Entry<Character,Double> entry: freqMap.entrySet()){
            queue.add(new HuffmanTreeNode(entry.getValue(),entry.getKey()));
        }

        while (!queue.isEmpty()){
            HuffmanTreeNode minFreqTreeNode = queue.remove();

            if (queue.isEmpty()){
                root = minFreqTreeNode;
                break;
            }

            HuffmanTreeNode nextMinFreqTreeNode = queue.remove();

            HuffmanTreeNode newNode = new HuffmanTreeNode((minFreqTreeNode.getWeight() + nextMinFreqTreeNode.getWeight()), minFreqTreeNode, nextMinFreqTreeNode);

            queue.add(newNode);
        }

        encodeChars(root,"");
    }

    public String encodeBitString(String str) {
        StringBuilder builder = new StringBuilder();
        /**
         * complete code here for lab assignment
         */


        for (int i = 0; i < str.length(); i++){
            builder.append(charCodes.get(str.charAt(i)));
        }

        return builder.toString();
    }

    /**
     * Uses a reader to fetch characters from a stream
     * @param rdr
     * @return
     * @throws Exception
     */
    public String decodeBitString(Reader rdr) throws Exception {
        StringBuilder builder = new StringBuilder();
        char [] buf = new char[1024];
        HuffmanTreeNode node = root;
        int nCharsRead = 0;
        while ((nCharsRead = rdr.read(buf)) > 0) {
            /**
             * Complete code for homework assignment
             * Each character read in buf, check if it is a '0' or '1'. Based on that,
             * set node to left child or right child.
             * If it is leaf, append character to builder and reset node to root
             */
        }
        return builder.toString();
    }


    /**
     * Decode the binary string encoded using this tree
     * @param code
     * @return
     */
    public String decodeBitString(String code) {
        /**
         * Complete code for homework assignment
         */
        return null;
    }


    /**
     * Compress a text file
     * @param fileName
     * @param compressedFileName
     */
    public void compress(String fileName, String compressedFileName) {
        FileReader rdr = null;
        FileWriter writer = null;
        try {
            char[] charBuf = new char[1024];
            rdr = new FileReader(fileName);
            int nCharsRead = 0;
            writer = new FileWriter(compressedFileName);
            while ((nCharsRead = rdr.read(charBuf)) >= 0) {
                writer.write(encodeBitString(new String(charBuf, 0, nCharsRead)));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (rdr != null) {
                try {
                    rdr.close();
                } catch (Exception e) {}
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {}
            }
        }
    }

    private static long getFileSize(String fileName) {
        return new File(fileName).length();
    }

    private static long getSizeForCompressedFile(String fileName) {
        return (long) Math.ceil(new File(fileName).length()/8);
    }


    /**
     * Get frequency map for characters in a file
     * @param fileName
     * @return
     */
    public static Map<Character,Double> getFreqMap(String fileName) {
        Map<Character,Double> freqMap = new HashMap<>();
        FileReader rdr = null;
        try {
            char[] charBuf = new char[1024];
            rdr = new FileReader(fileName);
            int nCharsRead = 0;
            while ((nCharsRead = rdr.read(charBuf)) >= 0) {
                for (int i=0; i < nCharsRead; i++) {
                    Double freq = freqMap.get(charBuf[i]);
                    if (freq == null) {
                        freq = 0d;
                    }
                    freqMap.put(charBuf[i],++freq);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (rdr != null) {
                try {
                    rdr.close();
                } catch (Exception e) {
                }
            }
        }
        return freqMap;
    }


    public String toString() {
        HuffmanTreeNavigator navigator = new HuffmanTreeNavigator();
        navigator.visit(root);
        return navigator.toString();
    }


    public static void main(String [] args) throws Exception {
        // Uncomment lines for checking the homework soluion
        Map<Character,Double> freqMap = new HashMap<>();
        freqMap.put('C', 32d);
        freqMap.put('D', 42d);
        freqMap.put('E', 120d);
        freqMap.put('K', 7d);
        freqMap.put('L', 42d);
        freqMap.put('M', 24d);
        freqMap.put('U', 37d);
        freqMap.put('Z', 2d);
        HuffmanTreeCoder coder = new HuffmanTreeCoder(freqMap);
        System.out.println(coder.toString());
        String msg = "MUZZ";
        String bitStr = coder.encodeBitString(msg);
        float compressionRatio = ((float) msg.length()*8) / bitStr.length();
        System.out.println("compression ratio for msg: "+msg+" = "+compressionRatio);
//      String val = coder.decodeBitString(bitStr);
//      System.out.println("Decoded message = " +val);
        freqMap = new HashMap<>();
        int [] freqArr = new int [] {64, 13, 22, 32, 103, 21, 15, 47, 57, 1, 5, 32, 20, 57, 63, 15,
                1, 48, 51, 80, 23, 8, 18, 1, 16, 1, 186};
        for (int i = (int) 'a'; i < (int) 'z'; i++) {
            freqMap.put((char) i, (double) freqArr[i - (int) 'a']);
        }
        freqMap.put(' ', 186d);
        coder = new HuffmanTreeCoder(freqMap);
        //System.out.println(coder.toString());
        msg = "this program builds a custom huffman tree for a particular file";
        bitStr = coder.encodeBitString(msg);
        compressionRatio = ((float) msg.length()*8) / bitStr.length();
        System.out.println("compression ratio for msg: "+msg+" = "+compressionRatio);
//        val = coder.decodeBitString(bitStr);
//        System.out.println("Decoded message = " +val);
//        String textFile = "testHuffman.txt";
//        String codeFile = "testHuffman_code.txt";
//        Map<Character,Double> freqMap1 = getFreqMap(textFile);
//        coder = new HuffmanTreeCoder(freqMap1);
//        coder.compress(textFile,codeFile);
//        compressionRatio = ((float) getFileSize(textFile)) / getSizeForCompressedFile(codeFile);
//        System.out.println("compression ratio for file: "+ textFile +" = "+compressionRatio);
//        val = coder.decodeBitString(new FileReader(codeFile));
//        System.out.println("Decoded message = " +val);
    }

}

