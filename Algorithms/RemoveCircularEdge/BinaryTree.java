import java.lang.*;
import java.util.*;

// Binary Tree != Binary Search Tree
public class BinaryTree {
    protected static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            value = v;
        }
    }

    private static TreeNode removeCircularEdgeFromTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        TreeNode newTreeRoot = new TreeNode(root.value);
        TreeNode newTree = newTreeRoot;
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> frontier = new LinkedList<>();
        Queue<TreeNode> newTreeFrontier = new LinkedList<>();

        frontier.add(root);
        newTreeFrontier.add(newTree);

        while (!frontier.isEmpty()) {
            int size = frontier.size();
            while (size-- > 0) {
                TreeNode current = frontier.remove();
                TreeNode currentNodeFromNewTree = newTreeFrontier.remove();

                visited.add(current);

                if (current.left != null) {
                    if (!visited.contains(current.left)) {
                        currentNodeFromNewTree.left = new TreeNode(current.left.value);
                        newTreeFrontier.add(currentNodeFromNewTree.left);
                        frontier.add(current.left);
                    }
                }

                if (current.right != null) {
                    if (!visited.contains(current.right)) {
                        currentNodeFromNewTree.right = new TreeNode(current.right.value);
                        newTreeFrontier.add(currentNodeFromNewTree.right);
                        frontier.add(current.right);
                    }
                }
            }
        }

        return newTreeRoot;
    }

    private static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.println("Node:" + node.value);
        inorder(node.right);
    }

    public static void main(String args[]) {
        TreeNode testCaseRoot = new TreeNode(2);
        TreeNode testCaseRootRight = new TreeNode(5);
        testCaseRoot.left = testCaseRoot;
        testCaseRoot.right = testCaseRootRight;
        testCaseRootRight.left = new TreeNode(1);
        testCaseRootRight.right = new TreeNode(9);

        inorder(removeCircularEdgeFromTree(testCaseRoot));
    }
}