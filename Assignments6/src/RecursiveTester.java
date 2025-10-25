/*
 * RecursiveTester.java - CS 314 Assignment 6
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
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {
    // run the tests
    public static void main(String[] args) {
        //test cases for nextIsDouble()
        int[] numsForDouble = { 22, 44, 0, 2, 11, 22};
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 2;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 1 passed. next is double.");
        } 
        else {
            System.out.println("Test 1 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        int[] numsForDoubleTwo = {0, 0, 1, 2, 4, 20, 41};
        actualDouble = Recursive.nextIsDouble(numsForDoubleTwo);
        expectedDouble = 3;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 2 passed. next is double.");
        } 
        else {
            System.out.println("Test 2 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }
        //test cases for minDifference()
        int[] abilities = { 4, 7, 8, 1, 10, 12};
        showFairTeamsResults(Recursive.minDifference(3, abilities), 2, 3);
        int[] abilitiesTwo = { 1, 20, 11, 7, 6, 3, 44};
        showFairTeamsResults(Recursive.minDifference(3, abilitiesTwo), 20, 4);
    }

    // Show the results of a fair teams test by comparing actual and expected
    // result.
    private static void showFairTeamsResults(int actual, int expected, int testNum) {
        if (actual == expected) {
            System.out.println("Test " + testNum + " passed. min difference.");
        } else {
            System.out.println("Test " + testNum + " failed. min difference.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
    }
}