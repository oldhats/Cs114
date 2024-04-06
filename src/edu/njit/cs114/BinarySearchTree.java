package edu.njit.cs114;

import org.w3c.dom.Node;

import java.util.*;

/**
 * Author: Ravi Varadarajan
 * Date created: 4/1/2024
 */
public class BinarySearchTree<K extends Comparable<K>,V> {

    private BSTNode<K,V> root;
    private int size;

    public static class BSTNodeData<K extends Comparable<K>,V> {
        private K key;
        private V value;

        public BSTNodeData(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "," + value;
        }
    }

    public static class BSTNode<K extends Comparable<K>,V> implements
            BinTreeNode<BSTNodeData<K,V>> {

        private BSTNodeData<K,V> nodeData;
        private int height;
        // number of keys (including the key in this node) in the subtree rooted at this node
        private int size;
        private BSTNode<K, V> left, right;

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.nodeData = new BSTNodeData<>(key, value);
            this.left = left;
            this.right = right;
            /**
             * Complete code to store height and size (for the lab)
             */


        }

        public void setAugmentedInfo(){

        int leftHeight = (left != null) ? left.height: 0;
        int rightHeight = (right != null) ? right.height : 0;
        int leftSize = (left != null) ? left.size : 0;
        int rightSize = (right != null) ? right.size : 0;

        this.height = 1 + Math.max(leftHeight, rightHeight);
        this.size = 1 + leftSize + rightSize;

        }


        public BSTNode(K key, V value) {
            this(key, value, null, null);
        }

        public K getKey() {
            return nodeData.key;
        }

        public V getValue() {
            return nodeData.value;
        }

        @Override
        public BSTNodeData<K, V> element() {
            return nodeData;
        }

        @Override
        public BSTNodeData<K, V> setElement(BSTNodeData<K, V> element) {
            BSTNodeData<K, V> oldValue = nodeData;
            this.nodeData = new BSTNodeData<>(element.key, element.value);
            return oldValue;
        }

        @Override
        public BSTNode<K, V> leftChild() {
            return left;
        }

        @Override
        public BSTNode<K, V> rightChild() {
            return right;
        }

        @Override
        public boolean isLeaf() {
            return (left == null && right == null);
        }

        private void setLeftChild(BSTNode<K, V> node) {
            this.left = node;
            /**
             * Complete code to store height and size  (for the lab)
             */

            setAugmentedInfo();
        }

        private void setRightChild(BSTNode<K, V> node) {
            this.right = node;
            /**
             * Complete code to store height and size  (for the lab)
             */

            setAugmentedInfo();
        }

        private void setValue(V value) { this.nodeData.value = value; };

        /**
         * Returns height of right subtree - height of left subtree
         * @return
         */
        @Override
        public int balanceFactor() {
            /**
             * Complete code for the lab
             *
             */

            int leftHeight = 0;
            int rightHeight = 0;

            if (left != null){
                leftHeight = left.height;
            }

            if (right != null){
                rightHeight = right.height;
            }



            return rightHeight - leftHeight;
        }

    }

    public BSTNode<K,V> getRoot() {
        return root;
    }

    private V getValueAux(BSTNode<K,V> localRoot, K key) {
        /**
         * Complete code for the lab
         */
        return null; // to be replaced if necessary
    }

    /**
     * Get value for the key
     * @param key
     * @return value, null if key does not exist
     */
    public V getValue(K key) {
        return getValueAux(root, key);
    }

    /**
     * Rotate left or right the child node depending on whether child is a right or
     * left child of localRoot
     * @param localRoot root of subtree involved in rotation
     * @param child child of localRoot
     * @return the new root of the subtree
     */
    private BSTNode<K,V> singleRotate(BSTNode<K,V> localRoot, BSTNode<K,V> child) {
        /**
         * Complete code that will be useful for homeowork
         */
        return child; // to be removed if necessary
    }

    /**
     * Rotate grandchild node left and then right if child is left child of localRoot and grandChild is
     * right child of child.
     * Rotate grandchild node right and then left if child is right child of localRoot and grandChild is
     * left child of child.
     * @param localRoot root of subtree involved in rotatio
     * @param child child node of localRoot
     * @param grandChild child node of child
     * @return the new root of the subtree
     */
    private BSTNode<K,V> doubleRotate(BSTNode<K,V> localRoot, BSTNode<K,V> child,
                                      BSTNode<K,V> grandChild) {
        /**
         * Complete code that will be useful for homeowork
         */
        return grandChild; // to be removed if necessary
    }

    private BSTNode<K,V> balance(BSTNode<K,V> localRoot) {
        /**
         * Complete code for the homework
         * (call singleRotate() or doubleRotate as the case may be)
         */
        return localRoot; // to be replaced if necessary for homework
    }

    public BSTNode<K,V> insertAux(BSTNode<K,V> localRoot, K key, V value) {
        if (localRoot == null) {
            return new BSTNode<K,V>(key, value);
        }
        int result = key.compareTo(localRoot.nodeData.key);
        if (result < 0) {
            localRoot.setLeftChild(insertAux(localRoot.left, key, value));
        } else if (result > 0){
            localRoot.setRightChild(insertAux(localRoot.right, key, value));
        } else {
            localRoot.setValue(value);
        }
        /**
         * Complete code to set height and size of localRoot for the lab
         */
        // Balance the tree if necessary

        int  localRootHeightLeft = (localRoot.left != null) ? localRoot.left.height + 1: 0;
        int  localRootHeightRight = (localRoot.right != null) ? localRoot.right.height + 1: 0;
        int  localRootSizeLeft = (localRoot.left != null) ? localRoot.left.size: 0;
        int  localRootSizeRight = (localRoot.right != null) ? localRoot.right.size: 0;

        localRoot.height = 1 + Math.max(localRootHeightLeft,localRootHeightRight);
        localRoot.size = 1 + localRootSizeLeft + localRootSizeRight;

        return balance(localRoot);
    }

    /**
     * Insert/Replace (key,value) association or mapping in the tree
     * @param key
     * @param value value to insert or replace
     */
    public void insert(K key, V value) {
        this.root = insertAux(root, key, value);
    }

    // Extra credit problem for homework
    /**
     * Delete the value associated with the key if it exists
     * Note you need to set height and size properly
     * in the nodes of the subtrees affected and also balance the tree
     * @param key
     * @return value deleted if key exists else null
     */
    public V delete(K key) {
        return null;
    }

    public int height() {
        return (root == null ? 0 : root.height);
    }

    public int size() {
        return (root == null ? 0 : root.size);
    }

    private boolean isBalanced(BSTNode<K,V> localRoot) {
        /**
         * Complete code here for the lab
         */

        if (localRoot == null) {
            return true;
        }

        if (Math.abs(localRoot.right.height - localRoot.left.height) > 1){
            return true;
        }

        return isBalanced(localRoot.left) && isBalanced(localRoot.right);
    }

    /**
     * Is the tree balanced ?
     * For every node, height of left and right subtrees differ by at most 1
     * @return
     */
    public boolean isBalanced() {


        return isBalanced(root);
    }



    /**
     * Get level ordering of nodes; keys in a level must be in descending order
     * @return a map which associates a level with list of nodes at that level
     */
    public Map<Integer, List<BSTNode<K,V>>> getNodeLevels() {
        Map<Integer, List<BSTNode<K,V>>> nodeLevels = new HashMap<>();
        /**
         * Complete code for the lab
         */
        return nodeLevels;
    }

    /**
     * Return list of nodes whose keys are greater than or equal to key1
     *   and smaller than or equal to key2
     * @param key1
     * @param key2
     * @return
     */
    public List<BSTNode<K,V>> getRange(K key1, K key2) {
        /**
         * Complete code for homework (define a recursive aux function to be called from here)
         */
        return null;
    }

    /**
     * Find number of keys smaller than or equal to the specified key
     * @param key
     * @return
     */
    public int rank(K key) {
        /**
         * Complete code for homework (define a recursive aux function to be called from here)
         */
        return 0;
    }


    public static void main(String [] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.insert(25,"a");
        bst.insert(15,"b");
        bst.insert(30,"c");
        bst.insert(5,"d");
        bst.insert(27,"e");
        bst.insert(36,"f");
        bst.insert(40,"g");
        bst.insert(10,"k");
        bst.insert(52,"l");
        System.out.println("Printing tree bst..");
        new BinTreeInorderNavigator<BSTNodeData<Integer,String>>().visit(bst.getRoot());
        int key = 36;
        String value = bst.getValue(key);
        if (value != null) {
            System.out.println("Value for key "+ key + "=" + value);
        } else {
            System.out.println("Key " + key + " does not exist");
        }
        key = 20;
        value = bst.getValue(key);
        if (value != null) {
            System.out.println("Value for key "+ key + "=" + value);
        } else {
            System.out.println("Key " + key + " does not exist");
        }
        bst.insert(40,"m");
        System.out.println("Printing tree bst..");
        new BinTreeInorderNavigator<BSTNodeData<Integer,String>>().visit(bst.getRoot());
//        System.out.println("Value for deleted key 5 = " + bst.delete(5));
//        System.out.println("Printing tree bst..");
//        new BinTreeInorderNavigator<BSTNodeData<Integer,String>>().visit(bst.getRoot());
//        System.out.println("Value for deleted key 30 = " + bst.delete(30));
//        System.out.println("Printing tree bst..");
//        new BinTreeInorderNavigator<BSTNodeData<Integer,String>>().visit(bst.getRoot());
        System.out.println("size of bst=" + bst.size());
        System.out.println("height of bst=" + bst.height());
        System.out.println("Is bst an AVL tree ? " + bst.isBalanced());
        Map<Integer, List<BSTNode<Integer,String>>> nodeLevels = bst.getNodeLevels();
        for (int level : nodeLevels.keySet()) {
            System.out.print("Keys at level " + level + " :");
            for (BSTNode<Integer,String> node : nodeLevels.get(level)) {
                System.out.print(" " + node.getKey());
            }
            System.out.println("");
        }
        BinarySearchTree<Integer, Integer> bst1 = new BinarySearchTree<>();
        bst1.insert(44,1);
        bst1.insert(17,2);
        bst1.insert(78,3);
        bst1.insert(50,4);
        bst1.insert(62,5);
        bst1.insert(88,6);
        bst1.insert(48,7);
        bst1.insert(32,8);
        System.out.println("Printing tree bst1..");
        new BinTreeInorderNavigator<BSTNodeData<Integer,Integer>>().visit(bst1.getRoot());
        System.out.println("size of bst1=" + bst1.size());
        System.out.println("height of bst1=" + bst1.height());
        System.out.println("Is bst1 an AVL tree ? " + bst1.isBalanced());
        Map<Integer, List<BSTNode<Integer,Integer>>> nodeLevels1 = bst1.getNodeLevels();
        for (int level : nodeLevels1.keySet()) {
            System.out.print("Keys at level " + level + " :");
            for (BSTNode<Integer,Integer> node : nodeLevels1.get(level)) {
                System.out.print(" " + node.getKey());
            }
            System.out.println("");
        }
//        System.out.println("rank of key 10 in bst=" + bst.rank(10)); // should be 2
//        System.out.println("rank of key 30 in bst=" + bst.rank(30)); // should be 6
//        System.out.println("rank of key 3 in bst=" + bst.rank(3)); // should be 0
//        System.out.println("rank of key 55 in bst=" + bst.rank(55)); // should be 9
//        List<BSTNode<Integer,Integer>> rangeNodes = bst1.getRange(32,62);
//        System.out.print("Keys in the range : [32,62] are:");
//        // should get 32,44,48,50,62,
//        for (BSTNode<Integer,Integer> node : rangeNodes) {
//            System.out.print(node.getKey() + ",");
//        }
//        System.out.println("");
//        rangeNodes = bst1.getRange(10,50);
//        System.out.print("Keys in the range : [10,50] are:");
//        // should get 17,32,44,48,50,
//        for (BSTNode<Integer,Integer> node : rangeNodes) {
//            System.out.print(node.getKey() + ",");
//        }
//        System.out.println("");
//        rangeNodes = bst1.getRange(90,100);
//        System.out.print("Keys in the range : [90,100] are:");
//        // should get empty list
//        for (BSTNode<Integer,Integer> node : rangeNodes) {
//            System.out.print(node.getKey() + ",");
//        }
//        System.out.println("");
    }

}
