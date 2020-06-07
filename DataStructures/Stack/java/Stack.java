// STACK [FIFO]
// 1) PUSH/POLL()
// 2) POP()
// 3) Peek()

class StackStructure {
    ArrayStructure stack;

    StackStructure(int l) {
        stack = new ArrayStructure(l);
    }

    public void push(int value) {
        stack.push(value);
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.get(stack.getSize());
    }
}

class Stack {
    private static void print(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        StackStructure stack = new StackStructure(69);

    }
}