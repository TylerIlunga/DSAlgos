
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
import java.util.LinkedList;
import java.util.Queue;

class LevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new LinkedList<List<Integer>>();
        }
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        gatherLevels(result, queue, list);
        return result;
    }

    private void gatherLevels(LinkedList<List<Integer>> result, Queue<Node> queue, LinkedList<Integer> list) {
        if (queue.isEmpty()) {
            return;
        }
        Queue<Node> nodeQueue = new LinkedList<Node>();
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            nodeQueue.offer(currentNode);
            list.add(currentNode.val);
        }
        result.add(list);
        list = new LinkedList<Integer>();
        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.poll();
            for (Node child : currentNode.children) {
                queue.offer(child);
            }
        }
        gatherLevels(result, queue, list);
    }
}