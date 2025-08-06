
import java.util.ArrayList;
import java.util.List;
import Base.TreeNode;

// DFS
// Leetcode: https://leetcode.com/problems/binary-tree-preorder-traversal/description/
// Time Complexity: O(n)
// - Each node is visited once, leading to O(n) time complexity.

// Space Complexity: O(n) 
// Worst-case space (skewed tree): O(n) (call stack depth = n)
// Best-case space (balanced tree): O(log n) (call stack depth = log n)
public class PreOrderTraversal {
    List<Integer> ans = new ArrayList<>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        preOrderBinaryTreeTraversal(root);
        return ans;
    }
    
    private void preOrderBinaryTreeTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        ans.add(root.val);
        preOrderBinaryTreeTraversal(root.left);        
        preOrderBinaryTreeTraversal(root.right);
    }
}
