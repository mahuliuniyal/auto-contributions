// StringManipulation.java
// This program teaches string manipulation techniques in Java.
// It demonstrates how to create and manipulate strings using various methods.

import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
        // Create a new Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Print out a greeting message with the user's name
        System.out.println("Hello, " + name + "!");

        // Demonstrate string concatenation using the '+' operator
        String greetingWithSpace = "Hello, ";
        String nameWithComma = ", Jane!";
        System.out.println(greetingWithSpace + nameWithComma);

        // Create a new string by repeating a character or substring
        String helloThreeTimes = "Hello";
        for (int i = 0; i < 3; i++) {
            System.out.println(helloThreeTimes);
        }

        // Use the toUpperCase() method to convert a string to uppercase
        String originalString = "hello";
        String upperCaseString = originalString.toUpperCase();
        System.out.println("Original: " + originalString);
        System.out.println("Upper case: " + upperCaseString);

        // Use the toLowerCase() method to convert a string to lowercase
        originalString = "HELLO";
        upperCaseString = originalString.toLowerCase();
        System.out.println("Lower case: " + lowerCaseString);

        // Use substring() to extract a part of a string
        String fullString = "Hello, World!";
        int start = 7; // index of the first character after the comma
        int end = 12; // index of the last character before the exclamation mark
        System.out.println("Extracted substring: " + fullString.substring(start, end));

        // Use replace() to replace a character or substring with another one
        fullString = "Hello, World!";
        char oldChar = 'o';
        char newChar = 'x';
        String modifiedString = fullString.replace(oldChar, newChar);
        System.out.println("Modified string: " + modifiedString);

        // Use trim() to remove whitespace from the beginning and end of a string
        String originalTrimmedString = "   Hello   ";
        String trimmedString = originalTrimmedString.trim();
        System.out.println("Trimmed string: " + trimmedString);

        scanner.close(); // close the Scanner object
    }

    private static String lowerCaseString; // declare and initialize a private variable
}