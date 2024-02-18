package edu.njit.cs114;

/** Write Lab Part 2 Answers below  :
 *  (a) How does the time complexity of array append vary as n increases ?
 *  The time complexity varies as n increases by O(n) because as each element is added time increases with it.
 *  (b) How does the number of element copies during array append vary as n increases ?
 *  As the elements increase the time also increases proportional to the elements.
 *  (c) Does it make much difference if initial size of the array is 5000 instead of 1 ?
 *  It does not because time is only affected by how many elements we are adding to the array.
 */


import java.util.Objects;

/**
 * Author: Ravi Varadarajan
 * Date created: 2/14/2024
 */
public class DynamicDoubleArray {

    private static final int DEFAULT_INITIAL_CAPACITY = 1;

    private Double [] arr;
    private int size;
    // keeps track of number of element copies made during array expansion or contraction
    private int nCopies;

    public DynamicDoubleArray(int initialCapacity) {
        arr = new Double[initialCapacity];
    }

    public DynamicDoubleArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Add element at specified index position shifting to right elements at positions higher than
     *    or equal to index
     * @param index
     * @param elem
     * @throws IndexOutOfBoundsException if index < 0 or index > size()
     */
    public void add(int index, double elem) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        size++;
        if (size == arr.length){
            expandArray();
        }

        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }

        arr[index] = elem;
        /**
         * Complete code here for the lab
         */



    }

    public void expandArray() {
        /**
         * Complete code here for the lab
         */
        Double [] newArr = new Double [(2 * arr.length)];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
            nCopies++;
        }
        arr = newArr;

    }





    /**
     * Append element to the end of the array
     * @param elem
     */
    public void add(double elem) {
        /**
         * Complete code here for the lab
         */

        if (size == arr.length) {
            expandArray();
        }
        size++;
        arr[size - 1] = elem;

    }

    /**
     * Set the element at specified index position replacing any previous value
     * @param index
     * @param elem
     * @return previous value in the index position
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double set(int index, double elem) throws IndexOutOfBoundsException {
        /**
         * Complete code here for the lab
         */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Double oldVal = arr[index];
        arr[index] = elem;


        return oldVal;
    }

    /**
     * Get the element at the specified index position
     * @param index
     * @return
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double get(int index) throws IndexOutOfBoundsException {
        /**
         * Complete code here for the lab
         */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Double gotIndex = arr[index];

        return gotIndex;
    }

    /**
     * Remove and return the element at the specified index position. The elements with positions
     *  higher than index are shifted to left
     * @param index
     * @return
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double remove(int index) throws IndexOutOfBoundsException {
        /**
         * Complete code here for homework
         */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        // var for element we are removing
        Double removedElement = arr[index];


        // for loop that gives the element at the specified index a null value
        // then shifts all elements with positions higher than the index to the left
        for (int i = index; i < arr.length; i++) {
            if(Objects.equals(arr[i], removedElement)) {
                arr[i] = null;
            } else {
                arr[i - 1] = arr[i];
            }
        }



        return removedElement;
    }

    /**
     * Remove and return the element at the end of the array
     * @return
     * @throws IndexOutOfBoundsException if size() == 0
     */
    public double remove() throws IndexOutOfBoundsException {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Array is empty");
        }
        /**
         * Complete code here for homework
         */

        // The last element in the array given to removedElement
        Double removedElement = arr[arr.length - 1];

        if (removedElement == null) {
            int i = 1;
            i++;
            removedElement = arr[arr.length - i];
        }



        // New arr with one less index
        Double [] newArr = new Double [arr.length - 1];


        // For loop that checks if the element in the array is the removedElement if so
        // skips over that element
        for (int i = 0; i < arr.length; i++){
            if(!Objects.equals(arr[i], removedElement)){
                newArr[i] = arr[i];
            }
        }


        this.arr = newArr;


        return removedElement;
    }

    /**
     * Removes from this list all of the elements whose index is between fromIndex,
     * inclusive, and toIndex, exclusive.
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by (toIndex - fromIndex) elements.
     * @return
     * @throws IndexOutOfBoundsException if fromIndex or toIndex is out of range
     * i.e. (fromIndex < 0 || fromIndex >= size() || toIndex > size() || toIndex < fromIndex)
     */
    public void removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        /**
         * Complete code here for homework
         */
    }

    /**
     * Increase the capacity of the array, if necessary, to ensure that it holds
     * at least minCapacity elements
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity) {
        /**
         * Complete code here for homework
         */
    }

    /**
     * Trim the capacity of the array to hold just the number of elements
     */
    public void trimToSize() {
        /**
         * Complete code here for homework
         */
    }

    /**
     * Returns the number of elements in the array
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Returns the total number of copy operations done due to expansion of array
     * @return
     */
    public int nCopies() {
        return nCopies;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(" + arr.length + ")");
        builder.append("[");
        for (int i=0; i < size; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(arr[i] == null ? "" : arr[i]);
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String [] args) throws Exception {
        DynamicDoubleArray arr = new DynamicDoubleArray();
        arr.add(8.5);
        arr.add(12.1);
        arr.add(-5.7);
        System.out.println("array of size " + arr.size() + " : " + arr);
        assert arr.size() == 3;
        arr.add(1, 4.9);
        arr.add(2, 20.2);
        System.out.println("array of size " + arr.size() + " : " + arr);
        assert arr.size() == 5;
        double oldVal = arr.set(2, 25);
        System.out.println("old value at index 2 after replacing it with 25 = " + oldVal);
        assert oldVal == 20.2;
        System.out.println("Element at position 2 = " + arr.get(2));
        assert arr.get(2) == 25;
        System.out.println("array of size " + arr.size() + " : " + arr);
        assert arr.size() == 5;
        /*     Uncomment the following for homework 4 */
        double removedVal = arr.remove(0);
        System.out.println("Removed element at position 0 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 8.5;
        assert arr.size() == 4;
        removedVal = arr.remove(2);
        System.out.println("Removed element at position 2 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 12.1;
        assert arr.size() == 3;
        removedVal = arr.remove(2);
        System.out.println("Removed element at position 2 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == -5.7;
        assert arr.size() == 2;
        removedVal = arr.remove();
        System.out.println("Removed element at end = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 25;
        assert arr.size() == 1;
        removedVal = arr.remove();
        System.out.println("Removed element at end = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 4.9;
        assert arr.size() == 0;
        arr.add(67);
        arr.add(-14);
        arr.add(15);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size == 3;
        arr.add(9.5);
        arr.add(-14);
        arr.add(22);
        assert arr.size == 6;
        arr.removeRange(2,5);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size == 3;
        int[] nItemsArr = new int[]{0, 100000, 200000, 400000, 800000, 1600000, 3200000};
        DynamicDoubleArray arr1 = new DynamicDoubleArray();
        System.out.println("Using initial array capacity of 1...");
        long totalTime = 0;
        for (int k = 1; k < nItemsArr.length; k++) {
            for (int i = 0; i < nItemsArr[k] - nItemsArr[k - 1]; i++) {
                long startTime = System.currentTimeMillis();
                arr1.add(i + 1);
                long stopTime = System.currentTimeMillis();
                totalTime += (stopTime - startTime);
            }
            System.out.println("copy cost for inserting " + nItemsArr[k] + " items = " +
                    +arr1.nCopies());
            System.out.println("total time(ms) for inserting " + nItemsArr[k] + " items = " +
                    +totalTime);
        }
        DynamicDoubleArray arr2 = new DynamicDoubleArray(5000);
        System.out.println("Using initial array capacity of 5000...");
        totalTime = 0;
        for (int k = 1; k < nItemsArr.length; k++) {
            for (int i = 0; i < nItemsArr[k] - nItemsArr[k - 1]; i++) {
                long startTime = System.currentTimeMillis();
                arr2.add(i + 1);
                long stopTime = System.currentTimeMillis();
                totalTime += (stopTime - startTime);
            }
            System.out.println("copy cost for inserting " + nItemsArr[k] + " items = " +
                    +arr2.nCopies());
            System.out.println("total time(ms) for inserting " + nItemsArr[k] + " items = " +
                    +totalTime);
        }
        /* Uncomment the following for homework 4 */
        totalTime = 0;
        for (int k=1; k < nItemsArr.length; k++) {
            for (int i = 0; i < nItemsArr[k]-nItemsArr[k-1]; i++) {
                long startTime = System.currentTimeMillis();
                arr1.remove();
                long stopTime = System.currentTimeMillis();
                totalTime += (stopTime - startTime);
            }
            System.out.println("total time(ms) for deleting " + nItemsArr[k] + " items = " +
                    + totalTime);
        }
    }

}




