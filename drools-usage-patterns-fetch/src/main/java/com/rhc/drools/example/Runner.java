package com.rhc.drools.example;

import com.rhc.drools.example.kie.KieBaseProvider;
import com.rhc.drools.example.model.MyName;
import com.rhc.drools.example.model.MyPerson;
import com.rhc.drools.example.model.PersonMapper;
import com.rhc.drools.example.persistence.Name;
import com.rhc.drools.example.persistence.Person;
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
		MyPerson person1 = new MyPerson(new MyName("Sal"), null);
		MyName name1 = person1.getName();

		MyPerson person2 = new MyPerson(new MyName("John"),null);
		MyName name2 = person2.getName();
		while (true) {
			//Create a Fact

			Person p1 = PersonMapper.peopleMap(person1);
			Person p2 = PersonMapper.peopleMap(person2);
			Name n1 = p1.getName();
			Name n2 = p2.getName();
			System.out.println("Pre-evaluation");
			System.out.println(p1);
			System.out.println(p2);

			fireRules(kieBaseNames, Name.class, n1, n2);
			p1.setName(n1);
			p2.setName(n2);

			System.out.println("Post-Name-evaluation");
			System.out.println(p1);
			System.out.println(p2);
			//Determine age with Drools
			fireRules(kieBaseAges, Person.class, p1, p2);
			//fireRules(kieBaseAges, Person.class, person2);

			System.out.println("Final");
			person1 = PersonMapper.unMap(p1);
			person2 = PersonMapper.unMap(p2);
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
	
	private static void fireRules(KieBase kieBase, Class clazz, Object... facts) {
		//KieSession is an interface to the drools engine
		KieSession kieSession = kieBase.newKieSession();
		for(Object fact : facts) {
			kieSession.insert(clazz.cast(fact));
		}
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
