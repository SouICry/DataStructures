package DataStructures;

/**
 * @author Guyue Zhou (SouICry) gzhou.email@gmail.com
 * @since 12/23/2015
 * A minimal overhead implementation of a fixed size stack of ints, so I don't have to rewrite it for everything.
 */
public class FastIntStack {
    private int[] stack;
    private int size = 0;

    public FastIntStack(int fixedSize){
        stack = new int[fixedSize];
    }

    public void push(int e){
        stack[size++] = e;
    }

    public int peek(){
        return stack[size - 1];
    }

    public int pop(){
        return stack[--size];
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
