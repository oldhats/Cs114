package edu.njit.cs114;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Ravi Varadarajan
 * Date created: 3/20/2024
 */
public class AnagramFinderHashTableImpl extends AbstractAnagramFinder {

    private static final int [] primes = new int [] {2 , 3 , 5 , 7 , 11 , 13 , 17 , 19 ,
            23 , 29 , 31 , 37 , 41 , 43 , 47 ,
            53 , 59 , 61 , 67 , 71 , 73 , 79 ,
            83 , 89 , 97 , 101};

    private Map<Character,Integer> letterMap = new HashMap<>();

    /**
     * To be completed: Initialize anagramTable
     */
    private Map<Long, ArrayList<String>> anagramTable = new HashMap<>();


    private void buildLetterMap() {
        /**
         * To be completed
         * Assign each lower case letter to a prime number from
         * primes array, starting from 'a'. Use a for-loop to do this. Do not use
         *  26 assignment statements.
         */
        // Assign each letter to a prime number
        for (int i = 0; i < 26; i++)
        { letterMap.put((char)((int) 'a' + i), primes[i]); }
    }

    public AnagramFinderHashTableImpl() {
        buildLetterMap();
    }

    /**
     * Finds hash code for a word using prime number factors
     * @param word
     * @return
     */
    public Long myHashCode(String word) {
        /**
         * To be completed
         * Use the product of powers of primes that characters of word are mapped to.
         * It should be the same for all anagrams of a word
         */
        Long product = 1L;


        // Multiply the prime number associated with each letter in the word
        for(int i = 0; i < word.length(); i++){
        product *= letterMap.get(word.charAt(i));
        }

        // Return the product
        return product;
    }

    /**
     * Add the word to the anagram table using hash code
     * @param word
     */
    @Override
    public void addWord(String word) {
        /**
         * To be completed
         * Use myHashCode to construct the key and add word to the anagram table
         */

        // Get the key for the word
        Long key = myHashCode(word);
        ArrayList<String> wordList = anagramTable.get(key);


        // If the key is not in the table, add it
        if (wordList == null) {
            ArrayList<String> newWordList = new ArrayList<>();
            anagramTable.put(key,newWordList);
        }

        // Add the word to the list of words with the same key
        anagramTable.get(key).add(word);

    }

    @Override
    public void clear() {
        anagramTable.clear();
    }


    /**
     * Return list of groups of anagram words which have most anagrams
     * @return
     * @throws Exception
     */
    @Override
    public List<List<String>> getMostAnagrams() {
        ArrayList<List<String>> mostAnagramsList = new ArrayList<>();
        /**
         * To be completed
         */

        int maxAnagramListSize = 0;

        // Find the maximum number of anagrams
        for (ArrayList<String> anagramWordList : anagramTable.values()){
            if(anagramWordList.size() > maxAnagramListSize) {
            maxAnagramListSize = anagramWordList.size();
            }
        }

        // Add all the lists with the maximum number of anagrams to the mostAnagramsList
        for (ArrayList<String> anagramWordList : anagramTable.values()) {
            if (anagramWordList.size() == maxAnagramListSize) {
                mostAnagramsList.add(anagramWordList);
            }
        }


            return mostAnagramsList;
    }

    public static void main(String [] args) {
        AnagramFinderHashTableImpl finder = new AnagramFinderHashTableImpl();
        finder.clear();
        long startTime = System.nanoTime();
        int nWords=0;
        try {
            nWords = finder.processDictionary("words.txt");
        } catch (IOException e) {
            e.printStackTrace ();
        }
        List<List<String>> anagramGroups = finder.getMostAnagrams();
        long estimatedTime = System.nanoTime () - startTime ;
        double seconds = ((double) estimatedTime /1000000000) ;
        System.out.println("Number of words : " + nWords);
        System.out.println("Number of groups of words with maximum anagrams : "
                + anagramGroups.size());
        if (!anagramGroups.isEmpty()) {
            System.out.println("Length of list of max anagrams : " + anagramGroups.get(0).size());
            for (List<String> anagramGroup : anagramGroups) {
                System.out.println("Anagram Group : "+ anagramGroup);
            }
        }
        System.out.println ("Time (seconds): " + seconds);

    }


}
