/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: Person.java
 * DESCRIPTION: Person
 */
public class Person {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName){
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
    }

    public Person(Person p){
        this.firstName = p.firstName;
        this.lastName = p.lastName;
    }

    public String getFirstName(){
        return new String (firstName);
    }

    public String getLastName(){
        return new String (lastName);
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    public boolean equals(Person p){
        return firstName.equalsIgnoreCase(p.firstName) &&
                lastName.equalsIgnoreCase(p.lastName);
    }

    public void eat(){
        System.out.println("person " + toString() + " is eating");
    }

    public void sleep(){
        System.out.println("person " + toString() + " is sleeping");

    }

    public void play(){
        System.out.println("person " + toString() + " is playing");

    }

    public void run(){
        System.out.println("person " + toString() + " is running");

    }


}