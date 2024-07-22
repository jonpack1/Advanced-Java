/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 21, 2024
 * PROJECT NAME: FibBigInteger.java
 * DESCRIPTION: FibBigInteger
 * worked with carlos,trace,luke,kierra,nassir,nurlan
 */

import java.math.BigInteger;
import java.util.Scanner;

public class FibBigInteger {
    public static BigInteger fib(int n) {
        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger result = BigInteger.valueOf(1);

        if (n >= 3) {
            for (int i = 3; i <= n; i++) {
                result = a.add(b);
                a = b;
                b = result;
            }
        }

        return result;
    }

    public static int digitCount(BigInteger num) {
        return num.toString().length();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number to calculate as Fibonacci: ");
        int input = scan.nextInt();

        BigInteger fibonacciNum = fib(input);
        int digitNum= digitCount(fibonacciNum);
        System.out.println("The Fibonacci of " + input + " is " + fibonacciNum);
        System.out.println("The number of digits in the Fibonacci number is: "+ digitNum);
    }
}