package com.rhc.drools.example.model;

public class Person {
	private String name;
	private String lastName = "";
	private Integer age;
	public String getLastName() { return this.lastName; }
	public void setLastName(String name) { this.lastName = name; }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + " " + lastName + ", age=" + age + "]";
	}
}