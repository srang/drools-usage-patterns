package com.rhc.drools.example.model;

public class MyPerson {
	private MyName name;
	private Integer age;
	public MyPerson(MyName name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public MyName getName() { return this.name; }
	public void setName(MyName name) { this.name = name; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }

	@Override
	public String toString() {
		return "[name=" + name.toString() + ", age=" + age + "]";
	}
}

