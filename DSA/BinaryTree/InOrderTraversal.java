import java.util.ArrayList;
import java.util.List;

import Base.TreeNode;

// DFS
// Typically, for binary search tree, we can retrieve all the data in sorted order using in-order traversal
// Leetcode: https://leetcode.com/problems/binary-tree-inorder-traversal/description/
// Time Complexity: O(n)
// - Each node is visited once, leading to O(n) time complexity.

// Space Complexity: O(n) 
// Worst-case space (skewed tree): O(n) (call stack depth = n)
// Best-case space (balanced tree): O(log n) (call stack depth = log n)
public class InOrderTraversal {
    List<Integer> ans = new ArrayList<>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrderBinaryTreeTraversal(root);
        return ans;
    }

    private void inOrderBinaryTreeTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderBinaryTreeTraversal(root.left);
        ans.add(root.val);
        inOrderBinaryTreeTraversal(root.right);
    }
}
