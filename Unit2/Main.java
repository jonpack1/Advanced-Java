/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 03, 2024
 * PROJECT NAME: Main.java
 * DESCRIPTION: sieve
 * worked with trace,luke,carlos, nassir, nurlan, kierra
 */
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
            System.out.println("Welcome ");

            int start = 0;
            int stop = 0;

            // Check if start and stop args are in cmd line
            if (args.length == 2) {
                start = Integer.parseInt(args[0]);
                stop = Integer.parseInt(args[1]);
            } else {
                Scanner scanner = new Scanner(System.in);

                // ask the user for start and stop v
                System.out.print("Enter the start value: ");
                start = scanner.nextInt();

                System.out.print("Enter the stop value: ");
                stop = scanner.nextInt();
            }

            // Create boolean array
            boolean[] primes = new boolean[stop + 1];

            // Set array values to true
            for (int i = 2; i <= stop; i++) {
                primes[i] = true;
            }

            // Sieve of Eratosthenes algorithm
            for (int p = 2; p * p <= stop; p++) {
                if (primes[p]) {
                    for (int i = p * p; i <= stop; i += p) {
                        primes[i] = false;
                    }
                }
            }

            // Count and display the total number of primes
            int count = 0;
            for (int i = start; i <= stop; i++) {
                if (primes[i]) {
                    count++;
                }
            }
            System.out.println("Total number of primes found between " + start + " and " + stop + ": " + count);
        }
    }


