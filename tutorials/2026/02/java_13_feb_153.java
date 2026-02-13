public class SlidingWindow {
    public static void main(String[] args) {
        String str = "abcde";
        int k = 2;
        System.out.println("Substrings of length 3 in the string 'abcde' are: ");
        for (int i = 0; i <= str.length() - k; i++) {
            String substr = str.substring(i, i + k);
            System.out.println(substr);
        }
    }

    public static void slidingWindow(String str, int k) {
        // Calculate the length of the string
        int n = str.length();
        
        // Iterate over the possible start indices of the window
        for (int i = 0; i <= n - k; i++) {
            String substr = str.substring(i, i + k);
            
            // Print the substring at each step
            System.out.println("Substrings of length " + k + " in the string '" + str + "' are: ");
            System.out.println(substr);

            // Remove elements from front if window size is 2
            for (int j = 0; j < substr.length() - 1; j++) {
                String temp = substr.substring(1) + substr.charAt(j);
                System.out.println(temp);
            }

            // Remove elements from end 
            for (int j = 1; j <= substr.length(); j++) {
                String temp = substr.substring(0, j-1) + substr.charAt(substr.length()-1);
                System.out.println(temp);

            }
        }
    }

}