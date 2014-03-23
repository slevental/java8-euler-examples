package java8.euler;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by esLion on 3/23/14.
 */
public class PrimeTest {

    @Test
    public void testGcd() throws Exception {
        assertEquals(7, Prime.gcd(14, 21));
    }
}
