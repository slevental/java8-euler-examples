package java8.euler;

import java.util.Comparator;
import java.util.stream.IntStream;

public class Euler {

    public static void main(String[] args) {
        problem3();
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
