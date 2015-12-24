package DataStructures.sort;

import java.util.List;

import DataStructures.Util;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 */


public class InsertionSort {

    /**
     * Implementation of pair insertion sort for arrays of Comparable elements
     * Stable, in place.
     *
     * @param elements - the array of Comparable elements to be sorted
     */
    public static void sort(Comparable[] elements) {
        sort(elements, 0, elements.length);
    }

    /**
     * Implementation of pair insertion sort for arrays of Comparable elements
     * Stable, in place.
     *
     * @param elements - the array of Comparable elements to be sorted
     * @param start    - the index to start at (inclusive)
     * @param end      - the index to end at (exclusive)
     */
    public static void sort(Comparable[] elements, int start, int end) {
        Comparable smallest, secondSmallest, temp;
        int smallestIndex, secondSmallestIndex;


        for (int i = start; i < end - 1; i += 2) {
            //Sets two smallest to first two elements in order
            if (elements[i].compareTo(elements[i + 1]) <= 0) {
                smallestIndex = i;
                smallest = elements[i];
                secondSmallestIndex = i + 1;
                secondSmallest = elements[secondSmallestIndex];
            } else {
                smallestIndex = i + 1;
                smallest = elements[smallestIndex];
                secondSmallest = elements[i];
                secondSmallestIndex = i;
            }

            //Finds two smallest
            for (int j = i + 2; j < end; j++) {
                temp = elements[j];
                if (temp.compareTo(secondSmallest) < 0) {
                    if (temp.compareTo(smallest) < 0) {
                        secondSmallest = smallest;
                        secondSmallestIndex = smallestIndex;
                        smallest = temp;
                        smallestIndex = j;
                    } else {
                        secondSmallest = temp;
                        secondSmallestIndex = j;
                    }
                }
            }

            /*!IMPORTANT!*/
            //If any of the first 2 elements need to be swapped to the other spot, directly swapping may override them
            //Need to swap the original values, and do this swap first, then do the other swap.
            Comparable firstValue = elements[i];
            Comparable secondValue = elements[i + 1];
            elements[i] = smallest;
            elements[i + 1] = secondSmallest;

            if (smallestIndex == i + 1) {
                elements[secondSmallestIndex] = firstValue;
            } else if (secondSmallestIndex == i) {
                elements[smallestIndex] = secondValue;
            } else {
                elements[smallestIndex] = firstValue;
                elements[secondSmallestIndex] = secondValue;
            }
        }
    }
}
