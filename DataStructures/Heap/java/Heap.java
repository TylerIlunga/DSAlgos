import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class HelloWorld {
    static class MaxHeap {
        List<Integer> heap;

        MaxHeap() {
            heap = new ArrayList<>();
        }

        MaxHeap(List<Integer> list) {
            heap = new ArrayList<>();
            listToHeap(list);
        }

        int getChild(int parent, boolean leftChild) {
            // Handle zero base
            return leftChild ? 2 * parent + 1 : 2 * parent + 2;
        }

        int getParent(int child) {
            return (child - 1) / 2;
        }

        void insert(int key) {
            if (heap.size() == 0) {
                heap.add(key);
                return;
            }
            heap.add(key);
            bubbleUp(heap.size() - 1);
        }

        void heapify(int parent) {
            int leftChild = getChild(parent, true);
            int rightChild = getChild(parent, false);
            int largest = parent;

            // System.out.println("Left and Right children-----");
            // System.out.println(leftChild);
            // System.out.println(rightChild);

            if (leftChild < heap.size() && heap.get(leftChild) > heap.get(parent)) {
                largest = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
                largest = rightChild;
            }

            // System.out.println("Largest/parent-----");
            // System.out.println(largest);
            // System.out.println(parent);

            if (largest != parent) {
                int temp = heap.get(parent);
                heap.set(parent, heap.get(largest));
                heap.set(largest, temp);
                heapify(largest);
            }

        }

        void bubbleUp(int child) {
            int parent = getParent(child);
            if (parent >= 0 && heap.get(child) > heap.get(parent)) {
                int temp = heap.get(child);
                heap.set(child, heap.get(parent));
                heap.set(parent, temp);
                bubbleUp(parent);
            }
        }

        int max() {
            return heap.get(0);
        }

        void removeMax() {
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapify(0);
        }

        void listToHeap(int[] list) {
            if (list == null || list.length == 0) {
                return;
            }

            if (list.length == 1) {
                heap.add(list[0]);
                return;
            }

            for (int l : list) {
                heap.add(l);
            }

            for (int i = list.length / 2; i >= 0; i--) {
                heapify(i);
            }
        }

        void listToHeap(List<Integer> list) {
            if (list == null || list.size() == 0) {
                return;
            }

            if (list.size() == 1) {
                heap.add(list.get(0));
                return;
            }

            heap = list;

            for (int i = list.size() / 2; i >= 0; i--) {
                heapify(i);
            }
        }

        List<Integer> sort(List<Integer> list) {
            listToHeap(list);

            // System.out.println("INITIALLY LIST-----");
            // System.out.println(heap.toString());

            list = new ArrayList<Integer>();

            while (heap.size() != 0) {
                int temp = heap.get(heap.size() - 1);
                heap.set(heap.size() - 1, max());
                heap.set(0, temp);

                // System.out.println(heap.toString());

                list.add(0, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);

                // System.out.println("POP-----");

                // System.out.println(heap.toString());

                // System.out.println("HEAPIFY-----");

                heapify(0);
            }

            return list;
        }

    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        int[] test = new int[] { 16, 14, 10, 8, 7, 9, 3, 2, 4, 1 };
        List<Integer> testList = new ArrayList<Integer>();
        MaxHeap heap = new MaxHeap();

        // for (int t : test) {
        // heap.insert(t);
        // }

        // OR

        // heap.listToHeap(test);

        // System.out.println(heap.max());
        // System.out.println(heap.getChild(0, true));
        // System.out.println(heap.getChild(0, false));
        // System.out.println(heap.getParent(1, true));
        // System.out.println(heap.getParent(2, false));

        // heap.removeMax();

        // System.out.println("heap.max() NEW");

        // System.out.println(heap.max());

        // System.out.println("-------");

        // for (int node : heap.heap) {
        // System.out.println(node);
        // }

        for (int t : test) {
            testList.add(t);
        }

        testList = heap.sort(testList);

        for (int nv : testList) {
            System.out.println(nv);
        }

    }
}
