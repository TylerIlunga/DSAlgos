// Queue [LIFO]

import java.util.ArrayList;
import java.util.List;

class Queue {
    static class QueueStructure {
        List<Integer> stack;

        QueueStructure() {
            stack = new ArrayList<>();
        }

        public void enqueue(int value) {
            stack.add(value);
        }

        public int dequeue() {
            return stack.remove(0);
        }

        public int peek(int value) {
            return stack.get(0);
        }

        public boolean isEmpty() {
            return stack.size() == 0;
        }
    }

    private static void print(int v) {
        System.out.println(String.format("Value: %d", v));
    }

    public static void main(String[] args) {
        QueueStructure queue = new QueueStructure();
        int[] nums = { 8, 129, 320, 3, 2, 392382, 32, 21, 832, 2321, 32012, 13, 242, 3982, 32302, 2183, 8, 12, 32, 333,
                222, 682, 35, 3843, };

        for (int num : nums) {
            queue.enqueue(num);
        }

        while (!queue.isEmpty()) {
            print(queue.dequeue());
        }
    }
}