package DataStructures.sort;

import java.util.List;

import DataStructures.*;

import java.util.concurrent.*;


/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * An implementation of QuickSort base on the DualPivotQuicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch,
 * borrowing the pivot selection technique and dual/single pivot determination technique
 */
public class QuickSort {

    public static final int INSERTION_SORT_THRESHOLD = 16;//Lower equal than this size arrays (subarrays) will use insertion sort. A guess for now, though can't be too small for 7th spliting to work


    public static void sort(Comparable[] elements) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        //Stack storing beginning and end indices of subarrays, pushed in start, end order
        Stack<IntPair> subArrays = new Stack<IntPair>((int) (Math.log(elements.length) / Math.log(3) + 1) << 2); //Twice the minimum length

        subArrays.push(new IntPair(0, elements.length));

        while (!subArrays.isEmpty()) {
            IntPair startEnd = subArrays.pop();
            int length = startEnd.secondIndex - startEnd.firstIndex;

            if (length <= INSERTION_SORT_THRESHOLD) {
                IntPair pivots = pivotSelect(elements, length, startEnd);
                if (pivots.secondIndex != pivots.firstIndex){ //Dual pivot sort because found 2 unique pivots

                }
                else { //Single pivot sort because found identical elements

                }

            }
        }
    }

    public static IntPair pivotSelect(Comparable[] elements, int length, IntPair startEnd){
        // Inexpensive approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        //Sorts these 5 sevenths to approximate median and terciles. Borrowed from the original implementation
        int e3 = (startEnd.firstIndex + startEnd.secondIndex) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (elements[e2].compareTo(elements[e1]) < 0) {
            Comparable t = elements[e2];
            elements[e2] = elements[e1];
            elements[e1] = t;
        }

        if (elements[e3].compareTo(elements[e2]) < 0) {
            Comparable t = elements[e3];
            elements[e3] = elements[e2];
            elements[e2] = t;
            if (t.compareTo(elements[e1]) < 0) {
                elements[e2] = elements[e1];
                elements[e1] = t;
            }
        }
        if (elements[e4].compareTo(elements[e3]) < 0) {
            Comparable t = elements[e4];
            elements[e4] = elements[e3];
            elements[e3] = t;
            if (t.compareTo(elements[e2]) < 0) {
                elements[e3] = elements[e2];
                elements[e2] = t;
                if (t.compareTo(elements[e1]) < 0) {
                    elements[e2] = elements[e1];
                    elements[e1] = t;
                }
            }
        }
        if (elements[e5].compareTo(elements[e4]) < 0) {
            Comparable t = elements[e5];
            elements[e5] = elements[e4];
            elements[e4] = t;
            if (t.compareTo(elements[e3]) < 0) {
                elements[e4] = elements[e3];
                elements[e3] = t;
                if (t.compareTo(elements[e2]) < 0) {
                    elements[e3] = elements[e2];
                    elements[e2] = t;
                    if (t.compareTo(elements[e1]) < 0) {
                        elements[e2] = elements[e1];
                        elements[e1] = t;
                    }
                }
            }
        }
        if (elements[e1] != elements[e2] && elements[e2] != elements[e3] && elements[e3] != elements[e4] && elements[e4] != elements[e5]) {
            return new IntPair(e2, e4);
        }
        return new IntPair(e3, e3);
    }


    public static void sort(List<Comparable> elements) {

    }
}
