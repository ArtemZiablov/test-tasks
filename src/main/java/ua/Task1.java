package ua;

import java.util.Scanner;


// reference: https://en.wikipedia.org/wiki/Catalan_number#Applications_in_combinatorics

/* We calculate the number of correct bracket expressions that can be formed using N pairs of parentheses.
*  Such number can be calculated by finding the N-th Catalan number.
*  In this implementation, I use a dynamic programming approach to find the Catalan number.
*  DP formula:
*   C(0) = 1
*   C(n) = sum(C(i)*C(n-1-i) for i=0 to n-1)
*/

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N (number of pairs of parentheses): ");
        final int N = scanner.nextInt();
        scanner.close();

        // Handle the base case (N=0 means empty sequence which is considered correct)
        if (N == 0) {
            System.out.println(1);
            return;
        }

        // Array for Catalan numbers
        long[] catalan = new long[N + 1];

        // Initial condition
        catalan[0] = 1;

        // Use dynamic programming to fill up the Catalan numbers
        for (int n = 1; n <= N; n++) {
            long temp = 0;
            for (int i = 0; i < n; i++) {
                // C(n) = sum(C(i)*C(n-1-i) for i=0 to n-1)
                temp += catalan[i] * catalan[n - 1 - i];
            }
            catalan[n] = temp;
        }

        // The answer is the N-th Catalan number
        System.out.println("result: " + catalan[N]);
    }
}