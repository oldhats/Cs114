package edu.njit.cs114;

import java.io.IOException;
import java.util.*;

/**
 * Author: Ravi Varadarajan
 * Date created: 3/22/2024
 */
public class AnagramFinderListImpl extends AbstractAnagramFinder {

    private List<WordChArrPair> wordChArrPairList = new ArrayList<>();


    private class WordChArrPair implements Comparable<WordChArrPair> {
        public final String word;
        public final char [] charArr;

        public WordChArrPair(String word) {
            this.word = word;
            charArr = word.toCharArray();
            Arrays.sort(charArr);
        }

        public boolean isAnagram(WordChArrPair wordArrPair) {
            /**
             * To be completed
             * Compare charArr already sorted using Arrays.equals() method
             */

            // Create a copy of the character array from the provided WordChArrPair
            char[] sortedWordArrPair = Arrays.copyOf(wordArrPair.charArr, wordArrPair.charArr.length);
            // Sort the copied character array
            Arrays.sort(sortedWordArrPair);

            // Compare the sorted character arrays
            // If they are equal, the words are anagrams
            return Arrays.equals(sortedWordArrPair, charArr);
        }


        @Override
        public int compareTo(WordChArrPair wordArrPair) {
            return this.word.compareTo(wordArrPair.word);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof WordChArrPair)) {
                return false;
            }
            WordChArrPair other = (WordChArrPair) obj;
            return this.word.equals(other.word);
        }
    }




    @Override
    public void clear() {
        wordChArrPairList.clear();
    }

    @Override
    public void addWord(String word) {
        WordChArrPair wordChArrPair = new WordChArrPair(word);
        if (!wordChArrPairList.contains(wordChArrPair)) {
            wordChArrPairList.add(wordChArrPair);
        }
    }

    @Override
    public List<List<String>> getMostAnagrams() {
        // Create a new list to store WordChArrPair objects and sort it
        List<WordChArrPair> wordArrPairList = new ArrayList<>(wordChArrPairList);
        Collections.sort(wordArrPairList);

        // Create a list to store groups of anagram words with the most anagrams
        List<List<String>> mostAnagramsList = new ArrayList<>();

        // Initialize the maximum size of an anagram group
        int maxAnagramSize = 0;

        // Repeat until wordArrPairList is empty
        while (!wordArrPairList.isEmpty()) {
            // Get and remove the first element from the list
            WordChArrPair firstElem = wordArrPairList.remove(0);

            // Create a new list to store anagrams of the first element
            List<String> anagramList = new ArrayList<>();
            // Add the first element to the anagram list
            anagramList.add(firstElem.toString());

            // Iterate over the remaining elements in the list
            Iterator<WordChArrPair> iterator = wordArrPairList.iterator();
            while (iterator.hasNext()) {
                WordChArrPair currentPair = iterator.next();
                // If the current element is an anagram of the first element
                if (firstElem.isAnagram(currentPair)) {
                    // Add it to the anagram list and remove it from wordArrPairList
                    anagramList.add(currentPair.toString());
                    iterator.remove();
                }
            }

            // Update the maximum size of an anagram group
            if (anagramList.size() > maxAnagramSize) {
                // If the current anagram list is larger, clear the mostAnagramsList
                mostAnagramsList.clear();
                // Add the new anagram list to the mostAnagramsList
                mostAnagramsList.add(anagramList);
                // Update the maximum anagram size
                maxAnagramSize = anagramList.size();
            } else if (anagramList.size() == maxAnagramSize) {
                // If the size is the same, add the anagram list to the mostAnagramsList
                mostAnagramsList.add(anagramList);
            }
        }

        // Return the list of groups with the most anagrams
        return mostAnagramsList;
    }


    public static void main(String [] args) {
        AnagramFinderListImpl finder = new AnagramFinderListImpl();
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
