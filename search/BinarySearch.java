package DataStructures.search;

import java.util.*;
/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * Iterative implementations of BinarySearch for Lists and arrays of Comparable elements.
 * Originally for coding practice.
 */

public class BinarySearch {

    /**
     * An iterative implementation of BinarySearch for arrays of Comparable elements.
     * The function return value is identical to that of Arrays.binarySearch, returning -(insertion point) - 1 if not found.
     * @param elements - the array of Comparable elements
     * @param key - the key to search for
     * @return index of the key, if it is contained within range. Otherwise, -(insertion point) - 1. The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
     */
    public static int search(Comparable[] elements, Comparable key){
        return search(elements, key, 0, elements.length);
    }


    /**
     * An iterative implementation of BinarySearch for arrays of Comparable elements.
     * The function return value is identical to that of Arrays.binarySearch, returning -(insertion point) - 1 if not found.
     * @param elements - the array of Comparable elements
     * @param key - the key to search for
     * @return index of the key, if it is contained within range. Otherwise, -(insertion point) - 1. The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
     */
    public static int search(Comparable[] elements, Comparable key, int fromIndex, int toIndex){
        int mid, result;
        while (fromIndex < toIndex) {
            mid = (fromIndex + toIndex) / 2;
            result = elements[mid].compareTo(key);
            if (result == 0)
                return mid;
            else if (result < 0){
                toIndex = mid;
            }
            else {
                fromIndex = mid + 1;
            }
        }

        return -toIndex - 1;
    }

    /**
     * An iterative implementation of BinarySearch for Lists of Comparable elements.
     * The function return value is identical to that of the equivalent Arrays.binarySearch, returning -(insertion point) - 1 if not found.
     * @param elements - the List of Comparable elements
     * @param key - the key to search for
     * @return index of the key, if it is contained within range. Otherwise, -(insertion point) - 1. The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
     */
    public static int search(List<Comparable> elements, Comparable key) {
        return search(elements, key, 0, elements.size());
    }

    /**
     * An iterative implementation of BinarySearch for Lists of Comparable elements.
     * The function return value is identical to that of the equivalent Arrays.binarySearch, returning -(insertion point) - 1 if not found.
     * @param elements - the List of Comparable elements
     * @param key - the key to search for
     * @return index of the key, if it is contained within range. Otherwise, -(insertion point) - 1. The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
     */
    public static int search(List<Comparable> elements, Comparable key, int fromIndex, int toIndex) {
        int mid, result;
        while (fromIndex < toIndex) {
            mid = (fromIndex + toIndex) / 2;
            result = elements.get(mid).compareTo(key);
            if (result == 0)
                return mid;
            else if (result < 0){
                toIndex = mid;
            }
            else {
                fromIndex = mid + 1;
            }
        }

        return -toIndex - 1;
    }

}
