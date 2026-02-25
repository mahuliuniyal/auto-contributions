// Trie.java

/**
 * Represents a Trie data structure in Java.
 * A Trie (also known as a prefix tree) is a tree-like data structure in which each node is associated with a string.
 * This implementation provides methods to insert, search, and delete strings in the Trie.
 */
public class Trie {
    // Node class representing a node in the Trie
    private class Node {
        // Children nodes
        Node[] children = new Node[26]; // 26 letters in English alphabet
        // Boolean flag to indicate if the node represents the end of a word
        boolean isEndOfWord = false;
    }

    // Root node of the Trie
    private Node root;

    // Constructor to initialize the Trie
    public Trie() {
        root = new Node();
    }

    // Method to insert a word into the Trie
    public void insert(String word) {
        // Start at the root node
        Node current = root;
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // Find the index of the character in the children array
            int index = c - 'a'; // 'a' is the 0th index in the alphabet
            // If the child node does not exist, create it
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            // Move to the child node
            current = current.children[index];
        }
        // Mark the last node as the end of a word
        current.isEndOfWord = true;
    }

    // Method to search for a word in the Trie
    public boolean search(String word) {
        // Start at the root node
        Node current = root;
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // Find the index of the character in the children array
            int index = c - 'a'; // 'a' is the 0th index in the alphabet
            // If the child node does not exist, return false
            if (current.children[index] == null) {
                return false;
            }
            // Move to the child node
            current = current.children[index];
        }
        // Return true if the last node is marked as the end of a word
        return current.isEndOfWord;
    }

    // Method to delete a word from the Trie
    public void delete(String word) {
        // Start at the root node
        Node current = root;
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // Find the index of the character in the children array
            int index = c - 'a'; // 'a' is the 0th index in the alphabet
            // If the child node does not exist, return
            if (current.children[index] == null) {
                return;
            }
            // Move to the child node
            current = current.children[index];
        }
        // If the last node is not marked as the end of a word, return
        if (!current.isEndOfWord) {
            return;
        }
        // Mark the last node as not the end of a word
        current.isEndOfWord = false;
        // If the child node is empty, delete it
        if (