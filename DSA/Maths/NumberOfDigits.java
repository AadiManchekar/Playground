package Maths;

import java.util.Scanner;

public class NumberOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        approach1(n);
        approach2(n);
        sc.close(); // Close the scanner
    }

    
    // Each iteration divides n by 10, reducing the number of digits by 1. So, the number of iterations is proportional to the number of digits, which is log₁₀(N).
    // approach1: O(log₁₀(N)) time, O(1) space
    private static void approach1(int n) {
        if (n == 0) {
            System.out.println("Number of digits by approach1: " + 1);
            return;
        }
        int noOfDigits = 0;
        n = Math.abs(n);
        while (n != 0) {
            n = n / 10;
            noOfDigits++;
        }
        
        System.out.println("Number of digits by approach1: " + noOfDigits);
    }
    
    // approach2: O(1) time, O(1) space
    private static void approach2(int n) {
        if (n == 0) {
            System.out.println("Number of digits by approach2: " + 1);
            return;
        }

        System.out.println(
                "Number of digits by approach2: "
                        + (int) (Math.floor(Math.log10(Math.abs(n))) + 1));
    }
}
