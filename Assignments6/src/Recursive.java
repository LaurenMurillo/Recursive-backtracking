/*
 * Recursive.java - CS 314 Assignment 6
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

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

/**
 * Various recursive methods to be implemented.
 */
public class Recursive {

    /**
     * Problem 1: Returns the number of elements in data that are followed
     * directly by value that is double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed
     * immediately by double the value
     *
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by
     *         a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        return doubleHelper(data); // Change as necessary
    }

    private static int doubleHelper(int[] array) {
        // base case
        if (array.length == 1) {
            return 0;
        } else if ((array[0] * 2) == array[1]) {
            // move to next element
            return 1 + doubleHelper(Arrays.copyOfRange(array, 1, array.length));
        } else {
            return 0 + doubleHelper(Arrays.copyOfRange(array, 1, array.length));
        }
    }

    /**
     * Problem 2: Draw a Sierpinski Carpet.
     *
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /*
     * Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     *
     * @param g The Graphics object to use to fill rectangles
     * 
     * @param size the size of the current square
     * 
     * @param limit the smallest allowable size of squares
     * 
     * @param x the x coordinate of the upper left corner of the current square
     * 
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {
        int newSize = size / 3;
        int newX = (int) x + newSize;
        int newY = (int) y + newSize;
        g.fillRect(newX, newY, newSize, newSize);
        // base case
        if (newSize >= limit) {
            // moving position
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int moveX = j * newSize + (int) x;
                    int moveY = i * newSize + (int) y;
                    drawSquares(g, newSize, limit, moveX, moveY);
                }
            }
        }
    }

    /**
     * Problem 3: Find the minimum difference possible between teams based on
     * ability scores. The number of teams may be greater than 2. The goal is
     * to minimize the difference between the team with the maximum total
     * ability and the team with the minimum total ability.
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * post: return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     *
     * @param numTeams  the number of teams to form
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team with the
     *         maximum total ability and the team with the minimum total ability.
     *         The
     *         return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        // int lowestSum = 0;
    	// int greatestSum = 0;
        // int otherSum = 0;
    	// for (int i = 0; i < abilities.length; i++) {
    	// 	if (abilities[i] > greatestSum) {
        //         lowestSum = abilities[i];
        //     }
        //     if (abilities[i] < lowestSum) { 
        //         greatestSum = abilities[i];
        //     }
    	// }
        // return -1;
        
        // sums of each team
        int[] sums = new int[numTeams];

        // number of people in each team
        int[] numPeople = new int[numTeams];
        return helper(numPeople, abilities, sums, 0);
    }
    private static int helper(int[] numPeople, int[] abilities, int[] sums, int personIndex) {
        if (personIndex == abilities.length) {
            // base case
            for (int count : numPeople) {
                // invalid
                if (count == 0) {
                    return Integer.MAX_VALUE;
                }
            }
            // valid
            int maxScore = Integer.MIN_VALUE;
            int minScore = Integer.MAX_VALUE;
            for (int sum : sums) {
                maxScore = Math.max(maxScore, sum);
                minScore = Math.min(minScore, sum);
            }
            return maxScore - minScore;
        }
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < sums.length; i++) {
            // CREATE A NEW POSSIBLE TEAM
            // for each team slot, add the ability # of the personIndex to the current sum
            sums[i] += abilities[personIndex];
            // for this team, add to number of people
            numPeople[i]++;
            // for this team, continue to add the NEXT ability to the current sum
            minDiff = Math.min(minDiff, helper(numPeople, abilities, sums, personIndex + 1));
            // backtracking
            sums[i] -= abilities[personIndex];
            numPeople[i]--;
        }
        return minDiff;
    }
}