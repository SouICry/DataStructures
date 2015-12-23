package DataStructures.sort;

import java.util.List;
import DataStructures.Util;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 */


public class InsertionSort {

    /**
     * Implementation of pair insertion sort
     * Stable, in place.
     * @param elements - the array of Comparable elements to be sorted
     */
    public static void sort(Comparable[] elements){
        Comparable smallest, secondSmallest, temp;
        int smallestIndex, secondSmallestIndex;


        for (int i = 0; i < elements.length; i+= 2){
            //Sets two smallest to first two elements in order
            if (elements[i].compareTo(elements[i+1]) <= 0){
                smallestIndex = i;
                smallest = elements[i];
                secondSmallestIndex = i+1;
                secondSmallest = elements[secondSmallestIndex];
            }
            else {
                smallestIndex = i+1;
                smallest = elements[smallestIndex];
                secondSmallest = elements[i];
                secondSmallestIndex = i;
            }

            //Finds two smallest
            for (int j = i + 2; j < elements.length; j++){
                temp = elements[j];
                if (temp.compareTo(secondSmallest) < 0){
                    if (temp.compareTo(smallest) < 0) {
                        secondSmallest = smallest;
                        secondSmallestIndex = smallestIndex;
                        smallest = temp;
                        smallestIndex = j;
                    }
                    else {
                        secondSmallest = temp;
                        secondSmallestIndex = j;
                    }
                }
            }

            //Swaps the values
            Util.swap(elements, smallestIndex, i);
            Util.swap(elements, secondSmallestIndex, i+1);
        }
    }

    public static void sort(List<Comparable> elements){

    }
}
