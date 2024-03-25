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
              return Arrays.equals(wordArrPair.charArr,charArr); // to be removed once function is completed.
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
        List<WordChArrPair> wordArrPairList = new ArrayList<>(wordChArrPairList);
        Collections.sort(wordArrPairList);
        ArrayList<List<String>> mostAnagramsList = new ArrayList<>();
        /**
         * To be completed
         *  Note : use isAnagram()method of WordArrPair to identify an anagram
         */


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
