/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 04 16, 2024
 * PROJECT NAME: CigaretteSmokersProblem.java
 * DESCRIPTION: CigaretteSmokersProblem
 * worked with Carlos, Nassir, Trace, Luke, Kierra
 */
import java.util.HashSet;

import java.util.Queue;

import java.util.Random;

import java.util.Set;

import java.util.Scanner;

import java.util.concurrent.Semaphore;

import java.util.concurrent.LinkedBlockingQueue;



public class CigaretteSmokersProblem {

    private static Semaphore tableSemaphore = new Semaphore(1);

    private static Semaphore agentSemaphore = new Semaphore(0);



    private static Random random = new Random();

    private static volatile int smokersFinished = 0;

    private static Queue<String> waitingSmokers = new LinkedBlockingQueue<>();



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of smokers: ");

        int numSmokers = scanner.nextInt();



        Thread agentThread = new Thread(CigaretteSmokersProblem::agent);

        agentThread.start();



        for (int i = 1; i <= numSmokers; i++) {

            final int smokerIndex = i;

            Thread smokerThread = new Thread(() -> smoker("smoker " + smokerIndex));

            smokerThread.start();

            System.out.println("smoker " + smokerIndex + " start:");

        }

    }



    private static void agent() {

        try {

            while (true) {

                System.out.println("Agent is trying to acquire the table...");

                tableSemaphore.acquire();

                System.out.println("Agent acquired the table.");

                String item = getItem(); // Random item placed on the table

                System.out.println("Agent placed " + item + " on the table");

                tableSemaphore.release(); // Release table semaphore first

                agentSemaphore.release(); // Signal a smoker

                System.out.println("Agent go to sleep");

                Thread.sleep(5000); // Simulate time for agent to sleep after putting item on table

                System.out.println("Agent awake");

                System.out.println("Agent running...");

                Thread.sleep(3000);

            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }

    }



    private static void smoker(String smokerId) {

        try {

            Set<String> items = new HashSet<>();

            while (true) {

                Thread.sleep(2000 + random.nextInt(3000));

                System.out.println(smokerId + " is trying to acquire the table...");

                waitingSmokers.offer(smokerId); // Add smoker to the waiting queue



                while (!waitingSmokers.peek().equals(smokerId)) {

                    Thread.sleep(1000); // Wait until it's the smoker's turn

                }



                if (!tableSemaphore.tryAcquire()) {

                    waitingSmokers.remove(smokerId); // Remove smoker from the waiting queue

                    continue; // Try again later

                }



                waitingSmokers.poll(); // Remove smoker from the waiting queue

                System.out.println(smokerId + " acquired the table.");

                Thread.sleep(1000);

                items.add(getItem());

                items.add(getItem());



                if (items.contains("paper") && items.contains("tobacco") && items.contains("matches")) {

                    System.out.println(smokerId + " acquired the table and is smoking");

                    Thread.sleep(2000); // Simulate smoking time

                    System.out.println(smokerId + " finished smoking");

                    items.clear();

                    tableSemaphore.release(); // Clear items from the table

                    synchronized (CigaretteSmokersProblem.class) {

                        if (++smokersFinished == waitingSmokers.size()) {

                            System.out.println("All smokers have finished smoking. Simulation complete.");

                        }

                    }

                } else {

                    String lackItems = "";

                    if (!items.contains("paper")) {

                        lackItems += "paper, ";

                    }

                    if (!items.contains("tobacco")) {

                        lackItems += "tobacco, ";

                    }

                    if (!items.contains("matches")) {

                        lackItems += "matches, ";

                    }

                    System.out.println(smokerId + " didn't find the following items: " + lackItems.substring(0, lackItems.length() - 2));

                    System.out.println(smokerId + " leave table");

                    Thread.sleep(1000);

                    tableSemaphore.release();

                }

            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }

    }



    private static String getItem() {

        String[] items = {"paper", "tobacco", "matches"};

        return items[random.nextInt(items.length)];

    }

}