package Lec7_String;
import java.util.Scanner;
import java.util.Arrays;
public class Assignment8 {

    // Exercise 1
    public static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    // Exercise 2
    public static String capitalizeName(String name) {
        String[] parts = name.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part.substring(0, 1).toUpperCase());
            sb.append(part.substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    // Exercise 3
    public static String compressString(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= str.length(); i++) {
            if (i < str.length() && str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                sb.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        return sb.toString();
    }

    // Exercise 4
    public static boolean areAnagrams(String s1, String s2) {
        char[] arr1 = s1.toLowerCase().toCharArray();
        char[] arr2 = s2.toLowerCase().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    // Exercise 5
    public static void parseURL(String url) {
        int protocolEnd = url.indexOf("://");
        String protocol = url.substring(0, protocolEnd);
        int domainEnd = url.indexOf("/", protocolEnd + 3);
        String domain = url.substring(protocolEnd + 3, domainEnd);
        String path = url.substring(domainEnd);
        System.out.println("Protocol: " + protocol);
        System.out.println("Domain: " + domain);
        System.out.println("Path: " + path);
    }

    // Exercise 6
    public static String longestWord(String sentence) {
        String[] words = sentence.split(" ");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    // Exercise 7
    public static String createAcronym(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return sb.toString();
    }

    // Exercise 8
    public static int countWords(String str) {
        String[] words = str.trim().split("\\s+");
        return words.length;
    }

    // Exercise 9
    public static String formatNumber(String digits) {
        StringBuilder sb = new StringBuilder(digits);
        int count = 0;
        for (int i = sb.length() - 1; i > 0; i--) {
            count++;
            if (count % 3 == 0) {
                sb.insert(i, ",");
            }
        }
        return sb.toString();
    }

    // Exercise 10 
    public static String longestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        int maxLength = 0;
        int endIndex = 0; 

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0; 
                }
            }
        }

        if (maxLength == 0) return "";
        return s1.substring(endIndex - maxLength, endIndex);
    }

    public static void main(String[] args) {
        System.out.println("1. Reverse Words: " + reverseWords("Java is fun"));
        System.out.println("2. Capitalize Name: " + capitalizeName(" ngUYen vAn a "));
        System.out.println("3. Compress String: " + compressString("AAABBCDDDD"));
        System.out.println("4. Are Anagrams (listen, silent): " + areAnagrams("listen", "silent"));
        System.out.println("5. Parse URL:");
        parseURL("https://dtu.edu.vn/portals/0/home.aspx");
        System.out.println("6. Longest Word: " + longestWord("The quick brown fox jumps over the lazy dog"));
        System.out.println("7. Acronym: " + createAcronym("Object Oriented Programming"));
        System.out.println("8. The number of words in the string is: " + countWords(" Java is a powerful language "));
        System.out.println("9. Format Number: " + formatNumber("1234567890"));
        System.out.println("10. Longest Common Substring: " + longestCommonSubstring("ABCDEF","XBCYDEFZ"));
    }
}
