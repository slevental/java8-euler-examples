package java8.euler;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Euler {

    public static void main(String[] args) {
        problem4();
    }

    private static void problem4() {
        int hi = 999, lo = 0;
        AtomicLong max = new AtomicLong(0);
        IntStream.range(lo, hi)
                .forEach(a -> IntStream.rangeClosed(lo, hi).forEach(b -> {
                    int palindromeCandidate = a * b;
                    long l;
                    if (Palindrome.palindrome(palindromeCandidate)) {
                        do {
                            l = max.get();
                        } while (l < palindromeCandidate && !max.compareAndSet(l, palindromeCandidate));
                    }
                }));
        System.out.println(max.get());
    }

    private static void problem3() {
        long x = 600851475143L;
        System.out.println(Prime.factorize(x).filter(Prime::prime).max(Comparator.naturalOrder()).get());
    }

    private static void problem2() {
        System.out.println(
                While.stream(FibonacciStream.stream(), x -> x <= 4e+6)
                        .parallel()
                        .filter(x -> x % 2 == 0)
                        .reduce(0, (a, b) -> a + b)
        );
    }

    private static void problem1() {
        System.out.println(
                IntStream.range(0, 1000)
                        .parallel()
                        .filter(x -> x % 5 == 0 || x % 3 == 0)
                        .reduce(0, (a, b) -> a + b)
        );
    }

}
