package Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetAllDivisors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        approach1(n);
        approach2(n);
        sc.close();
    }

    // Time Complexity: O(N),
    // Space Complexity: O(N), for storing the divisors in a list.
    private static void approach1(int n) {
        List<Integer> ans = new ArrayList<>(); 
        for(int i = 1;i<=n;i++){
            if(n%i == 0){
                ans.add(i);
            }
        }
        System.out.println(ans);
    }

    // Time Complexity: O(sqrt(N)),
    // Space Complexity: O(N), for storing the divisors in a list.
    private static void approach2(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                ans.add(i);
                if (i != n / i) { 
                    // Avoid adding the square root twice
                    ans.add(n / i);
                }
            }
        }
        // Time Complexity: O(N log N) for sorting the divisors
        // Space Complexity: O(N) for storing the divisors in a list.
        ans.sort(null); // Sort the divisors in ascending order
        System.out.println(ans);
    }
}
