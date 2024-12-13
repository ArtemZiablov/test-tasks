package ua;

import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        int n = 100;
        BigInteger fact = factorial(n); // get the result pf 100!

        String factStr = fact.toString(); // convert to String
        int sum = 0;

        // Sum all the digits
        for (char c : factStr.toCharArray()) {
            sum += (c - '0');
        }

        // Output the result
        System.out.println("Sum of digits in " + n + "! = " + sum);
    }

    // Public method for convenience
    public static BigInteger factorial(int n) {
        return factorialTail(BigInteger.valueOf(n), BigInteger.ONE);
    }

    // Tailrec for factorial
    private static BigInteger factorialTail(BigInteger n, BigInteger acc) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return acc;
        } else {
            return factorialTail(n.subtract(BigInteger.ONE), acc.multiply(n));
        }
    }
}

