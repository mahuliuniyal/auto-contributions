// Trie Data Structure Implementation in Java

public class Trie {

    // Node class representing a single node in the trie
    private static class Node {
        char character;
        boolean isEndOfWord;
        Node[] children;

        public Node(char character) {
            this.character = character;
            this.isEndOfWord = false;
            this.children = new Node[26];  // Array of child nodes for each character
        }
    }

    private Node root;  // Root node of the trie

    public Trie() {
        this.root = new Node('\0');  // '\0' is used as a sentinel value
    }

    // Insert a word into the trie
    public void insert(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';  // Convert character to index in children array
            if (current.children[index] == null) {  // If child node doesn't exist, create it
                current.children[index] = new Node(c);
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;  // Mark the end of the word
    }

    // Search for a word in the trie
    public boolean search(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {  // If child node doesn't exist, return false
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;  // Return true if the word exists, false otherwise
    }

    // Check if a prefix exists in the trie
    public boolean startsWith(String prefix) {
        Node current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {  // If child node doesn't exist, return false
                return false;
            }
            current = current.children[index];
        }
        return true;  // Return true if the prefix exists
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: true
        System.out.println(trie.search("ap"));      // Output: false

        System.out.println(trie.startsWith("app"));  // Output: true
        System.out.println(trie.startsWith("ban"));  // Output: true
        System.out.println(trie.startsWith("ora"));  // Output: false
    }
}