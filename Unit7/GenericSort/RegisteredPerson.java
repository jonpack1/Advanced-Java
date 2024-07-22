import java.time.LocalDate;

import java.time.LocalDate;

public class RegisteredPerson extends Person {

    private String govID;

    public RegisteredPerson(String firstName, String lastName, LocalDate dateOfBirth, String govID) {
        super(firstName, lastName, dateOfBirth);
        this.govID = String.valueOf(govID);
    }

    public RegisteredPerson(Person p, LocalDate dateOfBirth, String govID) {
        super(p.getFirstName(), p.getLastName(), dateOfBirth);
        this.govID = govID;
    }

    public RegisteredPerson(RegisteredPerson rp) {
        super(rp.getFirstName(), rp.getLastName(), rp.getDateOfBirth());
        this.govID = rp.govID;
    }

    public String getGovID() {
        return new String(govID);
    }

    public boolean equals(RegisteredPerson p) {
        return govID.equalsIgnoreCase(p.govID);
    }

    public boolean equals(Person p) {
        return getFirstName().equalsIgnoreCase(p.getFirstName()) && getLastName().equalsIgnoreCase(p.getLastName());
    }

    @Override
    public String toString() {
        return super.toString() + " [" + govID + "]";
    }

    public int compareTo(RegisteredPerson o) {
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

