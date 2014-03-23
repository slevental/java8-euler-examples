package java8.euler;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by esLion on 3/23/14.
 */
public class PrimeTest {

    @Test
    public void testModularExponentiation() throws Exception {
        assertME(2, 2, 2);
        assertME(3, 2, 2);
        assertME(4, 2, 3);
        assertME(10, 10, 23);
        assertME(0, 1, 3);
        assertME(4, 2, 3);
    }

    @Test
    public void testPrimes() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/primes")))) {
            reader.lines().forEach(s -> assertTrue("Failed with input: " + s, Prime.prime(Long.parseLong(s))));
        }
    }

    @Test
    public void testGcd() throws Exception {
        assertEquals(7, Prime.gcd(14, 21));
    }

    private void assertME(long a, long b, long n) {
        assertEquals(Math.pow(a, b) % n, (double) Prime.modularExponentiation(a, b, n));
    }
}
