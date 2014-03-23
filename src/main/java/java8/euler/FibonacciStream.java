package java8.euler;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
* Created by esLion on 3/23/14.
*/
class FibonacciStream {
    public static Stream<Integer> stream() {
        AtomicInteger previous = new AtomicInteger();
        return Stream.iterate(1, operand -> previous.getAndSet(operand) + operand);
    }
}
