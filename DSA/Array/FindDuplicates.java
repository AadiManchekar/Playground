import java.util.ArrayList;
import java.util.List;

// Leetcode: https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] < 0) {
                // Already visited → duplicate
                result.add(val);
            } else {
                // Mark visited
                nums[val - 1] = -nums[val - 1];
            }
        }
        
        return result;
    }
}
