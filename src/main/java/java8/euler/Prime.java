package java8.euler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.lang.Math.log;
import static java.lang.Math.round;

public class Prime {

    // offered by Cormen, probability that Miller-Rabin algorithms
    // will give false-negative equal 2^(-s) in case of s=50, e=8.8e-16
    private static final long S = 50;

    private static final Random RND = new Random();

    /**
     * Cormen, 2012, 31.6
     * <p>
     * modularExponentiation <- a^b mod n
     *
     * @param a
     * @param b
     * @param n
     * @return
     */
    public static long modularExponentiation(long a, long b, long n) {
        long c = 0, d = 1;
        for (long i = round(log(b) / log(2)); i >= 0; i--) {
            c = 2 * c;
            d = (d * d) % n;
            long bi = (long) Math.pow(2, i) & b;
            if (bi > 0) {
                c++;
                d = (d * a) % n;
            }
        }
        return d;
    }

    /**
     * Non-randomized version or Pseudoprime test
     */
    private static boolean whithness(long a, long n) {
        long t = 0;
        double u;
        while (true) {
            u = (n - 1) / Math.pow(2, ++t);
            if (Math.round(u) == u && u % 2 != 0) break;
            if (u < 1) throw new IllegalArgumentException("This is illegal candidate: " + n);
        }
        long prev = modularExponentiation(a, (long) u, n);
        long x = 0;
        for (long i = 0; i < t; i++) {
            x = (prev * prev) % n;
            if (x == 1 && prev != 1 && prev != n - 1)
                return true;
            prev = x;
        }
        return x != 1;
    }


    /**
     * Test that number is prime
     *
     * @param n number to test
     * @param s number of trials to prevent false-negative result with prob e=2^-s
     * @return true if it's a prime
     */
    private static boolean isPrimeByMillerRabin(long n, long s) {
        for (long i = 0; i < s; i++) {
            long a = randomLong(1, n - 1);
            if (whithness(a, n))
                return false;
        }
        return true;
    }

    public static boolean prime(long n) {
        if (n == 1 || n == 2) return true; // optimize
        if (n % 2 == 0) return false;
        return isPrimeByMillerRabin(n, S);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static Stream<Long> factorize(long n) {
        AtomicLong x = new AtomicLong(n);
        return While.stream(
                Stream.iterate(
                        factor(x.get()),
                        e -> factor(x.accumulateAndGet(e, (a, b) -> a / b))
                ),
                p -> x.get() > 1
        );
    }


    public static long factor(long n) {
        if (prime(n)) return n;
        if (n == 1) return n;

        long i = 1;
        long x = randomLong(0, n - 1);
        long y = x;
        long k = 2;

        while (true) {
            i++;
            x = (x * x - 1) % n;
            long d = gcd(y - x, n);
            if (d > 0 && d != 1 && d != n)
                return d;
            if (i == k) {
                y = x;
                k = 2 * k;
            }
        }
    }


    public static long randomLong(long lo, long hi) {
        return (Math.round(RND.nextDouble() * (hi - lo))) + lo;
    }
}
