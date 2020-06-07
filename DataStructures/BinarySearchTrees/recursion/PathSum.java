/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int diff = sum - root.val;
        if (diff == 0 && isLeaf(root)) {
            return true;
        }
        return hasPathSum(root.left, diff) || hasPathSum(root.right, diff);
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}