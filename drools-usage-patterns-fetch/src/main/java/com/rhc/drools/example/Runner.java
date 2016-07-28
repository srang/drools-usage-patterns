package com.rhc.drools.example;

import com.rhc.drools.example.kie.KieBaseProvider;
import com.rhc.drools.example.model.Name;
import com.rhc.drools.example.model.Person;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

public class Runner {
	public static void main(String[] args) {
		//Get the KieBase
		KieBaseProvider kbp = new KieBaseProvider("drools-usage-patterns-kjar-ages");
		KieBase kieBaseAges = kbp.getKieBase();

		KieBaseProvider kbp1 = new KieBaseProvider("drools-usage-patterns-kjar-names");
		KieBase kieBaseNames = kbp1.getKieBase();

		//Continually evaluate ruleset to see changes
		Person person1 = new Person();
		Name name1 = new Name("Sal");
		person1.setName(name1);

		Person person2 = new Person();
		Name name2 = new Name("John");
		person2.setName(name2);
		while (true) {
			//Create a Fact

			System.out.println("Pre-evaluation");
			System.out.println(person1);
			System.out.println(person2);

			fireRules(kieBaseNames, Name.class, name1);
			fireRules(kieBaseNames, Name.class, name2);
			person1.setName(name1);
			person2.setName(name2);

			System.out.println("Post-Name-evaluation");
			System.out.println(person1);
			System.out.println(person2);
			//Determine age with Drools
			fireRules(kieBaseAges, Person.class, person1);
			fireRules(kieBaseAges, Person.class, person2);

			System.out.println("Final");
			//Was our age set correctly?
			System.out.println(person1);
			System.out.println(person2);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//That's ok
			}
		}
		
	}
	
	private static void fireRules(KieBase kieBase, Class clazz, Object fact) {
		//KieSession is an interface to the drools engine
		KieSession kieSession = kieBase.newKieSession();
		//for(Object fact : facts) {
			kieSession.insert(clazz.cast(fact));
		//}
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
