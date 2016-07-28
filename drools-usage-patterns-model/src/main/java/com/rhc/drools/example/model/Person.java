package com.rhc.drools.example.model;

public class Person {
	private Name name;
	private Integer age;
	public Name getName() { return this.name; }
	public void setName(Name name) { this.name = name; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }

	@Override
	public String toString() {
		return "[name=" + name.toString() + ", age=" + age + "]";
	}
}

