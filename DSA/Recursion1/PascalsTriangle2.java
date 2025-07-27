package Recursion1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// import javafx.util.Pair;
// By default, Pair is not available in Java SE 21, so we define our own Pair class in Base package.
// But in leetcode, you can use javafx.util.Pair
import Base.Pair;

public class PascalsTriangle2 {
    // Leetcode: https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/3234/
    // Use HashMap for memoization
    private HashMap<Pair<Integer,Integer>, Integer> map = new HashMap<>();
    
    private int pascalValue(int i, int j) {
        
        if(j == 0 || j == i) {
            return 1;
        }
        
        // create a Pair to look up into map
        Pair<Integer, Integer> p = new Pair<>(i, j);
        
        // You can also use !map.containsKey(p)
        if(map.get(p) == null) { 
            // compute and store in map
            Integer ans = pascalValue(i-1, j-1) + pascalValue(i-1, j);
            map.put(p, ans);
            return ans;
        }
        
        // already computed just get from map and use it
        return map.get(p);
    }
    
    public List<Integer> getRow(int rowIndex) {
        
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i<= rowIndex; i++) {
            Integer val = pascalValue(rowIndex, i);
            ans.add(val);
        }

        return ans;
    }
}
