class Trie {

    private class TrieNode {
        char letter;
        boolean endOfWord;
        TrieNode[] children;

        TrieNode() {
            endOfWord = false;
            children = new TrieNode[128];
        }

        TrieNode(char l) {
            this();
            this.letter = l;
        }

        public TrieNode getChild(char c) {
            if (c >= 65 || c <= 90 || c >= 97 || c <= 122) {
                return children[c];
            }
            throw new IllegalArgumentException("Character is not an ASCII lowercase or uppercase letter.");
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void setEndOfWord(boolean isEndOfWord) {
            endOfWord = isEndOfWord;
        }

        public void addWord(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            char firstLetter = word.charAt(0);

            TrieNode child = getChild(firstLetter);
            if (child == null) {
                child = new TrieNode(firstLetter);
                children[firstLetter] = child;
            }

            if (word.length() > 1) {
                child.addWord(word.substring(1));
            } else {
                child.setEndOfWord(true);
            }
        }

    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.addWord(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return startsWith(word, true);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, false);
    }

    private boolean startsWith(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null) {
                return false;
            }
        }
        return !exact || lastNode.isEndOfWord();
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */