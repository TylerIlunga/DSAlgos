public class HelloWorld {

    static class MergeSortClass {
        MergeSortClass() {
        }

        void merge(int[] list, int start, int mid, int end) {
            // Reason for the O(n) auxillary space...
            int[] mergeHelper = new int[list.length];
            for (int i = 0; i < list.length; i++) {
                mergeHelper[i] = list[i];
            }

            int left = start;
            int right = mid + 1;
            int helperPointer = left;
            while (left <= mid && right <= end) {
                if (mergeHelper[left] <= mergeHelper[right]) {
                    list[helperPointer++] = mergeHelper[left++];
                } else {
                    list[helperPointer++] = mergeHelper[right++];
                }
            }

            for (int i = 0; i <= (mid - left); i++) {
                list[helperPointer + i] = mergeHelper[left + i];
            }

        }

        void sort(int[] list, int start, int end) {
            if (start < end) {
                int mid = (start + end) / 2;
                sort(list, 0, mid);
                sort(list, mid + 1, end);
                merge(list, start, mid, end);
            }
        }

        void mergeSort(int[] list) {
            if (list == null || list.length < 2) {
                return;
            }
            sort(list, 0, list.length - 1);
        }
    }

    static class BSTNode {
        int key;
        BSTNode left;
        BSTNode right;

        BSTNode(int k) {
            key = k;
        }
    }

    static class BST {
        BSTNode root;

        BST() {
        }

        void insert(int newKey) {
            if (root == null) {
                root = new BSTNode(newKey);
                return;
            }

            BSTNode curr = root;
            while (true) {
                if (newKey <= curr.key) {
                    if (curr.left == null) {
                        curr.left = new BSTNode(newKey);
                        break;
                    }
                    curr = curr.left;
                } else {
                    if (curr.right == null) {
                        curr.right = new BSTNode(newKey);
                        break;
                    }
                    curr = curr.right;
                }
            }
        }

        void traversal(int type) {
            switch (type) {
                case 0:
                    preorderT(root);
                    break;
                case 1:
                    inorderT(root);
                    break;
                case 2:
                    postorderT(root);
                    break;
                default:
                    break;
            }
        }

        void preorderT(BSTNode curr) {
            if (curr == null) {
                return;
            }

            System.out.println(String.format("Key: %d", curr.key));
            preorderT(curr.left);
            preorderT(curr.right);
        }

        void inorderT(BSTNode curr) {
            if (curr == null) {
                return;
            }

            inorderT(curr.left);
            System.out.println(String.format("Key: %d", curr.key));
            inorderT(curr.right);
        }

        void postorderT(BSTNode curr) {
            // Check your children first
            if (curr == null) {
                return;
            }

            postorderT(curr.left);
            postorderT(curr.right);
            System.out.println(String.format("Key: %d", curr.key));
        }

        void delete(int key) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                if (root.key == key) {
                    root = null;
                    return;
                }
            }

            BSTNode curr = root;
            BSTNode prev = root;
            while (true) {
                if (curr == null) {
                    System.out.println(String.format("Key (%d) not found", key));
                    break;
                }
                if (curr.key != key) {
                    prev = curr;
                    curr = key <= curr.key ? curr.left : curr.right;
                } else {
                    // 3 classes
                    // 1) Leaf node
                    if (curr.left == null && curr.right == null) {
                        System.out.println("Class 1");
                        if (prev.left != null && prev.left.key == curr.key) {
                            prev.left = null;
                        } else {
                            prev.right = null;
                        }
                        break;
                    }
                    // 2) Node has two children
                    if (curr.left != null && curr.right != null) {
                        // inorder successor[ios] (right node's left most node)
                        BSTNode ios = curr.right;
                        while (ios.left != null) {
                            prev = ios;
                            ios = ios.left;
                        }
                        curr.key = ios.key;
                        prev.left = null;
                        break;
                    }
                    // 3) Node has one child
                    if (curr.left != null) {
                        curr.key = curr.left.key;
                        curr.left = null;
                    } else {
                        curr.key = curr.right.key;
                        curr.right = null;
                    }
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        // int[] initValues = new int[] {12, 10, 15, 14, 13, 16, 8, 11, 9};
        int[] initValues = new int[] { 50, 40, 70, 60, 80 };
        BST tree = new BST();
        for (int value : initValues) {
            tree.insert(value);
        }

        // 12
        // / \
        // 10 15
        // / \ / \
        // 8 11 14 16
        // \ /
        // 9 13

        // tree.delete(9);
        // tree.delete(14);
        tree.delete(50);

        tree.traversal(0);

        // MergeSortClass msSorter = new MergeSortClass();
        // long startTime = System.nanoTime();
        // msSorter.mergeSort(initValues);
        // long endTime = System.nanoTime();

        // double msTime = (endTime - startTime) / 1e6;

        // startTime = System.nanoTime();
        // tree.traversal(1);
        // endTime = System.nanoTime();

        // double tTime = (endTime - startTime) / 1e6;

        // System.out.println(String.format("Merge Sort (ms): %f", msTime));
        // System.out.println(String.format("BST InOrder T (ms): %f", tTime));

    }
}
