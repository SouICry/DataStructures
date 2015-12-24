package DataStructures;

import java.util.List;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 */


public class Util {

    public static void swap(Comparable[] elements, int aIndex, int bIndex){
        Comparable temp = elements[aIndex];
        elements[aIndex] = elements[bIndex];
        elements[bIndex] = temp;
    }


    public static void printArray(Object[] arr){
        StringBuilder s = new StringBuilder(arr.getClass().getName()).append("[").append(arr.length).append("] { ");
        for (int i = 0; i < arr.length - 1; i++){
            s.append(arr[i].toString()).append(", ");
        }
        s.append(arr[arr.length - 1].toString()).append(" }");
        System.out.println(s);
    }

    @SuppressWarnings("unchecked")
    public static boolean isSorted(Comparable[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i].compareTo(arr[i + 1]) > 0){
                return false;
            }
        }
        return true;
    }

}
