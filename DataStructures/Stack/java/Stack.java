// STACK [FIFO]

import java.util.ArrayList;
import java.util.List;

class Stack {
    static class StackStructure {
        List<Integer> stack;

        StackStructure() {
            stack = new ArrayList<>();
        }

        public void push(int value) {
            stack.add(value);
        }

        public int pop() {
            return stack.remove(stack.size() - 1);
        }

        public int peek() {
            return stack.get(stack.size() - 1);
        }

        public boolean isEmpty() {
            return stack.size() == 0;
        }
    }

    private static void print(int v) {
        System.out.println(String.format("Value: %d", v));
    }

    public static void main(String[] args) {
        StackStructure stack = new StackStructure();
        int[] nums = { 8, 129, 320, 3, 2, 392382, 32, 21, 832, 2321, 32012, 13, 242, 3982, 32302, 2183, 8, 12, 32, 333,
                222, 682, 35, 3843, };

        for (int num : nums) {
            stack.push(num);
        }

        while (!stack.isEmpty()) {
            print(stack.pop());
        }
    }
}