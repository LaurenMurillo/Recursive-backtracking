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
import java.util.HashMap;

public class AnagramSolver {
    private HashMap<String, LetterInventory> wordInventory;

    /*
     * pre: list != null
     *
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        // preconditions
        if (dictionary == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "AnagramSolver. dictionary parameter can not be null.");
        }
        for (String element : dictionary) {
            wordInventory.put(element, new LetterInventory(element));
        }
    }

    /*
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        // CS 314 Students: add your code here.
        return null; // Change this return statement as necessary
    }

}