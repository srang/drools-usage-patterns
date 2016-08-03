package com.rhc.drools.example.persistence;

import java.math.BigDecimal;

public class Person {
	private Name name;
	private Integer age;
	private BigDecimal donation;
	public Person() {}
	public Person (Name name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public Name getName() { return this.name; }
	public void setName(Name name) { this.name = name; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }
	public BigDecimal getDonation() { return donation; }
	public void setDonation(BigDecimal donation) { this.donation = donation; }

	@Override
	public String toString() {
		return "[name=" + name.toString() + ", age=" + age + "]";
	}
}

