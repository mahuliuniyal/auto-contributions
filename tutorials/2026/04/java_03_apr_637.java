import java.util.Stack;

public class MonotonicStack {
    public static void main(String[] args) {
        // Initialize a stack
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(15);

        System.out.println("Initial stack: " + stack);
        System.out.println("Get next element: " + getNextElement(stack));
        System.out.println("Get next element from bottom: " + getBottomElement(stack));

        // Push elements to the stack
        stack.push(30);
        stack.push(25);

        System.out.println("After pushing 30 and 25:");
        System.out.println("Stack: " + stack);
        System.out.println("Get next element: " + getNextElement(stack));
        System.out.println("Get next element from bottom: " + getBottomElement(stack));

        // Pop elements from the stack
        stack.pop();
        stack.pop();

        System.out.println("After popping 2 elements:");
        System.out.println("Stack: " + stack);
        System.out.println("Get next element: " + getNextElement(stack));
        System.out.println("Get next element from bottom: " + getBottomElement(stack));

    }

    // Function to get the next element in a monotonic stack
    public static Integer getNextElement(Stack<Integer> stack) {
        if (stack.isEmpty()) return null;
        while (!stack.isEmpty() && stack.peek().equals(stack.firstElement())) {
            Stack<Integer> temp = new Stack<>();
            while (!stack.isEmpty() && stack.peek().equals(stack.firstElement())) {
                temp.push(stack.pop());
            }
            // If the top element is equal to the first element, pop all elements and add them back to the top
            stack = temp;
        }
        return stack.isEmpty() ? null : stack.peek();
    }

    // Function to get an element from the bottom of a monotonic stack
    public static Integer getBottomElement(Stack<Integer> stack) {
        if (stack.isEmpty()) return null;
        while (!stack.isEmpty() && stack.peek().equals(stack.lastElement())) {
            Stack<Integer> temp = new Stack<>();
            while (!stack.isEmpty() && stack.peek().equals(stack.lastElement())) {
                temp.push(stack.pop());
            }
            // If the top element is equal to the last element, pop all elements and add them back to the bottom
            stack = temp;
        }
        return stack.isEmpty() ? null : stack.last();
    }
}