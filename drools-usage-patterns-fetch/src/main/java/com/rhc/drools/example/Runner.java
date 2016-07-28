package com.rhc.drools.example;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import com.rhc.drools.example.kie.KieBaseProvider;
import com.rhc.drools.example.model.Person;

public class Runner {
	public static void main(String[] args) {
		//Get the KieBase
		KieBaseProvider kbp = new KieBaseProvider();
		KieBase kieBase = kbp.getKieBase();

		Person person2 = new Person();
		person2.setName("Sam");

		//Continually evaluate ruleset to see changes
		while (true) {
			//Create a Fact
			Person person1 = new Person();
			person1.setName("Sal");

			//Determine age with Drools
			Person sal = determinePersonsAge(person1,kieBase);
			Person sam = determinePersonsAge(person2,kieBase);

			//Was our age set correctly?
			System.out.println(sal);
			System.out.println(sam);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//That's ok
			}
		}
		
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
