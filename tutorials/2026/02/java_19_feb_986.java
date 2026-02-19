// Trie Data Structure Implementation in Java
public class Trie {

    // Node class representing each node in the trie
    private static class Node {
        boolean isEndOfWord;
        HashMap<Character, Node> children;

        public Node() {
            this.isEndOfWord = false;
            this.children = new HashMap<>();
        }
    }

    // Root of the trie
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    /**
     * Insert a word into the trie.
     *
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // If the character is not in the children, add it
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node());
            }
            current = current.children.get(c);
        }
        // Mark the end of the word
        current.isEndOfWord = true;
    }

    /**
     * Search for a word in the trie.
     *
     * @param word The word to be searched.
     * @return True if the word is found, false otherwise.
     */
    public boolean search(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            // If the character is not in the children, return false
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        // Return true if the end of the word is marked
        return current.isEndOfWord;
    }

    /**
     * Check if a prefix exists in the trie.
     *
     * @param prefix The prefix to be checked.
     * @return True if the prefix exists, false otherwise.
     */
    public boolean startsWith(String prefix) {
        Node current = root;
        for (char c : prefix.toCharArray()) {
            // If the character is not in the children, return false
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        // Return true if we have reached this point
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("apple"));  // prints: true
        System.out.println(trie.search("app"));   // prints: true
        System.out.println(trie.search("banana")); // prints: true
        System.out.println(trie.search("banan")); // prints: false

        System.out.println(trie.startsWith("app"));  // prints: true
        System.out.println(trie.startsWith("ban"));   // prints: true
        System.out.println(trie.startsWith("ora"));   // prints: false
    }
}