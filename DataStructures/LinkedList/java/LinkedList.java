class LinkedList {
    static class SingleLLNode {
        private int data;
        SingleLLNode next;

        SingleLLNode(int d) {
            data = d;
            next = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int d) {
            data = d;
        }
    }

    static class DoubleLLNode {
        private int data;
        DoubleLLNode prev;
        DoubleLLNode next;

        DoubleLLNode(int d) {
            data = d;
            next = null;
            prev = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int d) {
            data = d;
        }
    }

    static class SingleLinkedList {
        SingleLLNode head;

        SingleLinkedList() {
            head = null;
        }

        public void add(int data) {
            if (head == null) {
                head = new SingleLLNode(data);
                return;
            }
            if (head.next == null) {
                head.next = new SingleLLNode(data);
                return;
            }

            SingleLLNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = new SingleLLNode(data);
        }

        public void remove(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            if (index == 0) {
                head = head == null ? head : head.next;
                return;
            }

            SingleLLNode prev = head;
            SingleLLNode curr = head;
            while (curr != null && index != 0) {
                prev = curr;
                curr = curr.next;
                index--;
            }

            if (index != 0 && curr == null) {
                throw new IllegalArgumentException("index out of bounds");
            }

            if (curr == null) {
                return;
            }

            prev.next = curr.next;
            curr = null;
        }

        private SingleLLNode searchForNode(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            String nonExistentNodeError = "Node does not exist at that index";
            if (index == 0) {
                if (head == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head;
            }
            if (index == 1) {
                if (head == null || head.next == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.next;
            }

            SingleLLNode curr = head;
            while (curr != null && index != 0) {
                curr = curr.next;
                index--;
            }

            if (index != 0 && curr == null) {
                throw new IllegalArgumentException("index out of bounds");
            }

            if (curr == null) {
                throw new Error(nonExistentNodeError);
            }

            return curr;
        }

        public int search(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            String nonExistentNodeError = "Node does not exist at that index";
            if (index == 0) {
                if (head == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.getData();
            }
            if (index == 1) {
                if (head == null || head.next == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.next.getData();
            }

            return searchForNode(index).getData();
        }

        public void update(int index, int data) {
            searchForNode(index).setData(data);
        }

        private SingleLinkedList mergeTwoLists(SingleLLNode l1, SingleLLNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            SingleLLNode head = null;
            SingleLLNode tail = null;

            if (l1.data <= l2.data) {
                head = l1;
                tail = head;
                l1 = l1.next;
            } else {
                head = l2;
                tail = head;
                l2 = l2.next;
            }

            while (l1 != null && l2 != null) {
                if (l1.data <= l2.data) {
                    tail.next = l1;
                    tail = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    tail = l2;
                    l2 = l2.next;
                }
            }

            tail.next = l1 == null ? l2 : l1;

            return head;
        }
    }

    static class DoubleLinkedList {
        DoubleLLNode head;
        DoubleLLNode tail;

        DoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void add(int data) {
            if (head == null) {
                head = new DoubleLLNode(data);
                tail = head;
                return;
            }
            if (head.next == null) {
                head.next = new DoubleLLNode(data);
                head.next.prev = head;
                tail = head.next;
                return;
            }

            DoubleLLNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = new DoubleLLNode(data);
            curr.next.prev = curr;
            tail = curr.next;
        }

        private DoubleLLNode searchForNode(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            String nonExistentNodeError = "Node does not exist at that index";
            if (index == 0) {
                if (head == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head;
            }
            if (index == 1) {
                if (head == null || head.next == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.next;
            }

            DoubleLLNode curr = head;
            while (curr != null && index != 0) {
                curr = curr.next;
                index--;
            }

            if (index != 0 && curr == null) {
                throw new IllegalArgumentException("index out of bounds");
            }

            if (curr == null) {
                throw new Error(nonExistentNodeError);
            }

            return curr;
        }

        public void remove(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            if (index == 0) {
                head = head == null ? head : head.next;
                return;
            }

            DoubleLLNode curr = searchForNode(index);

            curr.prev.next = curr.next;
            if (curr.next == null) {
                tail = curr;
            }

            curr = null;
        }

        public int search(int index) {
            if (index < 0) {
                throw new IllegalArgumentException("index out of bounds");
            }

            String nonExistentNodeError = "Node does not exist at that index";
            if (index == 0) {
                if (head == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.getData();
            }
            if (index == 1) {
                if (head == null || head.next == null) {
                    throw new Error(nonExistentNodeError);
                }
                return head.next.getData();
            }

            return searchForNode(index).getData();
        }

        public void update(int index, int data) {
            searchForNode(index).setData(data);
        }
    }

    private static void print(String str, int v) {
        System.out.println(String.format(str, v));
    }

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        DoubleLinkedList dll = new DoubleLinkedList();

        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        for (int v : values) {
            sll.add(v);
            dll.add(v);
        }

        print("sll head: %d", sll.head.getData());
        sll.remove(0);
        print("sll head: %d", sll.head.getData());
        // sll.remove(-1);
        // sll.remove(10);
        print("sll node at index 2: %d", sll.search(2));
        sll.remove(2);
        print("sll node at index 2: %d", sll.search(2));
        // sll.update(-1, 9);
        // sll.update(10, 9);
        sll.update(2, 9);
        print("sll node at index 2: %d", sll.search(2));

        print("dll head: %d", dll.head.getData());
        print("dll tail: %d", dll.tail.getData());
        dll.remove(0);
        print("dll head: %d", dll.head.getData());
        print("dll tail: %d", dll.tail.getData());
        // dll.remove(-1);
        // dll.remove(10);
        print("dll node at index 4: %d", dll.search(4));
        dll.remove(4);
        print("dll node at index 4: %d", dll.search(4));
        // dll.update(-1, 9);
        // dll.update(10, 9);
        dll.update(5, 20);
        print("dll node at index 5: %d", dll.search(5));
        dll.update(0, 0);
        print("dll node at index 0: %d", dll.search(0));
    }
}