
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.lang.Math;

class MaxDepth {
    int topDownAnswer = 0;

    public int maxDepth(TreeNode root) {
        gatherMaxDepthTopDown(root, 0);
        int bottomUpAnswer = gatherMaxDepthBottomUp(root, 0);
        return bottomUpAnswer;
    }

    private void gatherMaxDepthTopDown(TreeNode root, int depth) {
        if (root == null)
            return;
        if (root.right == null && root.left == null) {
            topDownAnswer = Math.max(topDownAnswer, depth);
        }
        gatherMaxDepthTopDown(root.left, depth + 1);
        gatherMaxDepthTopDown(root.right, depth + 1);
    }

    private int gatherMaxDepthBottomUp(TreeNode root, int depth) {
        if (root == null)
            return 0;
        int left_depth = gatherMaxDepthBottomUp(root.left, depth);
        int right_depth = gatherMaxDepthBottomUp(root.right, depth);
        return Math.max(left_depth, right_depth) + 1;
    }
}