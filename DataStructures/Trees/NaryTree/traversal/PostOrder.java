
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
import java.util.Stack;

class PostOrder {
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        LinkedList<Integer> result = new LinkedList<Integer>();
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            result.addFirst(currentNode.val);
            for (Node child : currentNode.children) {
                stack.push(child);
            }
        }
        return result;
    }
}