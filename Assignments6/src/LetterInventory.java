public class LetterInventory {
    //frequency of letters in a given word or phrase
    //can do a map(String letter, int frequency)
    private final int alphabetSize;
    private int[] alphaFreq;//frequency of each letter of the alphabet
    private int total;
    private String og;

    public LetterInventory(String s){
        //pre condition
        if (s == null) {
            throw new IllegalArgumentException();
        }
        total = 0;
        og = s;
        alphabetSize = 26;
        alphaFreq = new int[alphabetSize];
        char currLetter = 'a';
        for (int j = 0; j < alphaFreq.length; j++) {
            for (int i = 0; i < s.length(); i++) {
                if ((s.substring(i,i+1).toLowerCase()).equals(String.valueOf(currLetter))) {
                    alphaFreq[j]++;
                    total++;
                }
            }
            currLetter += 1;
        }
    }

    //returns frequncy of a particular letter
    public int get(char letter) {
        //precondition, letter must be an english letter
        //if (!('a' <= letter && letter <= 'z') || !('A' <= letter && letter <= 'Z')) {
        if (!Character.isLetter(letter)) {//not sure if this argument is allowed (Character class)
            throw new IllegalArgumentException();
        }
        //know we know the following math would generate a valid index
        String lowerCase = String.valueOf(letter).toLowerCase();
        int index =  lowerCase.charAt(0) - 'a';
        return alphaFreq[index];
    }

    public int size() {
        return total;
    }

    public boolean isEmpty() {
        return total == 0;
    }

    //char to a string so we can add it to our string of all letters
    public String toString() {
        String letters = "";
        char currLetter = 'a';
        for (int i=0; i<alphaFreq.length; i++) {
            for (int j=0; j<alphaFreq[i]; j++){
                letters += String.valueOf(currLetter);
            }
            currLetter += 1;
        }
        return letters;
    }

    public LetterInventory add(LetterInventory l) {
        if (l == null) {
            throw new IllegalArgumentException();
        }
        LetterInventory combined = new LetterInventory(l.og+og);
        return combined;
    }

    public LetterInventory subtract(LetterInventory l) {
        if (l == null) {
            throw new IllegalArgumentException();
        }
        int[] tempFreqList = new int[alphabetSize];
        char currLetter = 'a';
        for (int i = 0; i < alphabetSize; i++) {
            tempFreqList[i] = alphaFreq[i] - l.get(currLetter);
            if (tempFreqList[i] < 0) {
                return null;
            }
            currLetter += 1;
        }
        String letters = "";
        currLetter = 'a';
        for (int i=0; i<tempFreqList.length; i++) {
            for (int j=0; j<tempFreqList[i]; j++) {
                letters += String.valueOf(currLetter);
            }
            currLetter += 1;
        }
        LetterInventory subtracted = new LetterInventory(letters);
        return subtracted;
    }

    public boolean equals(Object obj) {
        //pre condition
        if (obj instanceof LetterInventory) {
            return toString().equals(obj.toString());
        }
        return false;
    }
}
