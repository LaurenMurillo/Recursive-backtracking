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
        // check preconditions
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        return countDoubles(data, 0, 0);
    }

    /**
     * Helper method for nextIsDouble
     * Count the number of elements in array that are followed directly by value
     * that is double that element.
     * 
     * @param array The array to search
     * @return the number of elements that are followed directly by double that
     *         element.
     */
    private static int countDoubles(int[] array, int index, int count) {
        if (index == array.length - 1) {
            return count;
        }
        if ((array[index] * 2) == array[index + 1]) {
            count++;
        }
        return countDoubles(array, index + 1, count);
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
        final int DIVIDE_GRID = 3;
        int newSize = size / DIVIDE_GRID;
        int newX = (int) x + newSize;
        int newY = (int) y + newSize;
        g.fillRect(newX, newY, newSize, newSize);
        if (newSize >= limit) {
            for (int i = 0; i < DIVIDE_GRID; i++) {
                for (int j = 0; j < DIVIDE_GRID; j++) {
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
     *         The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        // check preconditions
        if (numTeams < 2) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "minDifference. numTeams parameter must be at least 2.");
        } else if (abilities == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "minDifference. abilities parameter can not be null.");
        } else if (abilities.length < numTeams) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "minDifference. abilities length must be at least number of teams to form.");
        }
        int[] sums = new int[numTeams];
        int[] numPeople = new int[numTeams];
        return getMinDiff(numPeople, abilities, sums, 0);
    }

    /**
     * Helper method for minDifference
     * Creates all valid sets of teams and gets the minimum difference
     * possible between teams based on ability scores.
     * 
     * @param numPeople   list of number of people in each team
     * @param abilities   the ability scores of the people to distribute
     * @param sums        list of every team's total abilities
     * @param personIndex current person in abilities list
     * @return the minimum possible difference between the team with the
     *         maximum total ability and the team with the minimum total ability.
     */
    private static int getMinDiff(int[] numPeople, int[] abilities, int[] sums, int personIndex) {
        if (personIndex == abilities.length) {
            for (int count : numPeople) {
                if (count == 0) {
                    return Integer.MAX_VALUE;
                }
            }
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
            sums[i] += abilities[personIndex];
            numPeople[i]++;
            minDiff = Math.min(minDiff, getMinDiff(numPeople, abilities, sums, personIndex + 1));
            sums[i] -= abilities[personIndex];
            numPeople[i]--;
        }
        return minDiff;
    }
}