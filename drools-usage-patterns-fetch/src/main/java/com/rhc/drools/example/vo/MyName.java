package com.rhc.drools.example.vo;

public class MyName {

    private String firstName;
    private String lastName;

    public MyName() {}

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
        return "{ " + ((firstName!=null)?firstName:"") + " " + ((lastName!=null)?lastName:"") + " }";
    }

    @Override
    public boolean equals(Object Oname) {
        MyName myName = (MyName) Oname;
        return myName.getFirstName().equals(this.firstName) && myName.getLastName().equals(this.lastName);
    }
}
