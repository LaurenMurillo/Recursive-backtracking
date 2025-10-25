/*
 * AnagramSolver.java - CS 314 Assignment 6
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Lauren Murillo
 * Email address 1: lnm2448@eid.utexas.edu
 * UTEID 1: lnm2448
 *
 * Name 2: Khanh-Hoa Nguyen
 * Email address 2: kpn397@eid.utexas.edu
 * UTEID 2: kpn397
 */

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Provides methods to find all possiblle anagrams for a given phrase.
 */
public class AnagramSolver {
    private TreeMap<String, LetterInventory> dictLetters;

    /**
     * Creates a new AnagramSolver object and stores the original words in the
     * dictionary and their corresponding letter inventories.
     * 
     * @param dictionary pre: list != null, list Contains the words to form
     *                   anagramsList from.
     */
    public AnagramSolver(Set<String> dictionary) {
        // check preconditions
        if (dictionary == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "AnagramSolver. dictionary parameter can not be null.");
        }
        dictLetters = new TreeMap<>();
        for (String word : dictionary) {
            dictLetters.put(word, new LetterInventory(word));
        }
    }

    /**
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     * 
     * @param s        the phrase that anagrams are formed from.
     * @param maxWords the maximum amount of words allowed in the generated
     *                 anagrams.
     * @return a list of anagrams that can be formed from s.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        // check preconditions
        if (s == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "getAnagrams. string parameter can not be null.");
        } else if (!s.matches(".*[a-zA-Z]+.*")) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "getAnagrams. string parameter must contain at least one English letter.");
        } else if (maxWords < 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "getAnagrams. maxWords parameter must be greater than or equal to 0.");
        }
        // preprocessing: filter words
        LetterInventory letters = new LetterInventory(s);
        ArrayList<String> eligible = new ArrayList<>();
        for (Map.Entry<String, LetterInventory> inventory : dictLetters.entrySet()) {
            if (letters.subtract(inventory.getValue()) != null) {
                eligible.add(inventory.getKey());
            }
        }
        List<List<String>> anagrams = new ArrayList<>();
        findAnagrams(letters, maxWords, 0, eligible, new ArrayList<>(), anagrams);
        Collections.sort(anagrams, new AnagramComparator());
        return anagrams;
    }

    /**
     * Find all the possible anagrams for a String phrase given the maximum amount
     * of words that can be in an anagram.
     * 
     * @param lettersLeft the letters left to be used in the anagram.
     * @param maxWords    the maximum amount of words that can be in an anagram.
     * @param wordIndex   the index of the eligible words.
     * @param eligible    a list of eligible words that can be in the anagram.
     * @param words       the words that are chosen for an anagram.
     * @param anagrams    a list of all anagrams for a String phrase.
     */
    private void findAnagrams(LetterInventory lettersLeft, int maxWords, int wordIndex,
            List<String> eligible, List<String> words, List<List<String>> anagrams) {
        if (lettersLeft.isEmpty()) {
            List<String> anagram = new ArrayList<>(words);
            Collections.sort(anagram);
            anagrams.add(anagram);
        } else if (maxWords == 0 || words.size() < maxWords) {
            for (int i = wordIndex; i < eligible.size(); i++) {
                LetterInventory letters = lettersLeft.subtract(dictLetters.get(eligible.get(i)));
                if (letters != null) {
                    words.add(eligible.get(i));
                    findAnagrams(letters, maxWords, i, eligible, words, anagrams);
                    words.remove(words.size() - 1);
                }
            }
        }
    }

    /**
     * Defines how two List<String> objects should be compared.
     */
    private static class AnagramComparator implements Comparator<List<String>> {
        /**
         * Compare one anagram to another anagram based on the number of words in it. If
         * they have the same number of words, compare the Strings lexicographically.
         * 
         * @param anagram1 the first anagram to compare to second anagram
         * @param anagram2 the second anagram to compare to the first anagram
         */
        @Override
        public int compare(List<String> anagram1, List<String> anagram2) {
            if (anagram1.size() != anagram2.size()) {
                return anagram1.size() - anagram2.size();
            }
            for (int i = 0; i < anagram1.size(); i++) {
                if (!anagram1.get(i).equals(anagram2.get(i))) {
                    return anagram1.get(i).compareTo(anagram2.get(i));
                }
            }
            return 0;
        }
    }
}