/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with Carlos, Nassir, Trace, Kierra, Luke
 */

import java.util.Scanner;

public class TestOCCCDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter test dates in US format (Month, Day, Year):");

        int validDates = 0;
        int exceptions = 0;

        while (true) {
            try {
                System.out.println("Enter date (or 'quit' to exit):");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                String[] parts = input.split("[\\s,/]+");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid input format. Please enter Month Day Year separated by spaces, commas, or slashes.");
                }

                int month;
                try {
                    month = Integer.parseInt(parts[0]);
                    if (month < 1 || month > 12) {
                        throw new IllegalArgumentException();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid month. Please enter a numeric value for the month.");
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid month. Please enter a month between 1 and 12.");
                }

                int day;
                try {
                    day = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid day. Please enter a numeric value for the day.");
                }

                int year;
                try {
                    year = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid year. Please enter a numeric value for the year.");
                }

                // Perform additional validation
                if (day < 1 || day > 31) {
                    throw new IllegalArgumentException("Invalid day. Please enter a day between 1 and 31.");
                }

                OCCCDate date = new OCCCDate(day, month, year);
                System.out.println("Date: " + date.toString(true, false, true));

                validDates++;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                exceptions++;
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred.");
                exceptions++;
            }
        }

        System.out.println("Total valid dates: " + validDates);
        System.out.println("Total exceptions occurred: " + exceptions);

        scanner.close();
    }
}














//
//        OCCCDate today = new OCCCDate(05, 03, 2024);
//
//        System.out.println(today.toString(OCCCDate.FORMAT_US,OCCCDate.STYLE_NUMBERS,OCCCDate.SHOW_DAY_NAME));
//
//
//
//        OCCCDate February = new OCCCDate(29, 02, 2022);
//
//        System.out.println(February.toString(OCCCDate.FORMAT_EURO,OCCCDate.STYLE_NUMBERS,OCCCDate.SHOW_DAY_NAME));
//
//
//
//        OCCCDate January = new OCCCDate(365, 01, 2022);
//
//        System.out.println(January.toString(OCCCDate.FORMAT_US,OCCCDate.STYLE_NAMES,OCCCDate.SHOW_DAY_NAME));
//
//
//
//        OCCCDate Kirk = new OCCCDate(22, 03, 2233);
//
//        System.out.println(Kirk.toString(OCCCDate.FORMAT_EURO,OCCCDate.STYLE_NAMES,OCCCDate.SHOW_DAY_NAME));









