/*
 * AnagramSolver.java - CS 314 Assignment 6
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1:
 * Email address 1:
 * UTEID 1:
 *
 * Name 2:
 * Email address 2:
 * UTEID 2:
 */

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnagramSolver {
    private TreeMap<String, LetterInventory> dictInven;

    /*
     * pre: list != null
     *
     * @param list Contains the words to form anagramsList from.
     */
    public AnagramSolver(Set<String> dictionary) {
        // preconditions
        if (dictionary == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "AnagramSolver. dictionary parameter can not be null.");
        }
        dictInven = new TreeMap<>();
        for (String element : dictionary) {
            dictInven.put(element, new LetterInventory(element));
        }
    }

    /*
     * Return a list of anagramsList that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        // preconditions

        // preprocessing: filter words
        LetterInventory word = new LetterInventory(s);
        ArrayList<String> eligible = new ArrayList<>();
        for (Map.Entry<String, LetterInventory> inventory : dictInven.entrySet()) {
            // LetterInventory subtracted = word.subtract(inventory.getValue());
            if (word.subtract(inventory.getValue()) != null) {
                eligible.add(inventory.getKey());
            }
        }
        // System.out.println(eligible);

        List<List<String>> anagramsList = new ArrayList<>();
        findAnagram(word, eligible, new ArrayList<>(), anagramsList, maxWords, 0);
        // sort anagramsList based on assignment's sorting method
        Collections.sort(anagramsList, new AnagramComparator());
        return anagramsList; // Change this return statement as necessary
    }

    private void findAnagram(LetterInventory lettersLeft, List<String> eligible, List<String> chosen,
            List<List<String>> anagramsList, int maxWords, int wordIndex) {
        // base case: all letters used
        if (lettersLeft.isEmpty()) {
            // pop pop pop
            List<String> anagram = new ArrayList<>(chosen);
            // sort words in lexicographical
            Collections.sort(anagram);
            anagramsList.add(anagram);
        } else if (maxWords == 0 || chosen.size() < maxWords) {
            for (int i = wordIndex; i < eligible.size(); i++) {
                LetterInventory subtracted = lettersLeft.subtract(dictInven.get(eligible.get(i)));
                if (subtracted != null) {
                    chosen.add(eligible.get(i));
                    // backtracking
                    findAnagram(subtracted, eligible, chosen, anagramsList, maxWords, i);
                    chosen.remove(chosen.size() - 1);
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
                if (anagram1.get(i).compareTo(anagram2.get(i)) != 0) {
                    return anagram1.get(i).compareTo(anagram2.get(i));
                }
            }
            return 0;
        }
    }
}