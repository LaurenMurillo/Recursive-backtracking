/*
 * LetterInventory.java - CS 314 Assignment 6
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

/**
 * Represents the frequency of letters for a given word or phrase.
 * Stores the number of times each English letter, 'a' through 'z', occurs in
 * the word or phrase.
 */
public class LetterInventory {
    private final int ALPHABET_SIZE;
    private int[] letterFreq;
    private int numLetters;

    /**
     * Creates a new LetterInventory object and instantiates ALPHABET_SIZE and
     * letter frequency list size.
     */
    public LetterInventory() {
        ALPHABET_SIZE = 26;
        letterFreq = new int[ALPHABET_SIZE];
    }

    /**
     * Creates a new LetterInventory object and stores valid letter frequencies in
     * a list.
     * 
     * @param phrase pre: != null, phrase to determine letter frequencies
     */
    public LetterInventory(String phrase) {
        this();
        // checks preconditions
        if (phrase == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "LetterInventory. phrase parameter can not be null.");
        }
        for (char letter : phrase.toCharArray()) {
            char currLetter = Character.toLowerCase(letter);
            if ('a' <= currLetter && currLetter <= 'z') {
                letterFreq[currLetter - 'a']++;
                numLetters++;
            }
        }
    }

    /**
     * Returns the frequency of a given letter in this LetterInventory.
     * 
     * @param letter pre: char must be an English letter, the letter to get the
     *               frequency of.
     * @return frequency of a given letter.
     */
    public int get(char letter) {
        char lower = Character.toLowerCase(letter);
        // check preconditions
        if ('a' > lower || lower > 'z') {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "get. letter parameter must be an English letter.");
        }
        return letterFreq[lower - 'a'];
    }

    /**
     * Returns the total number of letters in this LetterInventory.
     * 
     * @return total number of letters.
     */
    public int size() {
        return numLetters;
    }

    /**
     * Returns true if the size of this LetterInventory is 0, false otherwise.
     * 
     * @return true if the size of this LetterInventory is 0.
     */
    public boolean isEmpty() {
        return numLetters == 0;
    }

    /**
     * Returns a String representation of this LetterInventory.
     * All letters are listed in alphabetical order.
     * 
     * @return a String representation of this LetterInventory.
     */
    public String toString() {
        StringBuilder letters = new StringBuilder(numLetters);
        for (int index = 0; index < letterFreq.length; index++) {
            if (letterFreq[index] > 0) {
                char letter = (char) ('a' + index);
                for (int j = 0; j < letterFreq[index]; j++) {
                    letters.append(letter);
                }
            }
        }
        return letters.toString();
    }

    /**
     * Add the frequencies from the calling LetterInventory object to the
     * frequencies of the letters in the explicit parameter.
     * 
     * @param other pre: != null, LetterInventory object to add frequencies with.
     *              post: neither calling object or explicit parameter are altered.
     * @return a LetterInventory created by adding the frequencies.
     */
    public LetterInventory add(LetterInventory other) {
        // check preconditions
        if (other == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "add. other parameter can not be null.");
        }
        LetterInventory sum = new LetterInventory();
        for (int index = 0; index < ALPHABET_SIZE; index++) {
            sum.letterFreq[index] = letterFreq[index] + other.letterFreq[index];
        }
        sum.numLetters = numLetters + other.numLetters;
        return sum;
    }

    /**
     * Subtract the letter frequencies of the explicit parameter from the calling
     * LetterInventory's letter frequencies.
     * 
     * @param other pre: != null, the LetterInventory object which frequencies
     *              are being subtracted from calling LetterInventory object's
     *              letter frequencies.
     *              post: neither calling object or explicit parameter are altered.
     * @return a LetterInventory created by subtracting the frequencies.
     */
    public LetterInventory subtract(LetterInventory other) {
        // check preconditions
        if (other == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "subtract. other parameter can not be null.");
        }
        LetterInventory difference = new LetterInventory();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (letterFreq[i] - other.letterFreq[i] < 0) {
                return null;
            }
            difference.letterFreq[i] = letterFreq[i] - other.letterFreq[i];
        }
        difference.numLetters = numLetters - other.numLetters;
        return difference;
    }

    /**
     * Determines if two LetterInventorys are equal to each other.
     * Two LetterInventorys are equal if the frequency for each letter in the
     * alphabet is the same.
     * 
     * @param other The other object to compare this LetterInventory to.
     * @return true if the two LetterInventorys are equal to each other, false
     *         otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LetterInventory)) {
            return false;
        }
        LetterInventory otherInven = (LetterInventory) other;
        if (numLetters != otherInven.numLetters) {
            return false;
        }
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (letterFreq[i] != otherInven.letterFreq[i]) {
                return false;
            }
        }
        return true;
    }
}
