import java.time.LocalDate;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with carlos, luke, trace, nassir, nurlan, duy, trevor, austin
 */


import java.time.LocalDate;

public class OCCCPerson extends RegisteredPerson {
    private String studentID;

    public OCCCPerson(RegisteredPerson rp, String studentID) {
        super(rp);
        this.studentID = studentID;
    }

    public OCCCPerson(OCCCPerson p) {
        super(p);
        this.studentID = p.studentID;
    }

    public OCCCPerson(String firstName, String lastName, LocalDate dateOfBirth, String govID, String studentID) {
        super(firstName, lastName, dateOfBirth, govID);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OCCCPerson)) return false;
        OCCCPerson p = (OCCCPerson) obj;
        return super.equals(p) && studentID.equalsIgnoreCase(p.studentID);
    }

    @Override
    public String toString() {
        return super.toString() + " [Student ID: " + studentID + "]";
    }

    public int compareTo(OCCCPerson o) {
        // Compare by last name
        int lastNameComparison = this.getLastName().compareToIgnoreCase(o.getLastName());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            // If last names are equal, compare by first name
            int firstNameComparison = this.getFirstName().compareToIgnoreCase(o.getFirstName());
            if (firstNameComparison != 0) {
                return firstNameComparison;
            } else {
                // If first names are equal, compare by date of birth
                return this.getDateOfBirth().compareTo(o.getDateOfBirth());
            }
        }
    }
}