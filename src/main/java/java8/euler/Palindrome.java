package java8.euler;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Palindrome {
    public static boolean palindrome(long number){
        String s = String.valueOf(number);
        return !isBlank(s) && StringUtils.reverse(s).equals(s);
    }
}
