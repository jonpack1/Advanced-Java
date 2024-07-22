/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with Carlos, Nassir, Trace, Kierra, Luke
 */

import java.util.Scanner;

public class PersonMain {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // Prompt the user for data for a Person
        System.out.println("Enter first name for Person:");
        String personFirstName = scanner.nextLine();

        System.out.println("Enter last name for Person:");
        String personLastName = scanner.nextLine();

        // Create and display the Person
        Person person = new Person(personFirstName, personLastName);
        System.out.println("Person details: " + person.toString());

        // Prompt the user for data for a RegisteredPerson
        System.out.println("\nEnter first name for RegisteredPerson:");
        String registeredPersonFirstName = scanner.nextLine();

        System.out.println("Enter last name for RegisteredPerson:");
        String registeredPersonLastName = scanner.nextLine();

        System.out.println("Enter government ID for RegisteredPerson:");
        String registeredPersonGovID = scanner.nextLine();

        // Create and display the RegisteredPerson
        RegisteredPerson registeredPerson = new RegisteredPerson(registeredPersonFirstName, registeredPersonLastName, registeredPersonGovID);
        System.out.println("RegisteredPerson details: " + registeredPerson.toString());

        // Prompt the user for data for an OCCCPerson
        System.out.println("\nEnter first name for OCCCPerson:");
        String occcPersonFirstName = scanner.nextLine();

        System.out.println("Enter last name for OCCCPerson:");
        String occcPersonLastName = scanner.nextLine();

        System.out.println("Enter government ID for OCCCPerson:");
        String occcPersonGovID = scanner.nextLine();

        System.out.println("Enter student ID for OCCCPerson:");
        String occcPersonStudentID = scanner.nextLine();

        // Create and display the OCCCPerson
        OCCCPerson occcPerson = new OCCCPerson(registeredPerson, occcPersonStudentID);
        System.out.println("OCCCPerson details: " + occcPerson.toString());

        // Prompt the user for a government ID for a new RegisteredPerson using the existing Person
        System.out.println("\nEnter government ID for a new RegisteredPerson using the existing Person:");
        String newRegisteredPersonGovID = scanner.nextLine();

        // Create a new RegisteredPerson using the existing Person and the provided government ID
        RegisteredPerson newRegisteredPerson = new RegisteredPerson(person, newRegisteredPersonGovID);

        // Display the new RegisteredPerson using toString
        System.out.println("New RegisteredPerson details: " + newRegisteredPerson.toString());

        // Prompt the user for a student ID for a new OCCCPerson using the newly-created RegisteredPerson
        System.out.println("\nEnter student ID for a new OCCCPerson using the newly-created RegisteredPerson:");
        String newOCCCPersonStudentID = scanner.nextLine();

        // Create a new OCCCPerson using the newly-created RegisteredPerson and the provided student ID
        OCCCPerson newOCCCPerson = new OCCCPerson(newRegisteredPerson, newOCCCPersonStudentID);

        // Display the new OCCCPerson using toString
        System.out.println("New OCCCPerson details: " + newOCCCPerson.toString());

        // Close the scanner
        scanner.close();


    }
}







