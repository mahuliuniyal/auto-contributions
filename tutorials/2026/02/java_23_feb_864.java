// Trie.java

// Node class representing a node in the Trie
class Node {
    // Character stored in the node
    char character;
    // Child nodes
    Node[] children;
    // Is the node a word's end?
    boolean isEndOfWord;

    // Constructor to initialize the node
    public Node(char character) {
        this.character = character;
        children = new Node[26]; // Assuming English alphabet
        isEndOfWord = false;
    }
}

// Trie class implementing the Trie data structure
class Trie {
    // Root node
    Node root;

    // Constructor to initialize the Trie
    public Trie() {
        root = new Node('\0');
    }

    // Insert a word into the Trie
    public void insert(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // Find the child node corresponding to the character
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node(c);
            }
            current = current.children[index];
        }
        // Mark the end of the word
        current.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // Find the child node corresponding to the character
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        // Check if the word is a valid word in the Trie
        return current.isEndOfWord;
    }

    // Delete a word from the Trie
    public void delete(String word) {
        delete(root, word, 0);
    }

    // Helper method to delete a word from the Trie
    private void delete(Node current, String word, int index) {
        // Base case: word is empty or current node is null
        if (index >= word.length() || current == null) {
            return;
        }
        // Find the child node corresponding to the character
        int indexChar = word.charAt(index) - 'a';
        if (current.children[indexChar] == null) {
            return;
        }
        current.children[indexChar] = null;
        // If the word is deleted, mark the current node as end of word
        if (index < word.length() - 1) {
            delete(current.children[indexChar], word, index + 1);
        } else {
            current.isEndOfWord = false;
        }
    }

    // Print the Trie
    public void printTrie() {
        printTrie(root, 0);
    }

    // Helper method to print the Trie
    private void printTrie(Node current, int level) {
        if (current == null) {
            return;
        }
        printTrie(current.children[0], level + 1);
        System.out.print(current.character + " ");
        if (current.isEndOfWord) {
            for (int i = 0; i < level; i++) {
                System.out.print("   ");
            }
            System.out.println("END");
        }
    }
}

// Main class
public class Main {
    public static void main(String[]