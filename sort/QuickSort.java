package DataStructures.sort;

import java.util.List;

import DataStructures.*;


/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * An implementation of QuickSort based on the logic behind DualPivotQuicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch,
 * borrowing the pivot selection and dual/single pivot determination techniques.
 * <p/>
 * Uses a minimal processing overhead fixed size stack that always requires O(n) more memory for speed purposes.
 * Array version is in place and iterative.
 * List version returns a sorted copy.
 */
public class QuickSort {

    public static final int INSERTION_SORT_THRESHOLD = 16;//Lower equal than this size arrays (subarrays) will use insertion sort. A guestimate for now, though can't be too small for 7th splitting to work

    /**
     * An in place, iterative implementation of QuickSort, combining Dual/Single pivot QuickSort and insertion sort for small arrays.
     * Based on the logic behind DualPivotQuicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch
     *
     * @param elements - the array of Comparable elements to sort
     */
    public static void sort(Comparable[] elements) {
        sort(elements, 0, elements.length);
    }

    /**
     * An in place, iterative implementation of QuickSort, combining Dual/Single pivot QuickSort and insertion sort for small arrays.
     * Based on the logic behind DualPivotQuicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch
     *
     * @param elements - the array of Comparable elements to sort
     * @param start    - the index to begin sorting at (inclusive)
     * @param end      - the index to stop sorting at (exclusive)
     */
    @SuppressWarnings("unchecked")
    public static void sort(Comparable[] elements, int start, int end) {

        //Stack storing beginning and end indices of subarrays, popped in start, end order.
        FastIntStack subArrays = new FastIntStack(end - start + 2);//Enough for worse case

        subArrays.push(end);
        subArrays.push(start);

        while (!subArrays.isEmpty()) {
            int left = subArrays.pop();
            int right = subArrays.pop();
            int length = right - left;

            if (length <= INSERTION_SORT_THRESHOLD) { //Insertion sort under threshold
                InsertionSort.sort(elements, left, right);
            } else { //Quicksort otherwise

                //Stores the outer bounds of the array to be sorted
                int startIndex = left;
                int endIndex = right;

                int[] pivots = pivotSelect(elements, length, left, right); //Calls pivotSelect to generate one or two pivots

                if (pivots[2] < 0) { //Dual pivot sort because found 2 unique pivots.
                    Comparable pivotLeft = elements[pivots[1]];
                    Comparable pivotRight = elements[pivots[3]];

                    //Points left to first element on left >= pivotLeft,
                    while (elements[left].compareTo(pivotLeft) < 0) {
                        left++;
                    }

                    //Swaps the pivots into the middle section
                    int middle = left;
                    Util.swap(elements, pivots[1], middle++);
                    Util.swap(elements, pivots[3], middle++);

                    right--;//Make it point inside of array
                    //Points right to first element from right that is not greater than pivotRight
                    while (elements[right].compareTo(pivotRight) > 0) {
                        right--;
                    }

                    //Left now points to first slot on right side of left section.
                    //Right points to first slot on left side of right section.
                    //Middle now points to first slot on right side of middle section, which also is the next element to check.

                    //Loop until no more elements left unsorted
                    while (middle <= right) {
                        if (elements[middle].compareTo(pivotLeft) < 0) {
                            //Less than left pivot, move to left section
                            Util.swap(elements, middle++, left++);
                        } else if (elements[middle].compareTo(pivotRight) > 0) {
                            //Greater than right pivot, move to right side
                            Util.swap(elements, middle, right--);
                        } else {
                            //Otherwise belongs in middle section
                            middle++;
                        }
                    }

                    //Queue left section for sorting
                    subArrays.push(left); //end exclusive
                    subArrays.push(startIndex); //start inclusive
                    //Queue right section for sorting
                    subArrays.push(endIndex); //end exclusive
                    subArrays.push(right + 1); //start inclusive


                    if (left < pivots[0] && pivots[4] < right) { //If central part is too big (> ~4/7), move elements equal to the pivots to either end
                        //Point left to first element not equal to pivotLeft from left in middle section, and right vice versa
                        while (elements[left].compareTo(pivotLeft) == 0) {
                            left++;
                        }
                        while (elements[right].compareTo(pivotRight) == 0) {
                            right--;
                        }
                        middle = left;

                        //Points middle to first element from left equal to either pivot
                        while (middle <= right && elements[middle].compareTo(pivotLeft) > 0 && elements[middle].compareTo(pivotRight) < 0) {
                            middle++;
                        }

                        //Sorts
                        while (middle <= right) {
                            if (elements[middle].compareTo(pivotLeft) == 0) {
                                Util.swap(elements, middle++, left++);
                            } else if (elements[middle].compareTo(pivotRight) == 0) {
                                Util.swap(elements, middle, right--);
                            } else {
                                middle++;
                            }
                        }
                    }

                    //Queue middle section (shrunk or not shrunk) for sorting
                    subArrays.push(right + 1);//end exclusive
                    subArrays.push(left);//start inclusive

                } else { //Single pivot 3-way quicksort because pivots are degenerate with at least 2 identical elements
                    Comparable pivot = elements[pivots[3]];
                    while (elements[left].compareTo(pivot) < 0) { //Looks for first larger or equal element from left
                        left++;
                    }

                    int center = left;
                    Util.swap(elements, pivots[3], center++);

                    right--;//Moves it within array
                    while (elements[right].compareTo(pivot) > 0) { //Looks for first smaller or equal element
                        right--;
                    }


                    while (center <= right) { //Checks the rest of the elements, starting from right side
                        int result = elements[center].compareTo(pivot);
                        if (result < 0) {
                            Util.swap(elements, left++, center++);
                        } else if (result == 0) {
                            center++;
                        } else {
                            Util.swap(elements, center, right--);
                            while (elements[right].compareTo(pivot) > 0) { //Points to next first smaller or equal element from right
                                right--;
                            }
                        }
                    }

                    //Queue ths left and right sides for sorting
                    subArrays.push(left); //End exclusive
                    subArrays.push(startIndex); //Start inclusive

                    subArrays.push(endIndex); //End exclusive
                    subArrays.push(right + 1); //Start inclusive
                }
            }
        }
    }

    //Sorts the 5 sevenths of the array. Returns either the two ~terciles if no elements are duplicates or the median if any are duplicates
    @SuppressWarnings("unchecked")
    private static int[] pivotSelect(Comparable[] elements, int length, int start, int end) {
        // Inexpensive approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        //Sorts these 5 sevenths to approximate median and terciles. Borrowed from the original implementation
        int e3 = (start + end) >>> 1; // The midpoint
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
        int[] result = {e1, e2, e3, e4, e5};
        if (elements[e1] != elements[e2] && elements[e2] != elements[e3] && elements[e3] != elements[e4] && elements[e4] != elements[e5]) {
            result[2] = -1;
        }
        return result;
    }

}
