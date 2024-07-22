/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 04 27, 2024
 * PROJECT NAME: WordBag.java
 * DESCRIPTION: wordbag
 * worked with carlos, luke, trace, nassir, nurlan, duy, trevor, austin
 */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class WordBag {

    public static void main(String[] args) {
        String inputFile = getInputFileName(args);
        String outputFile = getOutputFileName(args, inputFile);

        Map<String, Integer> wordCounts = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        List<Person> persons = new ArrayList<>();
        List<Person> sortedPersons;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                String objType = words[0];
                switch (objType){
                    case "Person":
                        LocalDate personDateOfBirth = LocalDate.parse(words[3], dateFormatter);
                        persons.add(new Person(words[1], words[2], personDateOfBirth));
                        break;
                    case "RegisteredPerson":
                        LocalDate registeredPersonDateOfBirth = LocalDate.parse(words[3], dateFormatter);
                        persons.add(new RegisteredPerson(words[1], words[2], registeredPersonDateOfBirth, words[4]));
                        break;
                    case "OCCCPerson":
                        LocalDate occcPersonDateOfBirth = LocalDate.parse(words[3], dateFormatter);
                        persons.add(new OCCCPerson(words[1], words[2], occcPersonDateOfBirth, words[4], words[5] ));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }

        // Sort the list of persons
        sortedPersons = new ArrayList<>(persons);
        Collections.sort(sortedPersons);

        // Display unsorted persons
        System.out.println("Unsorted Persons:");
        for (Person person : persons) {
            System.out.println(person);
        }

        // Display sorted persons
        System.out.println("\nSorted Persons:");
        for (Person person : sortedPersons) {
            System.out.println(person);
        }

        // Output to file
        outputToFile(outputFile, sortedPersons);
    }

    private static void outputToFile(String outputFile, List<Person> sortedPersons) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            for (Person person : sortedPersons) {
                writer.println(person);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputFile);
        }
    }

    private static String getInputFileName(String[] args) {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter input filename: ");
            return scanner.nextLine();
        }
        return args[0];
    }

    private static String getOutputFileName(String[] args, String inputFile) {
        if (args.length == 2) {
            return args[1];
        } else if (args.length == 1) {
            return inputFile.replaceFirst("[.][^.]+$", "") + ".out";
        }
        return "output.out"; // Default output filename
    }
}



