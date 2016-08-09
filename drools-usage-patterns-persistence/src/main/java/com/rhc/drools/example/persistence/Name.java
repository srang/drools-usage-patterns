package com.rhc.drools.example.persistence;

public class Name {

    private String firstName;
    private String lastName;

    public Name() {}
    public Name(String firstName) {
        this(firstName,null);
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() { return this.lastName; }
    public void setLastName(String name) { this.lastName = name; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String name) { this.firstName = name; }

    @Override
    public String toString() {
        return "{ " + ((firstName!=null)?firstName:"") + " " + ((lastName!=null)?lastName:"") + " }";
    }

    @Override
    public boolean equals(Object Oname) {
        Name name = (Name) Oname;
        return name.getFirstName().equals(this.firstName) && name.getLastName().equals(this.lastName);
    }
}
