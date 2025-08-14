import java.util.LinkedList;
import java.util.Queue;

import Base.TreeNode;

// Leetcode: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
public class MaxDepth {

    // Time Complexity: O(n) as we visit each node once.
    // Space Complexity: O(n) in the worst case (skewed tree) due to recursion
    // stack, O(log n) in the best case (balanced tree).
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right)) + 1;
    }

    // Time Complexity: O(n) as we visit each node once.
    // BFS uses a queue to store nodes of the current level, and in the worst case, it might need to hold all nodes at the widest level.
    // The maximum width (W) of a binary tree can be at most N/2 + 1 (for a complete binary tree), resulting in a space complexity of O(N) in the worst case.
    // In the best case (skewed tree), the queue will hold only one node at a time, resulting in a space complexity of O(1).
    public int maxDepthBFS(TreeNode root) {

        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int height = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();

                // Add child nodes of the current node
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            height++;
        }
        return height;
    }
}
