public class OddEvenList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }
    }

    // Given a singly linked list, group all odd nodes together followed by the even
    // nodes.
    // Please note here we are talking about the node number and not the value in
    // the nodes
    private static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode oddsListHead = null;
        ListNode oddsListTail = null;
        ListNode evensListHead = null;
        ListNode evensListTail = null;

        int index = 1;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (index % 2 == 0) {
                if (evensListHead == null) {
                    evensListHead = head;
                    evensListTail = evensListHead;
                } else {
                    evensListTail.next = head;
                    evensListTail = evensListTail.next;
                }
            } else {
                if (oddsListHead == null) {
                    oddsListHead = head;
                    oddsListTail = oddsListHead;
                } else {
                    oddsListTail.next = head;
                    oddsListTail = oddsListTail.next;
                }
            }
            head = next;
            index++;
        }

        if (oddsListHead == null) {
            return evensListHead;
        }

        oddsListTail.next = evensListHead;

        return oddsListHead;
    }

    public static void main(String[] args) {
        int[][] listValues = new int[][] { { 1, 2, 3, 4, 5 }, { 1, 3, 5, 7, 9 }, { 2, 4, 6, 8, 10 },
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
            llist = oddEvenList(llist);
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