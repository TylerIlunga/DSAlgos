// LinkedList
// 1) Pointers: head(single/double), tail(double)
// 2) Nodes: prev(double), data(single/double), next(single/double)

class SingleLLNode {
    private int data;
    private SingleLLNode next;

    public void setData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }

    public SingleLLNode next() {
        return next;
    }
}

class DoubleLLNode {
    private DoubleLLNode prev;
    private int data;
    private DoubleLLNode next;

    public DoubleLLNode prev() {
        return prev;
    }

    public void setData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }

    public DoubleLLNode next() {
        return next;
    }
}

class SingleLinkedList {
    SingleLLNode head;

    SingleLinkedList() {
    }

    public void add(int data) {
    }

    public void remove(int index) {
    }

    public int search(int index) {
        return 0;
    }

    public void update(int index, int data) {

    }
}

class DoubleLinkedList {
    DoubleLLNode head;
    DoubleLLNode tail;

    public void add(int data) {
    }

    public void remove(int index) {
    }

    public int search(int index) {
        return 0;
    }

    public void update(int index, int data) {

    }
}

class LinkedList {
    public static void main(String[] args) {

    }
}