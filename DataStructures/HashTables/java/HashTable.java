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

    public static void insert(String key, int data) {
        if (loadFactor() >= 0.7) {
            increaseBucketSize();
        }

        int index = hash(key) % buckets.length;
        System.out.println(String.format("index: %d", index));
        if (buckets[index] == null) {
            buckets[index] = new LinkedList();
        }

        buckets[index].add(key, data);
    }

    public static int indexOf(String key) {
        int index = hash(key) % buckets.length;
        if (buckets[index] == null) {
            return -1;
        }

        LinkedList list = buckets[index];
        if (list.head.key == key) {
            return index;
        }

        return list.search(key) ? index : -1;
    }

    public static int get(String key) {
        int index = hash(key) % buckets.length;
        if (buckets[index] == null) {
            return -1;
        }
        return buckets[index].get(key).data;
    }

    static class LinkedListNode {
        String key;
        int data;
        LinkedListNode next;

        LinkedListNode(String k, int d) {
            key = k;
            data = d;
        }
    }

    // Light-implementation for the sake of focus on the
    // hash table itself...
    static class LinkedList {
        LinkedListNode head;

        LinkedList() {
        }

        void add(String key, int data) {
            if (head == null) {
                head = new LinkedListNode(key, data);
            }

            LinkedListNode curr = head;
            while (curr.next != null) {
                if (curr.key == key) {
                    curr.data = data;
                    return;
                }
                curr = curr.next;
            }

            curr.next = new LinkedListNode(key, data);
        }

        LinkedListNode nodeAtIndex(int index) {
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

        LinkedListNode get(String key) {
            if (head == null) {
                return null;
            }

            LinkedListNode curr = head;
            while (curr != null) {
                if (curr.key == key) {
                    return curr;
                }
                curr = curr.next;
            }

            return null;
        }

        boolean search(String key) {
            if (head == null) {
                return false;
            }

            LinkedListNode curr = head;
            while (curr != null) {
                if (curr.key == key) {
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
        int i = 0;
        for (String value : values) {
            llist.add(value, i);
            i++;
        }

        for (String value : values) {
            System.out.println(value);
            System.out.println(llist.get(value).data);
        }

        System.out.println(llist.search("aa"));

        for (i = 0; i <= values.length; i++) {
            System.out.println(llist.nodeAtIndex(i).data);
        }

        i++;

        // NULL
        System.out.println(llist.nodeAtIndex(i));

        // List of west coast (best coast) states
        String[] states = new String[] { "california", "oregon", "washington", "nevada", "montana", "idaho" };

        HashTable ht = new HashTable(5);

        i = 0;

        for (String state : states) {
            ht.insert(state, i);
            i++;
        }

        for (i = 0; i < states.length; i++) {
            System.out.println(String.format("Data for key '%s' in hash table: %d", states[i], ht.get(states[i])));
        }

        i++;

        // NullPointerException: does not exist in Hash Table
        System.out.println(ht.get("new jersey"));
        System.out.println(ht.get("new york"));
    }
}
