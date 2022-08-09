package org.study.practice;

public class FibonacciShortApproach {

    /** Here we have used so-called anonymous class <- fibonacciGenerator. This class has to state in direct meaning of that word.
     *  What is more, we hadn't implemented any other method but next().
     *  Why? Because 'InfGenerator<T>' is interface which already includes default override hasNext() and iterator() methods. */

    public static void main(String[] args) {

        var fibonacciGenerator = new InfGenerator<Integer>() {
            int a = 0;
            int b = 1;

            @Override
            public Integer next() {
                int result = a;
                a = b;
                b += result;
                return result;
            }};

        /** It is possible to iterate through 'fibonacciGenerator' variable because our interface extends 'Iterable' interface.
         *  That simple makes our InfGenerator... iterable :) */

        for (int fibNumber : fibonacciGenerator) {
            if (fibNumber > 5000) break;
            System.out.println(fibNumber);
        }
    }
}
