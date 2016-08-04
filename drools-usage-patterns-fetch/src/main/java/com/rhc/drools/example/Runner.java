package com.rhc.drools.example;

import com.github.javafaker.Faker;
import com.rhc.drools.example.kie.KieBaseProvider;
import com.rhc.drools.example.persistence.Name;
import com.rhc.drools.example.persistence.Person;
import com.rhc.drools.example.persistence.Team;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;
import java.util.Random;

public class Runner {
	public static void main(String[] args) {
		//Get the KieBase
		KieBaseProvider kbp = new KieBaseProvider("drools-usage-patterns-kjar-groups");
		KieBase kieBase = kbp.getKieBase();
		Faker faker = new Faker(new Random(123));
		Team teamA = new Team(faker.team().name());
        for (int i = 0; i<20; i++) {
            teamA.addMember(new Person(new Name(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
        }
        teamA.addMember(new Person(new Name("Sam","Rang"),34, faker.number().randomDouble(2,12,50)));

		Team teamB = new Team(faker.team().name());
		for (int i = 0; i<20; i++) {
			teamB.addMember(new Person(new Name(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
		}
		teamB.addMember(new Person(new Name("Sam","Rang"),34, faker.number().randomDouble(2,12,50)));
		teamB.addMember(new Person(new Name("Sam","Rang"),25, faker.number().randomDouble(2,12,50)));

		teamB.setBudget(teamB.getBudget().subtract(new BigDecimal(10.50)).setScale(2, BigDecimal.ROUND_CEILING));

		Team teamC = new Team(faker.team().name());
		for (int i = 0; i<20; i++) {
			teamC.addMember(new Person(new Name(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
		}

		System.out.println("Pre-evaluation");
		System.out.println("  TEAM A");
		System.out.println(teamA.toString());
		System.out.println("  TEAM B");
		System.out.println(teamB.toString());
		System.out.println("  TEAM C");
		System.out.println(teamC.toString()+"\n\n");

		KieSession kieSession = kieBase.newKieSession();
		kieSession.insert(teamA);
		kieSession.insert(teamB);
		kieSession.insert(teamC);

		kieSession.getAgenda().getAgendaGroup("properties").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();

        System.out.println("\n\nPost-evaluation");
		System.out.println("  TEAM A");
        System.out.println(teamA.toString());
		System.out.println("  TEAM B");
		System.out.println(teamB.toString());
		System.out.println("  TEAM C");
		System.out.println(teamC.toString());
        return;

	}
}
