package org.study.practice;

import java.util.Iterator;

public class FibonacciLongApproach implements Iterable<Integer>, Iterator<Integer> {

    private int a = 0;
    private int b = 1;

    private FibonacciLongApproach() {}

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }


    /** Fibonacci loop is endless that is why hasNext always returns true value */
    @Override
    public boolean hasNext() {
        return true;
    }

    /** Steps : (first loop) result = 0 | a = 1 | b = 1 + 0 ...
     *          (second loop) result = 1 | a = 1 | b = 1 + 1 ...   */
    @Override
    public Integer next() {
        int result = a;
        a = b;
        b += result;

        return result;
    }

    public static void main(String[] args) {
        for (int i : new FibonacciLongApproach()) {
            System.out.println(i);
            if (i > 2000) break;
        }
    }
}
