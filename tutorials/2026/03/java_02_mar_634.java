// Trie.java

public class Trie {
    // Node class representing a node in the Trie
    private static class Node {
        // Children nodes
        private Node[] children = new Node[26];
        // End of word flag
        private boolean endOfWord;
        // Character associated with this node
        private char character;

        public Node() {
            // Initialize children array and end of word flag
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            endOfWord = false;
        }

        public void setCharacter(char c) {
            // Set character associated with this node
            character = c;
        }

        public Node getChild(char c) {
            // Return child node corresponding to character c
            return children[c - 'a'];
        }

        public boolean isEndOfWord() {
            // Return true if this node represents the end of a word
            return endOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            // Set end of word flag
            this.endOfWord = endOfWord;
        }
    }

    // Root node of the Trie
    private Node root;

    public Trie() {
        // Initialize root node
        root = new Node();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        // Start at the root node
        Node currentNode = root;
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // Find the child node corresponding to the current character
            Node childNode = currentNode.getChild(c);
            // If child node does not exist, create a new one
            if (childNode == null) {
                childNode = new Node();
                currentNode.getChild(c).setChild(childNode);
            }
            // Move to the child node
            currentNode = childNode;
        }
        // Mark the end of the word
        currentNode.setEndOfWord(true);
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        // Start at the root node
        Node currentNode = root;
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // If child node does not exist, word is not in Trie
            if (currentNode.getChild(c) == null) {
                return false;
            }
            // Move to the child node
            currentNode = currentNode.getChild(c);
        }
        // Return true if word is marked as end of word
        return currentNode.isEndOfWord();
    }

    // Check if a word starts with a prefix in the Trie
    public boolean startsWith(String prefix) {
        // Start at the root node
        Node currentNode = root;
        // Iterate over each character in the prefix
        for (char c : prefix.toCharArray()) {
            // If child node does not exist, prefix is not in Trie
            if (currentNode.getChild(c) == null) {
                return false;
            }
            // Move to the child node
            currentNode = currentNode.getChild(c);
        }
        // Return true if we reach this point
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("banana");