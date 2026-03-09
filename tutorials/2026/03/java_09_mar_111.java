// Two Pointers Technique in Java
// Two pointers are a technique used to solve problems that require manipulating two sequences of elements.
public class TwoPointers {
    public static void main(String[] args) {
        // Example 1: Finding the Middle Element of a Linked List
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println("Middle Element: " + findMiddle(head));
    }

    // Function to find the middle element of a linked list
    public static int findMiddle(Node head) {
        // Initialize two pointers, slow and fast
        Node slow = head;
        Node fast = head;

        // Move fast pointer two steps at a time while slow pointer moves one step
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Return the middle element
        return slow.data;
    }

    // Node class to represent a node in the linked list
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Example 2: Validating Palindrome
    public static void main(String[] args) {
        String s = "madam";
        System.out.println(isPalindrome(s));
    }

    // Function to check if a string is a palindrome
    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        // Initialize two pointers, start and end
        while (i < j) {
            // If characters at start and end are not equal, return false
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        // If all characters are equal, return true
        return true;
    }

    // Example 3: Finding the First Duplicate in an Array
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 4, 5};
        System.out.println(firstDuplicate(nums));
    }

    // Function to find the first duplicate in an array
    public static int firstDuplicate(int[] nums) {
        int n = nums.length;
        // Initialize two pointers, start and end
        int i = 0;
        int j = n - 1;

        while (i < j) {
            // If elements at start and end are equal, return the start index
            if (nums[i] == nums[j]) {
                return i;
            }
            i++;
            j--;
        }

        // If no duplicate found, return -1
        return -1;
    }
}