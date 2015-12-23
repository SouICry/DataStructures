package DataStructures;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/22/2015
 * Implementation of a Queue backed by a resizeable 2-dimensional array to balance memory and speed costs.
 * Array will grow or shink to contain enough rows to store all its elements.
 * Note that types are <I>unchecked</I> so be sure to pass in compatible types to prevent runtime exceptions.
 *
 * Each row has size equal to the initial capacity, and it contains enough rows to contain the expected max capacity.
 * Default is 32 and 1024.
 * Only the array of row indices is copied and doubled when exceeding max capacity
 */


public class Queue<T> {
    private Object[] rows;
    private int rowSize;
    private int startRow = 0; //Row start is in. Must be different from end row when end loops around by design.
    private int endRow = 0; //Row end is in.
    private int start = 0; //The overall index of the first element. Can loop around. Equal to end when empty
    private int end = 0; //The overall index after the last element, aka index to add next element. Can loop around.

    /**
     * Creates a Queue with an initial capacity of 32 and expected max capacity of 1024.
     * Queue is doubled if exceeded, copying (currentMaxCapacity / 32) elements.
     */
    public Queue(){
        rowSize = 32;
        rows = new Object[33];
        rows[0] = new Object[rowSize];
    }

    /**
     * Creates a Queue with an initial capacity of ceil(sqrt(maxExpectedCapacity)) and max expected capacity of (initial size)^2.
     * Queue is doubled if exceeded, copying sqrt(maxExpectedCapacity) elements
     * @param maxExpectedCapacity - the maximum expected capacity
     */
    public Queue (int maxExpectedCapacity){
        rowSize = (int) Math.ceil(Math.sqrt(maxExpectedCapacity));
        rows = new Object[rowSize + 1];
        rows[0] = new Object[rowSize];
    }

    /**
     * Creates a Queue with given initial capacity and max expected capacity of lowest multiple of initialCapacity that is greater or equal to maxExpectedCapacity .
     * Queue is doubled if exceeded, copying (currentMaxCapacity / initial capacity) elements.
     * @param initialCapacity - the initial capacity (row size)
     * @param maxExpectedCapacity - the expected max capacity
     */
    public Queue (int initialCapacity, int maxExpectedCapacity){
        rowSize = initialCapacity;
        rows = new Object[(int)Math.ceil(maxExpectedCapacity / initialCapacity) + 1];
        rows[0] = new Object[rowSize];
    }

    /**
     * Checks if the Queue is empty
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return start == end;
    }

    /**
     * Adds an element to end of Queue, resizing if necessary. Identical to offer
     * @param e - the element to add
     */
    public void add(T e){

    }

    /**
     * Adds an element to end of Queue, resizing if necessary. Identical to offer
     * @param e - the element to add
     */
    public void offer(T e){
        add(e);
    }

    /**
     * Removes and returns the first element in the Queue, throwing an exception if empty
     * @return - the first element if it exists, or null if exception is ignored.
     * @throws NoSuchElementException
     */
    @SuppressWarnings("unchecked")
    public T remove(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return (T)((Object[])rows[start / rowSize])[start++ % rowSize];
    }

    /**
     * Removes and returns the first element in the Queue, returning null if empty
     * @return - the first element if it exists, or null if empty
     */
    @SuppressWarnings("unchecked")
    public T poll(){
        if (isEmpty()){
            return null;
        }
        return (T)((Object[])rows[start / rowSize])[start++ % rowSize];
    }

    /**
     * Returns the first element in the Queue, without removing it, or null if empty
     * @return - the first element, or null if empty
     */
    @SuppressWarnings("unchecked")
    public T peek(){
        if (isEmpty()){
            return null;
        }
        return (T)((Object[])rows[start / rowSize])[start % rowSize];
    }

    /**
     * Returns the first element in the Queue, without removing it, throwing an exception if empty
     * @return - the first element, or null if exception ignored
     * @throws NoSuchElementException
     */
    @SuppressWarnings("unchecked")
    public T element(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return (T)((Object[])rows[start / rowSize])[start % rowSize];
    }
}
