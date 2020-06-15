public class SortLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }
    }

    public static ListNode sortList(ListNode head) {
        // edge case
        if (head == null || head.next == null) {
            return head;
        }

        // Split the nodes you the runner's technique...
        // until both slow.next pointer and fast.next pointer is null
        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect temp list from slow list;
        temp.next = null;

        // Break down list until we reach a local "head" [4] or [2] ...
        ListNode leftHalf = sortList(head);
        ListNode rightHalf = sortList(slow);

        return mergeList(leftHalf, rightHalf);
    }

    private static ListNode mergeList(ListNode leftHalf, ListNode rightHalf) {
        ListNode mergedListHead = null;
        ListNode mergedListTail = null;

        while (leftHalf != null && rightHalf != null) {
            if (leftHalf.val < rightHalf.val) {
                if (mergedListHead == null) {
                    mergedListHead = leftHalf;
                    mergedListTail = mergedListHead;
                } else {
                    mergedListTail.next = leftHalf;
                    mergedListTail = mergedListTail.next;
                }
                leftHalf = leftHalf.next;
            } else {
                if (mergedListHead == null) {
                    mergedListHead = rightHalf;
                    mergedListTail = mergedListHead;
                } else {
                    mergedListTail.next = rightHalf;
                    mergedListTail = mergedListTail.next;
                }
                rightHalf = rightHalf.next;
            }
        }

        if (leftHalf == null && rightHalf != null) {
            mergedListTail.next = rightHalf;
        }
        if (rightHalf == null && leftHalf != null) {
            mergedListTail.next = leftHalf;
        }

        return mergedListHead;
    }

    public static void main(String[] args) {
        int[][] listValues = new int[][] { { 3, 4, 1, 2, 5 }, { 1, 5, 7, 9, 3 }, { 8, 2, 4, 1, 6, 10 },
                { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 }, { 2, 4, 6, 8, 10, 1, 3, 5, 7, 9 } };

        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();
        ListNode list3 = new ListNode();
        ListNode list4 = new ListNode();
        ListNode list5 = new ListNode();

        ListNode[] llists = new ListNode[] { list1, list2, list3, list4, list5 };

        System.out.println("Before....");
        System.out.println("--------------------");
        for (int i = 0; i < llists.length; i++) {
            ListNode current = llists[i];
            System.out.println(String.format("List #%d", i + 1));
            System.out.println("--------------------");
            for (int value : listValues[i]) {
                System.out.println(value);
                current.val = value;
                current.next = new ListNode();
                current = current.next;
            }
            System.out.println("--------------------");
        }

        for (ListNode llist : llists) {
            llist = sortList(llist);
        }

        System.out.println("After....");
        System.out.println("--------------------");
        for (int i = 0; i < llists.length; i++) {
            ListNode current = llists[i];
            System.out.println(String.format("List #%d", i + 1));
            System.out.println("--------------------");
            while (current != null) {
                System.out.println(current.val);
                current = current.next;
            }
            System.out.println("--------------------");
        }
    }

}