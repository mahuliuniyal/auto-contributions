public class KnuthMorrisPratt {

    // Function to compute longest prefix suffix array for the pattern
    public static int[] computeLPSArray(char[] pattern) {
        int m = pattern.length;
        int[] lps = new int[m];

        // Initialize the length of the longest proper prefix which is also a suffix
        lps[0] = 0;

        // Compute lps[] for pattern[0..m-1]
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = lps[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            lps[i] = j;
        }
        return lps;
    }

    // Function to perform KMP search
    public static int[] kmpSearch(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int[] lps = computeLPSArray(pattern.toCharArray());
        int j = 0;
        int[] res = new int[n];

        // Fill the result array res[] for pattern[0..m-1]
        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern[j]) {
                j = lps[j - 1];
            }
            if (text.charAt(i) == pattern[j]) {
                j++;
            }
            if (j == m) {
                // Pattern found at index i-m+1
                res[i - m] = i;
                j = lps[j - 1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABAB";

        int[] result = kmpSearch(text, pattern);
        System.out.println("Indices of pattern in text are:");
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                System.out.println("Index " + i + ": " + result[i]);
            }
        }
    }
}