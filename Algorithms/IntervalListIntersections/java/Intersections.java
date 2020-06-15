import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Intersections {
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        // edge cases
        if (A.length == 0 && B.length == 0 || A.length == 0 || B.length == 0) {
            return new int[][] {};
        }

        // Set answer list
        List<int[]> intersection = new ArrayList<>();

        // Iterate through pairs of each list
        int aPairIndex = 0;
        int bPairIndex = 0;
        while (aPairIndex < A.length && bPairIndex < B.length) {
            int currAPairStart = A[aPairIndex][0];
            int currAPairEnd = A[aPairIndex][1];
            int currBPairStart = B[bPairIndex][0];
            int currBPairEnd = B[bPairIndex][1];
            if (currAPairStart > currBPairStart && currAPairStart > currBPairEnd) {
                bPairIndex++;
                continue;
            }
            if (currAPairStart < currBPairStart && currAPairEnd < currBPairStart) {
                aPairIndex++;
                continue;
            }

            int[] intersectionAnsPair = new int[2];

            intersectionAnsPair[0] = Math.max(currAPairStart, currBPairStart);
            intersectionAnsPair[1] = Math.min(currAPairEnd, currBPairEnd);

            if (intersectionAnsPair[1] == currAPairEnd) {
                aPairIndex++;
            } else {
                bPairIndex++;
            }

            intersection.add(intersectionAnsPair);
        }

        return intersection.toArray(new int[intersection.size()][]);
    }

    public static void main(String[] args) {
        int[][][] intervalsA = new int[][][] { {}, { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } },
                { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } }, };

        int[][][] intervalsB = new int[][][] { { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } }, {},
                { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } } };

        for (int interval = 0; interval < intervalsA.length; interval++) {
            int[][] intersection = intervalIntersection(intervalsA[interval], intervalsB[interval]);
            System.out.println(String.format("Intersection: %s", Arrays.deepToString(intersection)));
        }
    }
}