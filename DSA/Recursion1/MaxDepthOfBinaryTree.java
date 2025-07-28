package Recursion1;

import Base.TreeNode;

// LeetCode: https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/2375/
// Time Complexity:

// O(N), where N is the number of nodes in the binary tree.
// Every node is visited once.
// Space Complexity:

// O(H), where H is the height of the tree.
// This is due to the recursion stack. In the worst case (skewed tree), H = N; in the best case (balanced tree), H = log N.
public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
