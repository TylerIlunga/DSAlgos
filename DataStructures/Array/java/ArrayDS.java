class ArrayDS {
    private static int LENGTH;
    private static int size;
    private static int[] nums;

    ArrayDS(int l) {
        LENGTH = l;
        size = 0;
        nums = new int[LENGTH];
    }

    private void extendArray() {
        int[] newArray = new int[LENGTH];
        for (int i = 0; i < size; i++) {
            newArray[i] = nums[i];
        }
        nums = newArray;
    }

    private void handleUnshift() {
        int[] newArray = new int[LENGTH];
        for (int i = 1; i <= size; i++) {
            newArray[i - 1] = nums[i];
        }
        nums = newArray;
    }

    public void push(int value) {
        if (size == 0) {
            nums[0] = value;
            size++;
            return;
        }
        if (size == LENGTH) {
            LENGTH *= 2;
            extendArray();
        }

        nums[size] = value;
        size++;
    }

    public int pop() {
        if (size == 0) {
            return 0;
        }
        int value = nums[size - 1];

        nums[size - 1] = 0;
        size--;

        return value;
    }

    public int unshift() {
        if (size == 0) {
            return 0;
        }
        int value = nums[0];

        nums[0] = 0;
        size--;

        handleUnshift();

        return value;
    }

    public int get(int index) {
        if (index < 0 || index > LENGTH - 1) {
            throw new IndexOutOfBoundsException("Index of out bounds");
        }
        return nums[index];
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return LENGTH;
    }
}

public class Array {
    private static void print(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        ArrayDS arr = new ArrayDS(2);
        arr.push(1);
        arr.push(2);
        arr.push(3);
        print(arr.get(0));
        print(arr.get(1));
        print(arr.get(2));
        print(arr.get(3));
        arr.push(4);
        print(arr.get(3));
        print(arr.unshift());
        print(arr.pop());
        print(arr.get(3));
        print(arr.get(0));
        print(arr.get(1));
    }
}