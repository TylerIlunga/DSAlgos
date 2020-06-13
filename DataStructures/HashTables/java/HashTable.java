public class HashTable {
    static int size;
    static LinkedList[] buckets;

    HashTable(int startingSize) {
        size = startingSize;
        buckets = new LinkedList[startingSize];
    }

    private static double loadFactor() {
        return Math.floor(size / buckets.length);
    }

    private static void increaseBucketSize() {
        int newSize = buckets.length * 2;
        System.out.println(String.format("increasing bucket size to: %d", newSize));
        LinkedList[] newBuckets = new LinkedList[newSize];

        for (int i = 0; i < buckets.length; i++) {
            newBuckets[i] = buckets[i];
        }

        buckets = newBuckets;
    }

    private static int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int) key.charAt(i);
        }
        System.out.println(String.format("hash: %d", hash));
        return hash;
    }

    public static void insert(String key) {
        if (loadFactor() >= 0.7) {
            increaseBucketSize();
        }

        int index = hash(key) % buckets.length;
        System.out.println(String.format("index: %d", index));
        if (buckets[index] == null) {
            buckets[index] = new LinkedList();
        }

        buckets[index].add(key);
    }

    public static int indexOf(String key) {
        int index = hash(key) % buckets.length;
        if (buckets[index] == null) {
            return -1;
        }

        LinkedList node = buckets[index];
        if (node.head.data == key) {
            return index;
        }

        return node.search(key) ? index : -1;
    }

    static class LinkedListNode {
        String data;
        LinkedListNode next;

        LinkedListNode(String d) {
            data = d;
        }
    }

    // Light-implementation for the sake of focus on the
    // hash table itself...
    static class LinkedList {
        LinkedListNode head;

        LinkedList() {
        }

        void add(String data) {
            if (head == null) {
                head = new LinkedListNode(data);
            }

            LinkedListNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = new LinkedListNode(data);
        }

        LinkedListNode get(int index) {
            if (head == null) {
                return null;
            }

            LinkedListNode curr = head;
            while (curr != null && index != 0) {
                curr = curr.next;
                index--;
            }

            return curr == null || index > 0 ? null : curr;
        }

        boolean search(String key) {
            if (head == null) {
                return false;
            }

            LinkedListNode curr = head;
            while (curr != null) {
                if (curr.data == key) {
                    return true;
                }
                curr = curr.next;
            }

            return false;
        }

    }

    public static void main(String[] args) {
        System.out.println("Hash Table:");

        String[] values = new String[] { "aa", "bb", "cc", "dd", "ee", "ff", "gg" };

        LinkedList llist = new LinkedList();
        for (String value : values) {
            llist.add(value);
        }

        for (String value : values) {
            System.out.println(llist.search(value));
        }

        System.out.println(llist.search("aa"));

        int i;
        for (i = 0; i <= values.length; i++) {
            System.out.println(llist.get(i).data);
        }

        i++;

        // NULL
        System.out.println(llist.get(i));

        // List of west coast (best coast) states
        String[] states = new String[] { "california", "oregon", "washington", "nevada", "montana", "idaho" };

        HashTable ht = new HashTable(5);
        for (String state : states) {
            ht.insert(state);
        }

        for (i = 0; i < states.length; i++) {
            System.out.println(String.format("Index of '%s' in hash table: %d", states[i], ht.indexOf(states[i])));
        }

        i++;

        // -1
        System.out.println(ht.indexOf("new jersey"));
        System.out.println(ht.indexOf("new york"));
    }
}
