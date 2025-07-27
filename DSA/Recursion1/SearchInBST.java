package Recursion1;

import Base.TreeNode;

public class SearchInBST {
    // LeetCode: https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/3233/
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val) {
            return root;
        } 

        return (root.val > val) ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
