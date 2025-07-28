package Maths;

import java.util.Scanner;


// Leetcode: 7. Reverse Integer
public class ReverseInteger {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        approach1(n);
        sc.close();
    }


    // Each iteration divides n by 10, reducing the number of digits by 1. So, the number of iterations is proportional to the number of digits, which is log₁₀(N).
    // approach1: O(log₁₀(N)) time, O(1) space
    private static void approach1(int n) {
        boolean isNegative = n < 0 ? true : false;

        n = Math.abs(n);

        int reverseOfN = 0;
        while(n!=0) {
            int lastDigit = n%10;

            // Ensure that the reverse number is within Integer limits
            // Before multiplying reverseOfN by 10 and adding lastDigit, check if this operation would cause an overflow.
            // The condition checks if reverseOfN is greater than (Integer.MAX_VALUE - lastDigit) / 10.
            // If it is, then reverseOfN * 10 + lastDigit would exceed Integer.MAX_VALUE, causing an overflow.
            // In such a case, we return early to avoid incorrect results.
            if(reverseOfN > (Integer.MAX_VALUE - lastDigit)/10) {
                // will overflow
                return;
            } 
            reverseOfN = reverseOfN * 10 + lastDigit;
            n = n / 10;
        }

        reverseOfN = isNegative ? -reverseOfN : reverseOfN;

        System.out.println("Reverse number is " + reverseOfN);
    }
}
