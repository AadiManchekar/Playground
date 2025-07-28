package Maths;

import java.util.Scanner;

public class CheckIfNumberIsPrime {
    public static void main(String[] args) {
        // A prime number is a natural number greater than 1 that cannot be formed by multiplying
        // two smaller natural numbers.
        // For example, 2, 3, 5, 7, 11, and 13 are prime numbers.
        // The number 1 is not considered a prime number.
        // The number 4 is not a prime number because it can be formed by multiplying 2 by 2.
        // The number 6 is not a prime number because it can be formed by multiplying 2 by 3.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        approach1(n);
        approach2(n);
        sc.close();
    }

    // approach1: O(N) time, O(1) space
    private static void approach1(int n) {
        if (n <= 1) {
            System.out.println(n + " is not a prime number.");
            return;
        }
        // code same as GetAllDivisors.approach1
        int noOfDivisors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                noOfDivisors++;
            }
        }
        if (noOfDivisors == 2) {
            System.out.println(n + " is a prime number.");
        } else {
            System.out.println(n + " is not a prime number.");
        }
    }

    // approach2: O(sqrt(N)) time, O(1) space
    private static void approach2(int n) {
        if (n <= 1) {
            System.out.println(n + " is not a prime number.");
            return;
        }
        // code same as GetAllDivisors.approach2
        int noOfDivisors = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                noOfDivisors++;
                if (i != n / i) {
                    noOfDivisors++; // Count the corresponding divisor
                }
            }
        }
        if (noOfDivisors == 2) {
            System.out.println(n + " is a prime number.");
        } else {
            System.out.println(n + " is not a prime number.");
        }
    }
}
