/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 23, 2024
 * PROJECT NAME: PalindromesFinal.java
 * DESCRIPTION: PalindromesFinal
 * worked with carlos, trace, kierra, nassir, nurlan
 */
import java.util.Scanner;

public class PalindromesFinal {

    public static void main(String[] args) {
        int strictPalindromeCount = 0;
        int ordinaryPalindromeCount = 0;
        int nonPalindromeCount = 0;

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter a string (or press Enter to exit): ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }

            String strictString = "";
            boolean hasSpace = false;

            String ordinaryString = "";

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (Character.isLetter(c) || Character.isDigit(c)) {
                    strictString += c;
                    ordinaryString += c;
                } else if (Character.isSpaceChar(c)) {
                    hasSpace = true;
                    strictString += c;
                }
            }

            if (hasSpace && isPalindrome(strictString)) {
                strictPalindromeCount++;
            } else if (!ordinaryString.isEmpty() && isPalindrome(ordinaryString)) {
                ordinaryPalindromeCount++;
            } else {
                nonPalindromeCount++;
            }
        }

        System.out.println("\nSummary of results:");
        System.out.println("Strict Palindromes: " + strictPalindromeCount);
        System.out.println("Ordinary Palindromes: " + ordinaryPalindromeCount);
        System.out.println("Non-Palindromes: " + nonPalindromeCount);

        scanner.close();
    }

    public static boolean isPalindrome(String str) {
        // Checks if the string is a palindrome by comparing it with its reversed version recursively
        str = str.toLowerCase();
        return checkPalindromeRecursively(str, 0, str.length() - 1);
    }

    private static boolean checkPalindromeRecursively(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return checkPalindromeRecursively(str, start + 1, end - 1);
    }
}
