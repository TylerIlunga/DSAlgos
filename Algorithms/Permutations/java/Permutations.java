public class Permutations {
    // Time: O(length of s1 + length of s2) ==> linear or O(n)
    private static boolean isPermutation(String s1, String s2) {
        // abc && // cab
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() == 0 || s2.length() == 0) {
            return s1.length() == 0 && s2.length() == 0;
        }

        boolean[] s1AsciiCharExists = new boolean[128];
        for (int i = 0; i < s1.length(); i++) {
            int asciiValue = s1.charAt(i);
            s1AsciiCharExists[asciiValue] = true;
        }
        for (int i = 0; i < s2.length(); i++) {
            int asciiValue = s2.charAt(i);
            if (!s1AsciiCharExists[asciiValue]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]) {
        System.out.println(String.format("abc is a permutation of cab: %s", isPermutation("abc", "cab")));
        System.out.println(String.format("abc is a permutation of cabd: %s", isPermutation("abc", "cabd")));
        System.out.println(String.format("abc is a permutation of cabd: %s", isPermutation("abc", "bac")));
        System.out.println(String.format("java is a permutation of avaj: %s", isPermutation("java", "avaj")));
        System.out
                .println(String.format("java is a permutation of javascript: %s", isPermutation("java", "javascript")));
        System.out.println(String.format("multithreading is a permutation of thatmulireding: %s",
                isPermutation("multithreading", "thatmulireding")));
        System.out.println(String.format("multithreading is a permutation of singlethread: %s",
                isPermutation("multithreading", "singlethread")));
    }
}