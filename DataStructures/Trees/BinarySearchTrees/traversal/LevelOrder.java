
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> al = new ArrayList<List<Integer>>();
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();

        if (root == null)
            return al;
        current.add(root);

        while (!current.isEmpty()) {
            TreeNode currentNode = current.remove();

            if (currentNode.left != null) {
                next.add(currentNode.left);
            }

            if (currentNode.right != null) {
                next.add(currentNode.right);
            }

            nodeValues.add(currentNode.val);

            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<TreeNode>();
                al.add(nodeValues);
                nodeValues = new ArrayList<Integer>();
            }
        }

        return al;
    }
}