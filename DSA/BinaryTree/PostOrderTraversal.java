import java.util.ArrayList;
import java.util.List;

import Base.TreeNode;

// DFS
// when you delete nodes in a tree, deletion process will be in post-order. That is to say, when you delete a node, you will delete its left child and its right child before you delete the node itself.
// Do check (post-order is widely used in mathematical expressions): https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/992/
// Leetcode: https://leetcode.com/problems/binary-tree-postorder-traversal/description/
// Time Complexity: O(n)
// - Each node is visited once, leading to O(n) time complexity.

// Space Complexity: O(n) 
// Worst-case space (skewed tree): O(n) (call stack depth = n)
// Best-case space (balanced tree): O(log n) (call stack depth = log n)
public class PostOrderTraversal {
    List<Integer> ans = new ArrayList<>();
    
    public List<Integer> postorderTraversal(TreeNode root) {
        postOrderBinaryTreeTraversal(root);
        return ans;
    }

    private void postOrderBinaryTreeTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderBinaryTreeTraversal(root.left);
        postOrderBinaryTreeTraversal(root.right);
        ans.add(root.val);
    }
}