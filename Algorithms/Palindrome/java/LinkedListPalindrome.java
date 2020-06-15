public class LinkedListPalindrome {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // Runner technique to split the LL in half...
        // Slower with stack but the concept is the same...
        // Stack<Integer> reversedListData = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // reversedListData.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        // Old elements 0->1->2->1->0, slow points to 2...point to 1 please :D
        if (fast != null) {
            slow = slow.next;
        }

        ListNode prev = null;
        ListNode reversed = slow;
        ListNode curr = reversed;
        ListNode next = curr;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        curr = prev;
        reversed = curr;
        slow = head;

        // Assert that right half is the reverse of the left half
        while (reversed != null) {
            // Slower...
            // if (slow.val != reversedListData.pop()) {
            // return false;
            // }
            if (slow.val != reversed.val) {
                return false;
            }
            slow = slow.next;
            reversed = reversed.next;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] listValues = new int[][] { { 3, 4, 1, 2, 5 }, { 1, 2, 1 }, { 8, 2, 4, 1, 6, 10 },
                { 1, 3, 5, 7, 9, 9, 7, 5, 3, 1 }, { 2, 4, 6, 8, 10, 10, 8, 6, 4, 2 } };

        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();
        ListNode list3 = new ListNode();
        ListNode list4 = new ListNode();
        ListNode list5 = new ListNode();

        ListNode[] llists = new ListNode[] { list1, list2, list3, list4, list5 };

        System.out.println("--------------------");
        for (int i = 0; i < llists.length; i++) {
            ListNode current = llists[i];
            System.out.println(String.format("List #%d", i + 1));
            System.out.println("--------------------");
            for (int j = 0; j < listValues[i].length; j++) {
                int value = listValues[i][j];

                System.out.println(value);

                current.val = value;

                if (j != listValues[i].length - 1) {
                    current.next = new ListNode();
                    current = current.next;
                }
            }
            System.out.println("--------------------");
        }

        System.out.println("isPalindrome()");
        System.out.println("--------------------");
        for (int i = 0; i < llists.length; i++) {
            System.out.println(String.format("List #%d is a palindrome: %s", i + 1, isPalindrome(llists[i])));
            System.out.println("--------------------");
        }
    }
}