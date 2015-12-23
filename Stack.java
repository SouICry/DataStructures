package DataStructures;

import java.util.*;
/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * Implementation of a stack backed by a resizeable 2-dimensional array to balance memory and speed costs.
 * Array will only grow or shrink to contain enough rows to store all its elements (plus up to 2 empty rows worth of elements)
 * Note that types are <I>unchecked</I> so be sure to pass in compatible types to prevent runtime exceptions.
 *
 * Each row has size equal to the initial capacity, and it contains enough rows to contain the expected max capacity.
 * Default is 32 and 1024.
 * Only the array of row indices is copied and doubled when exceeding max capacity
 *
 */

public class Stack<T>  {
    private Object[] rows;
    private int rowSize;
    private int size = 0; //Size of array, so index to put next element
    private int currentCapacity;

    /**
     * Creates a stack with an initial capacity of 32 and expected max capacity of 1024.
     * Stack is doubled if exceeded, copying (currentMaxCapacity / 32) elements.
     */
    public Stack(){
        rowSize = 32;
        rows = new Object[32];
        rows[0] = new Object[rowSize];
        currentCapacity = 32;
    }

    /**
     * Creates a stack with an initial capacity of ceil(sqrt(maxExpectedCapacity)) and max expected capacity of (initial size)^2.
     * Stack is doubled if exceeded, copying sqrt(maxExpectedCapacity) elements
     * @param maxExpectedCapacity - the maximum expected capacity
     */
    public Stack (int maxExpectedCapacity){
        rowSize = (int) Math.ceil(Math.sqrt(maxExpectedCapacity));
        rows = new Object[rowSize];
        rows[0] = new Object[rowSize];
        currentCapacity = rowSize;
    }

    /**
     * Creates a stack with given initial capacity and max expected capacity of lowest multiple of initialCapacity that is greater or equal to maxExpectedCapacity .
     * Stack is doubled if exceeded, copying (currentMaxCapacity / initial capacity) elements.
     * @param initialCapacity - the initial capacity (row size)
     * @param maxExpectedCapacity - the expected max capacity
     */
    public Stack (int initialCapacity, int maxExpectedCapacity){
        rowSize = initialCapacity;
        rows = new Object[(int)Math.ceil(maxExpectedCapacity / initialCapacity)];
        rows[0] = new Object[rowSize];
        currentCapacity = rowSize;
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * Retrieves, but does not remove, the top element of the stack, or returns null if empty.
     * @return top element, or null if none exist
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        return size == 0 ? null : (T)((Object[]) rows[size / rowSize])[size % rowSize];
    }

    /**
     * Pops an element from the top of the stack. Throws NoSuchElementException if empty.
     * @return the element popped from the stack
     * @throws NoSuchElementException
     */
    @SuppressWarnings("unchecked")
    public T pop() throws NoSuchElementException{
        if (size == 0)
            throw new NoSuchElementException();
        size--;
        if (currentCapacity / rowSize - 2 > size / currentCapacity){
            rows[currentCapacity / rowSize - 1] = null;
        }

        return (T)((Object[]) rows[size / rowSize])[size % rowSize];
    }

    /**
     * Pushes an element to the top of the stack, resizing if necessary.
     * @param e - the element to be pushed
     */
    public void push(T e){
        if (size >= currentCapacity){
            if (size > rows.length * rowSize){ //Exceeded max size, double it
                rows = Arrays.copyOf(rows, rows.length * 2);
            }
            Object[] n = new Object[rowSize];
            n[0] = e;
            rows[currentCapacity / rowSize] = n;
            currentCapacity += rowSize;
            size++;
        }
        else {
            ((Object[])rows[size / rowSize])[size++ % rowSize] = e;
        }
    }
}
