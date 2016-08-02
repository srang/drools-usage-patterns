package com.rhc.drools.example;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import com.rhc.drools.example.kie.EmbeddedKieBaseProvider;
import com.rhc.drools.example.persistence.Person;

public class Runner {
	public static void main(String[] args) {
		//Get the KieBase
		EmbeddedKieBaseProvider kbp = new EmbeddedKieBaseProvider();
		KieBase kieBase = kbp.getKieBase();
		
		//Create a Fact
		Person person = new Person();
		person.setName("Sal");
		
		//Determine age with Drools
		Person sal = determinePersonsAge(person,kieBase);
		
		//Was our age set correctly?
		System.out.println(sal);
	}
	
	private static Person determinePersonsAge(Person person, KieBase kieBase) {
		//KieSession is an interface to the drools engine
		KieSession kieSession = kieBase.newKieSession();
		kieSession.insert(person);
		kieSession.fireAllRules();
		kieSession.dispose();
		return person;
	}

}
