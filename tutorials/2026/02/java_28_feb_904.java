// String Hashing in Java
// This program demonstrates how to calculate the hash value of a given string using Java's built-in methods.

import java.util.HashSet;
import java.util.Set;

public class StringHashing {

    // Step 1: Define a method to calculate the hash value of a string
    public static int calculateHash(String str) {
        // Initialize the hash value to 0
        int hashValue = 0;

        // Iterate over each character in the string
        for (char c : str.toCharArray()) {
            // Calculate the ASCII value of the character
            int asciiValue = c;

            // Multiply the current hash value by 31 (a prime number) and add the ASCII value of the character
            hashValue = hashValue * 31 + asciiValue;
        }

        // Return the calculated hash value
        return hashValue;
    }

    // Step 2: Define a method to check if two strings have the same hash value
    public static boolean checkHashEquality(String str1, String str2) {
        // Calculate the hash values of both strings
        int hashValue1 = calculateHash(str1);
        int hashValue2 = calculateHash(str2);

        // Return true if the hash values are equal, false otherwise
        return hashValue1 == hashValue2;
    }

    // Step 3: Define a method to check if a string is a substring of another string
    public static boolean checkSubstring(String str1, String str2) {
        // Iterate over each character in the first string
        for (int i = 0; i < str1.length(); i++) {
            // Check if the substring from the current index to the end of the first string is a substring of the second string
            if (str2.contains(str1.substring(i))) {
                // If a match is found, return true
                return true;
            }
        }

        // If no match is found, return false
        return false;
    }

    // Step 4: Define a method to calculate the hash value of a set of strings
    public static int calculateSetHash(Set<String> strSet) {
        // Initialize the hash value to 0
        int hashValue = 0;

        // Iterate over each string in the set
        for (String str : strSet) {
            // Calculate the hash value of the string and add it to the total hash value
            hashValue += calculateHash(str);
        }

        // Return the calculated hash value
        return hashValue;
    }

    public static void main(String[] args) {
        // Test the methods with some example strings
        System.out.println("Hash Value of 'Hello World': " + calculateHash("Hello World"));
        System.out.println("Hash Value of 'Java': " + calculateHash("Java"));
        System.out.println("Check Hash Equality: " + checkHashEquality("Hello World", "Hello Universe"));
        System.out.println("Check Substring: " + checkSubstring("Hello", "Hello World"));
        System.out.println("Hash Value of a Set: " + calculateSetHash(new HashSet<String>() {{
            add("Hello");
            add("World");
            add("Java");
        }}));
    }
}