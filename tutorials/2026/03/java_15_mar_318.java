// Trie.java
public class Trie {
    // Node class representing a node in the trie
    static class Node {
        String word;
        boolean isEndOfWord;
        Node[] children;

        public Node() {
            this.children = new Node[26];
            this.word = null;
            this.isEndOfWord = false;
        }
    }

    // Root of the trie
    Node root;

    // Constructor to initialize the trie
    public Trie() {
        this.root = new Node();
    }

    // Method to insert a word into the trie
    void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        // Mark the end of the word
        current.isEndOfWord = true;
        current.word = word;
    }

    // Method to search for a word in the trie
    boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        // If the word is found, check if it's a complete word
        return current.isEndOfWord;
    }

    // Method to check if a prefix exists in the trie
    boolean startsWith(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        // If the prefix exists, it's a complete word
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("apple"));  // Output: true
        System.out.println(trie.search("app"));     // Output: true
        System.out.println(trie.search("banana"));  // Output: true

        System.out.println(trie.startsWith("app"));  // Output: true
        System.out.println(trie.startsWith("bana"));  // Output: false
    }
}