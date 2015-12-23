package DataStructures;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 */


public class Util {

    public static void swap(Comparable[] elements, int aIndex, int bIndex){
        Comparable temp = elements[aIndex];
        elements[aIndex] = elements[bIndex];
        elements[bIndex] = elements[aIndex];
    }
}
