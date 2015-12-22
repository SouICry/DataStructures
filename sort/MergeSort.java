package DataStructures.sort;

import java.util.*;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * A stable, iterative, in place implementations of MergeSort for arrays and Lists of Comparable elements.
 * Originally made as coding practice.
 */
public class MergeSort {

    /**
     * Sorts the given array of Comparable elements.
     * Stable, iterative, in place.
     * @param elements - the array of elements
     */
    public static void sort(Comparable[] elements) {
        int i, length = elements.length;

        //Merges from bottom up. size is size of two sets of elements to be merged
        for (int size = 2; size < length * 2; size *= 2){
            //Merges two sets full sets of half of size each time
            for (i = size; i <= length; i+= size){
                merge(elements, i - size, i - size / 2, i);
            }

            //Merges the leftover sets, if more than one full set of elements remains
            if ((i -= size / 2) < length){
                merge(elements, i - size / 2, i, length);
            }
        }
    }

    //Start inclusive, end exclusive. Split is first element of second set
    private static void merge(Comparable[] elements, int start, int split, int end){
        Comparable[] left = Arrays.copyOfRange(elements, start, split);
        Comparable[] right = Arrays.copyOfRange(elements, split, end);

        int leftIndex = 0, rightIndex = 0, index = start;
        while (leftIndex < split && rightIndex < end){
            elements[index++] = left[leftIndex].compareTo(right[rightIndex]) < 0 ? left[leftIndex++] : right[rightIndex++];
        }

        while (leftIndex < split){
            elements[index++] = left[leftIndex++];
        }
    }

    /**
     * Sorts the given List of Comparable elements.
     * Stable, iterative, in place.
     * @param elements - the List of elements
     */
    public static void sort(List<Comparable> elements){
        int i, length = elements.size();

        //Merges from bottom up. size is size of two sets of elements to be merged
        for (int size = 2; size < length * 2; size *= 2){
            //Merges two sets full sets of half of size each time
            for (i = size; i <= length; i+= size){
                merge(elements, i - size, i - size / 2, i);
            }

            //Merges the leftover sets, if more than one full set of elements remains
            if ((i -= size / 2) < length){
                merge(elements, i - size / 2, i, length);
            }
        }
    }

    //Merges based on give sublist indices. Start inclusive, end exclusive. Split is first element of second set
    private static void merge(List<Comparable> elements, int start, int split, int end){
        Comparable[] left = subArray(elements, start, split);
        Comparable[] right = subArray(elements, split, end);

        int leftIndex = 0, rightIndex = 0, index = start;
        while (leftIndex < split && rightIndex < end){
            elements.set(index++, left[leftIndex].compareTo(right[rightIndex]) < 0 ? left[leftIndex++] : right[rightIndex++]);
        }

        while (leftIndex < split){
            elements.set(index++, left[leftIndex++]);
        }
    }

    //Copies given part of list into a new array, start inclusive, end exclusive
    private static Comparable[] subArray(List<Comparable> elements, int start, int end){
        Comparable[] arr = new Comparable[end - start];
        for (int i = start; i < end; i++)
            arr[i] = elements.get(i);
        return arr;
    }
}
