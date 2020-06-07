import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

class BinarySearch {
    BinarySearch() {
    }

    private static boolean search(int[] array, int target) {
        System.out.println(String.format("Target: %d", target));
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (array[middle] < target) {
                low = middle + 1;
            } else if (array[middle] > target) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You must provide a target(int) argument.");
        }
        int[] test0 = { 8, 129, 320, 3, 2, 392382, 32, 21, 832, 2321, 32012, 13, 242, 3982, 32302, 2183 };
        System.out.println(String.format("Searching for %s in:\n%s", args[0], Arrays.toString(test0)));
        System.out.println("Sorting array...");
        Arrays.sort(test0);
        System.out.println(String.format("Sorted array:\n%s", Arrays.toString(test0)));
        System.out.println("Searching...");
        System.out.println(String.format("%s is in array: %s", args[0], search(test0, Integer.parseInt(args[0]))));
        String a = "Basic ".concat(String.valueOf(Base64.getEncoder().encodeToString(
                "vuzedazebejuge3yhugy9eqema5a5ure9u2usara:ryje5yrupeze2esybery".getBytes(StandardCharsets.UTF_8))));
        System.out.println(a);
    };
}