package Recursion1;

import java.util.HashMap;

// LeetCode: https://leetcode.com/explore/learn/card/recursion-i/255/recursion-memoization/1662/
public class ClimbingStairs {
    private HashMap<Integer,Integer> cache = new HashMap<>();
    
    public int climbStairs(int n) {
        if(n <= 3) {
            return n;
        }
        
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        
        Integer ans = climbStairs(n-1) + climbStairs(n-2);
        cache.put(n, ans);
        return ans;
    }
}
