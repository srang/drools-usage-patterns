package com.rhc.drools.example.persistence;

import java.math.BigDecimal;
import java.math.MathContext;

public class Person {
	private Name name;
	private Integer age;
	private BigDecimal donation;

	public Person() {}
	public Person (Name name, Integer age, Double donation) {
		this.name = name;
		this.age = age;
		this.donation = new BigDecimal(donation.doubleValue()).setScale(2, BigDecimal.ROUND_CEILING);
	}
	public Name getName() { return this.name; }
	public void setName(Name name) { this.name = name; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }
	public BigDecimal getDonation() { return donation; }

	@Override
	public String toString() {
		return "{ name=" + name.toString() + ", age=" + age + ", donation=$" + donation + " }";
	}
}

