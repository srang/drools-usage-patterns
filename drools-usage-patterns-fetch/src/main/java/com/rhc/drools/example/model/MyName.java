package com.rhc.drools.example.model;

public class MyName {

    private String firstName;
    private String lastName;

    public MyName(String firstName) {
        this(firstName,null);
    }

    public MyName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() { return this.lastName; }
    public void setLastName(String name) { this.lastName = name; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String name) { this.firstName = name; }

    @Override
    public String toString() {
        return "[firstName=" + ((firstName!=null)?firstName:"") + ", lastName=" + ((lastName!=null)?lastName:"") + "]";
    }

    @Override
    public boolean equals(Object Oname) {
        MyName name = (MyName) Oname;
        return name.getFirstName().equals(this.firstName) && name.getLastName().equals(this.lastName);
    }
}
