public class LetterInventory {
    //frequency of letters in a given word or phrase
    //can do a map(String letter, int frequency)
    private final int alphabetSize;
    private int[] alphaFreq;//frequency of each letter of the alphabet
    private int total;//total letters in inventory

    public LetterInventory() {
        alphabetSize = 26;
        alphaFreq = new int[alphabetSize];
        total = 0;
    }

    public LetterInventory(String s){
        //pre condition
        if (s == null) {
            throw new IllegalArgumentException();
        }
        //instantiating variables:
        alphabetSize = 26;
        alphaFreq = new int[alphabetSize];
        total = 0;
        //setting frequencies in array:
        char firstLetter = 'a';
        for (int i = 0; i < s.length(); i++) {
            char currL = s.charAt(i);
            if (Character.isLetter(currL)) {
                alphaFreq[Character.toLowerCase(currL) - firstLetter]++;
                total++;
            }
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
        int index =  Character.toLowerCase(letter) - 'a';
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
        StringBuilder letters = new StringBuilder();
        char currLetter = 'a';
        for (int i=0; i<alphaFreq.length; i++) {
            for (int j=0; j<alphaFreq[i]; j++){
                letters.append(String.valueOf(currLetter));
            }
            currLetter += 1;
        }
        return letters.toString();
    }

    public LetterInventory add(LetterInventory l) {
        if (l == null) {
            throw new IllegalArgumentException();
        }
        LetterInventory combined = new LetterInventory();
        int[] tempFreqList = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; i++) {
            tempFreqList[i] = alphaFreq[i] + l.alphaFreq[i];
        }
        combined.alphaFreq = tempFreqList;
        combined.total = this.total + l.total;
        return combined;
    }

    public LetterInventory subtract(LetterInventory l) {
        if (l == null) {
            throw new IllegalArgumentException();
        }
        int[] tempFreqList = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; i++) {
            tempFreqList[i] = alphaFreq[i] - l.alphaFreq[i];
            if (tempFreqList[i] < 0) {
                return null;
            }
        }
        LetterInventory subtracted = new LetterInventory();
        subtracted.alphaFreq = tempFreqList;
        //subtracted.og = subtracted.toString();
        subtracted.total = total - l.total;
        return subtracted;
    }

    public boolean equals(Object obj) {
        //pre condition
        if (obj instanceof LetterInventory) {
            LetterInventory other = (LetterInventory) obj;
            if (total != other.total) {
                return false;
            }

            for (int i = 0; i < alphabetSize; i++) {
                if (alphaFreq[i] != other.alphaFreq[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
