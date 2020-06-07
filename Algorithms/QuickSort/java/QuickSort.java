public class QuickSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getPivot(int[] arr, int low, int high) {
        int pivot = arr[high];
        int newLow = low - 1;

        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                newLow++;
                swap(arr, i, newLow);
            }

        }

        swap(arr, newLow + 1, high);

        return newLow + 1;
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = getPivot(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 8, 129, 320, 3, 2, 392382, 32, 21, 832, 2321, 32012, 13, 242, 3982, 32302, 2183, 8, 12, 32, 333,
                222, 682, 35, 3843, };
        sort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}