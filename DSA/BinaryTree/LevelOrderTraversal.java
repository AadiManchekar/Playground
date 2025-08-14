import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Base.TreeNode;

// BFS
// Leetcode: https://leetcode.com/problems/binary-tree-level-order-traversal/
// Time Complexity: O(n)
// Space Complexity: O(n) (worse case) For a complete binary tree, the max nodes at the bottom level is n/2 ⇒ O(n) space.
// BFS uses a queue to store nodes of the current level, and in the worst case, it might need to hold all nodes at the widest level.
// The maximum width (W) of a binary tree can be at most N/2 + 1 (for a complete binary tree), resulting in a space complexity of O(N) in the worst case.
// In the best case (skewed tree), the queue will hold only one node at a time, resulting in a space complexity of O(1).
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Traverse all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();
                currentLevel.add(node.val);

                // Add child nodes of the current node
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            ans.add(currentLevel);
        }

        return ans;
    }
}
