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

    /*
     * Return a list of anagramsList that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
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
        // boolean hasEnglishLetter = false;
        // for (char letter : s.toCharArray()) {
        // char lower = Character.toLowerCase(letter);
        // if ('a' <= lower && lower <= 'z') {
        // hasEnglishLetter = true;
        // }
        // }
        // if (!hasEnglishLetter) {
        // throw new IllegalArgumentException("Violation of precondition: "
        // + "getAnagrams. string parameter must contain at least one English letter.");
        // }

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

    private void findAnagrams(LetterInventory lettersLeft, int maxWords, int wordIndex,
            List<String> eligible, List<String> words, List<List<String>> anagrams) {
        if (lettersLeft.isEmpty()) {
            List<String> anagram = new ArrayList<>(words);
            Collections.sort(new ArrayList<>(words));
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

    private static class AnagramComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> anagram1, List<String> anagram2) {
            // sort by # of words, fewest words coming first
            if (anagram1.size() != anagram2.size()) {
                return anagram1.size() - anagram2.size();
            }
            // sort by string
            for (int i = 0; i < anagram1.size(); i++) {
                if (!anagram1.get(i).equals(anagram2.get(i))) {
                    return anagram1.get(i).compareTo(anagram2.get(i));
                }
            }
            return 0;
        }
    }
}