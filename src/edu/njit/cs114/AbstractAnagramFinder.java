package edu.njit.cs114;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Author: Ravi Varadarajan
 * Date created: 3/20/2024
 */
public abstract class AbstractAnagramFinder {

    /**
     * Clear the data structure
     */
    abstract public void clear();

    /**
     * Add a word to the data structure
     * @param word
     */
    abstract public void addWord(String word);

    /**
     * Return list of groups of anagram words.txt which have most anagrams
     * @return
     * @throws Exception
     */
    abstract public List<List<String>> getMostAnagrams();

    /**
     * Process words.txt of a dictionary file for finding anagrams of words.txt
     * @param dictionaryFile
     * @return number of words.txt in the dictionary file
     * @throws IOException
     */
    public int processDictionary(String dictionaryFile) throws IOException {
        clear();
        BufferedReader fileReader = new BufferedReader(new FileReader(dictionaryFile));
        String line = null;
        int maxLen = 0;
        int nWords = 0;
        while ((line = fileReader.readLine()) != null) {
            String word = line.trim().toLowerCase();
            addWord(word);
            nWords++;
        }
        fileReader.close();
        return nWords;
    }

}
