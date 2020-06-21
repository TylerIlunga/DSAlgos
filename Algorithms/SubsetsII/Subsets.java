import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsets {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> allSubsets = new ArrayList<List<Integer>>();
        if (nums.length == 1) {
            List<Integer> subsets = new ArrayList<>();
            allSubsets.add(new ArrayList<Integer>());
            subsets.add(nums[0]);
            allSubsets.add(subsets);
            return allSubsets;
        }

        Set<List<Integer>> ssNoDups = getSubsetsWithNoDups(new HashSet<List<Integer>>(), nums, 0);
        for (List<Integer> subset : ssNoDups) {
            allSubsets.add(subset);
        }
        return allSubsets;
    }

    private Set<List<Integer>> getSubsetsWithNoDups(Set<List<Integer>> allSubsets, int[] nums, int index) {
        if (nums.length == index) {
            allSubsets.add(new ArrayList<Integer>());
        } else {
            int term = nums[index];
            Set<List<Integer>> allCurrentSubsets = new HashSet<List<Integer>>();

            allSubsets = getSubsetsWithNoDups(allSubsets, nums, index + 1);
            for (List<Integer> subset : allSubsets) {
                List<Integer> clone = new ArrayList<>();
                clone.addAll(subset);
                clone.add(term);
                Collections.sort(clone);
                allCurrentSubsets.add(clone);
            }

            allSubsets.addAll(allCurrentSubsets);

        }
        return allSubsets;
    }

    public static void main(String[] args) {
        // Leetcode test cases...
    }
}