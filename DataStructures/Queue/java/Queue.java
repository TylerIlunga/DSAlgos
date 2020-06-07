// Queue [FILO]
// 1) PUSH/ENQUEUE()
// 2) POP/DEQUEUE()
// 3) Peek/OFFER()

class QueueStructure {
    ArrayStructure stack;

    QueueStructure(int l) {
        stack = new ArrayStructure(l);
    }

    public void enqueue(int value) {
        stack.push(value);
    }

    public int dequeue() {
        return stack.unshift();
    }

    public int peek(int value) {
        return stack.get(stack.getSize());
    }
}

class Queue {
    private static void print(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        QueueStructure queue = new QueueStructure(69);

    }
}