package com.rhc.drools.example.vo;

import java.math.BigDecimal;

public class MyPerson {
	private MyName myName;
	private Integer age;
	private BigDecimal donation;

	public MyPerson() {}
	public MyPerson(MyName myName, Integer age, Double donation) {
		this.myName = myName;
		this.age = age;
		this.donation = new BigDecimal(donation.doubleValue()).setScale(2, BigDecimal.ROUND_CEILING);
	}
	public MyName getName() { return this.myName; }
	public void setName(MyName myName) { this.myName = myName; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }
	public BigDecimal getDonation() { return donation; }

	@Override
	public String toString() {
		return "{ myName=" + myName.toString() + ", age=" + age + ", donation=$" + donation + " }";
	}
}

