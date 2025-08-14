import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;

import Base.TreeNode;

// Leetcode: https://leetcode.com/problems/symmetric-tree/description/
public class SymmetricTree {
    // DFS approach
    // Time complexity: O(n), where n is the total number of nodes in the tree.
    // Space Complexity
    // Space usage comes from recursive call stack.
    // In the worst case (skewed tree), recursion depth can be O(n).
    // In the best case (balanced tree), recursion depth is O(log n).
    // Therefore: O(h), where h is the height of the tree (worst-case O(n), best-case O(log n)).
    public boolean isSymmetricDFS(TreeNode root) {
        return areMirrorNodes(root.left, root.right);
    }

    private boolean areMirrorNodes(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)
            return true;
        
        if(root1 == null || root2 == null)
            return false;
        
        if(root1.val != root2.val)
            return false;
        
        return areMirrorNodes(root1.left, root2.right) && areMirrorNodes(root1.right, root2.left);
    }

    // BFS approach
    // Time Complexity: O(n), where n is the total number of nodes in the tree.
    // Space Complexity: O(w), where w is the maximum width of the tree.
    // w (width) at worst case can be O(n/2) for a complete binary tree. Which is equivalent to O(n).
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root.left);
        queue.add(root.right);
        
        while(!queue.isEmpty()) {
            // pop two mirror elements and compare
            TreeNode root1 = queue.remove();
            TreeNode root2 = queue.remove();
            
            if(root1 == null && root2 == null) {
                continue;
            }
            
            if(root1 == null || root2 == null) {
                return false;
            }
            
            if(root1.val != root2.val) {
                return false;
            }
            
            // add mirror child elements
            
            // mirror child elements 1
            queue.add(root1.left);
            queue.add(root2.right);
            
            // mirror child elements 2
            queue.add(root1.right);
            queue.add(root2.left);
        }
        return true;
    }
}
