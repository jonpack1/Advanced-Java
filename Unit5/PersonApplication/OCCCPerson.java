/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with Carlos, Nassir, Trace, Kierra, Luke
 */


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

    public String getStudentID() {
        return studentID;
    }



    public boolean equals(OCCCPerson p) {
        return studentID.equalsIgnoreCase(p.studentID);
    }

    public boolean equals(RegisteredPerson p) {
        return getGovID().equalsIgnoreCase(p.getGovID());
    }

    public boolean equals(Person p) {
    return getFirstName().equalsIgnoreCase(p.getLastName()) && getLastName().equalsIgnoreCase(p.getLastName());
    }

    @Override
    public String toString() {
        return super.toString() + " [Student ID: " + studentID + "]";
    }
}
