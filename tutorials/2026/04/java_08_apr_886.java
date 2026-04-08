// Trie Data Structure Implementation in Java
public class Trie {

    // Node class representing each node in the Trie
    static class Node {
        boolean isEndOfWord;
        Node[] children;

        public Node() {
            this.isEndOfWord = false;
            this.children = new Node[26]; // Assuming only lowercase English letters
        }
    }

    private Node root;

    public Trie() {
        this.root = new Node();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // Find the corresponding child node
            int index = c - 'a'; // Assuming only lowercase English letters
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        // Mark the end of the word
        current.isEndOfWord = true;
    }

    /**
     * Searches for a word in the Trie.
     *
     * @param word The word to be searched.
     * @return True if the word is found, false otherwise.
     */
    public boolean search(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // Find the corresponding child node
            int index = c - 'a'; // Assuming only lowercase English letters
            if (current.children[index] == null) {
                return false; // Not found
            }
            current = current.children[index];
        }
        // Check if it's the end of a word
        return current.isEndOfWord;
    }

    /**
     * Checks if a prefix exists in the Trie.
     *
     * @param word The prefix to be checked.
     * @return True if the prefix exists, false otherwise.
     */
    public boolean startsWith(String prefix) {
        Node current = root;
        for (char c : prefix.toCharArray()) {
            // Find the corresponding child node
            int index = c - 'a'; // Assuming only lowercase English letters
            if (current.children[index] == null) {
                return false; // Not found
            }
            current = current.children[index];
        }
        // Check if it's a prefix
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("apple"));  // True
        System.out.println(trie.search("app"));     // True
        System.out.println(trie.search("ap"));      // False

        System.out.println(trie.startsWith("appl")); // True
        System.out.println(trie.startsWith("ban"));   // True
        System.out.println(trie.startsWith("ora"));   // False
    }
}