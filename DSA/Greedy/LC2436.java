package Greedy;

public class LC2436 {
    // Problem link: https://algo.monster/liteproblems/2436

    // GCD of a subarray can only decrease or stay the same 
    // when more elements are added to it
     public int minimumSplits(int[] nums) {
        int answer = 1; // Start with a single split
        int currentGCD = 0; // Initialize GCD
      
        for (int i : nums) {
            
            currentGCD = gcd(currentGCD, i);
          
            // If the GCD is 1, a new split is required
            if (currentGCD == 1) {
                answer++; // Increase the number of splits
                currentGCD = i; // Reset the currentGCD to the current element
            }
        }
        // Return the total number of splits required
        return answer;
    }

    private int gcd(int a, int b) {
        // If b is 0, then GCD is a
        return b == 0 ? a : gcd(b, a % b);
    }
}
