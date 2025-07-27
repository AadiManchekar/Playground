package Recursion1;

import java.util.HashMap;

// LeetCode: https://leetcode.com/explore/learn/card/recursion-i/255/recursion-memoization/1661/
public class FibonacciNumber {
    // cache for memoization
    private HashMap<Integer, Integer> cache = new HashMap<>();
    
    public int fib(int n) {
        
        // Base conditions
        if(n < 2){
            return n;
        }
        
        if(cache.containsKey(n)) {
            // value is already in cache so no need to compute
            // get from cache and use it
            return cache.get(n);
        }
        
        // compute and persist in cache
        int ans = fib(n-1) + fib(n-2);
        cache.put(n, ans);
        
        return ans;
    }
}
