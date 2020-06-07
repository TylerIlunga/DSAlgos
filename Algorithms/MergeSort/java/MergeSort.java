import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MergeSort {
    MergeSort() {
    }

    public static void sort(int[] array) {
        int[] helper = new int[array.length];
        sort(array, helper, 0, array.length - 1);
    }

    private static void sort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middleIndex = (high + low) / 2;
            sort(array, helper, low, middleIndex); // Sort left array
            sort(array, helper, middleIndex + 1, high); // Sort right array
            merge(array, helper, low, middleIndex, high); // Merge sorted arrays
        }
    }

    private static void merge(int[] array, int[] helper, int low, int middle, int high) {
        // Copy all elements from given array to helper array for manipulation.
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeftIndex = low;
        int helperRightIndex = middle + 1;
        int current = low;

        // Iterate through helper array.
        while (helperLeftIndex <= middle && helperRightIndex <= high) {
            // Compare the left and right half and copy back the smaller element from the
            // two halves into the given array(original).
            if (helper[helperLeftIndex] <= helper[helperRightIndex]) {
                array[current++] = helper[helperLeftIndex++];
            } else {
                array[current++] = helper[helperRightIndex++];
            }
        }

        // Copy the rest of the left side of the helper array into the given array;
        for (int i = 0; i <= (middle - helperLeftIndex); i++) {
            array[current + i] = helper[helperLeftIndex + i];
        }
    }

    public static void main(String[] args) {
        int[] test0 = { 8, 129, 320, 3, 2, 392382, 32, 21, 832, 2321, 32012, 13, 242, 3982, 32302, 2183, 8, 12, 32, 333,
                222, 682, 35, 3843, };
        System.out.println(String.format("Before:\n%s", Arrays.toString(test0)));
        long startTime = System.nanoTime();
        sort(test0);
        long endTime = System.nanoTime();
        double durationInMs = (endTime - startTime) * 1e-6;
        long durationInSeconds = TimeUnit.MILLISECONDS.toSeconds((endTime - startTime) / 1000000);
        System.out.println(String.format("After:\n%s\nTime:\nseconds: %s\nmilliseconds: %s", Arrays.toString(test0),
                durationInSeconds, durationInMs));
    }
}
