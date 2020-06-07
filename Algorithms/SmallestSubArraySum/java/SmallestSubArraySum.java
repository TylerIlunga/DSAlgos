import java.util.Arrays;

public class SmallestSubArraySum {

  public static int findSmallestSubArraySum(int[] arr, int n, int x) {
    int end = 0, start = 0;
    int curr_sum = 0, min_len = 0;

    while (end < n) {
      while (curr_sum <= x && end < n) {
        curr_sum += arr[end++];
      }
      while (curr_sum > x && start < n) {
        if (end - start < min_len) {
          min_len = end - start;
        }
        curr_sum -= arr[start++];
      }
    }

    return min_len;
  }

  public static int[] evaluateArgs(String[] args) {
    int[] intArgs = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      intArgs[i] = Integer.parseInt(args[i]);
    }
    System.out.println(Arrays.toString(intArgs));
    return intArgs;
  }

  public static void main(String[] args) {
    int[] array = evaluateArgs(args);
    int output = findSmallestSubArraySum(array, 6, array.length);
    System.out.println("Smallest length" + output);
  }
}
