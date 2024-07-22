import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with carlos, luke, trace, nassir, nurlan, duy, trevor, austin
 */
import java.time.LocalDate;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", getFirstName(), getLastName(), getDateOfBirth().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
    }

    @Override
    public int compareTo(Person o) {
        // Compare by last name
        int lastNameComparison = this.getLastName().compareToIgnoreCase(o.getLastName());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            // If last names are equal, compare by first name
            return this.getFirstName().compareToIgnoreCase(o.getFirstName());
        }
    }

    public void setFirstName(String newFirstName) {
        this.firstName = firstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = lastName;
    }
}
