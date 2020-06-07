
/**
 * Leetcode
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.ArrayList;

class InOrder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        gatherResult(root, result);
        System.out.println(result);
        return result;
    }

    private void gatherResult(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        gatherResult(root.left, result);
        System.out.println(root.val);
        result.add(root.val);
        gatherResult(root.right, result);
    }
}