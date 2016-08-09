package com.rhc.drools.example;

import com.github.javafaker.Faker;
import com.rhc.drools.example.kie.KieBaseProvider;
import com.rhc.drools.example.persistence.Name;
import com.rhc.drools.example.persistence.Person;
import com.rhc.drools.example.persistence.Team;
import com.rhc.drools.example.vo.MyName;
import com.rhc.drools.example.vo.MyPerson;
import com.rhc.drools.example.vo.MyTeam;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Runner {
	public static void main(String[] args) {
		//Get the KieBase
		KieBaseProvider kbp = new KieBaseProvider("drools-usage-patterns-kjar-groups");
		KieBase kieBase = kbp.getKieBase();
		Faker faker = new Faker(new Random(123));
		MyTeam teamA = new MyTeam(faker.team().name());
        for (int i = 0; i<20; i++) {
            teamA.addMember(new MyPerson(new MyName(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
        }
        teamA.addMember(new MyPerson(new MyName("Sam","Rang"),34, faker.number().randomDouble(2,12,50)));

		MyTeam teamB = new MyTeam(faker.team().name());
		for (int i = 0; i<20; i++) {
			teamB.addMember(new MyPerson(new MyName(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
		}
		teamB.addMember(new MyPerson(new MyName("Sam","Rang"),34, faker.number().randomDouble(2,12,50)));
		teamB.addMember(new MyPerson(new MyName("Sam","Rang"),25, faker.number().randomDouble(2,12,50)));

		teamB.setBudget(teamB.getBudget().subtract(new BigDecimal(10.50)).setScale(2, BigDecimal.ROUND_CEILING));

		MyTeam teamC = new MyTeam(faker.team().name());
		for (int i = 0; i<20; i++) {
			teamC.addMember(new MyPerson(new MyName(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50), faker.number().randomDouble(2,12,50)));
		}

		Mapper<MyTeam, Team> myTeamMapper = new Mapper<MyTeam, Team>(MyTeam.class, Team.class);
		try {
			System.out.println("Pre-evaluation");
			System.out.println("  TEAM A");
			System.out.println(teamA.toString());
			System.out.println("  TEAM B");
			System.out.println(teamB.toString());
			System.out.println("  TEAM C");
			System.out.println(teamC.toString() + "\n");

			Map<String,String> fieldMap = new HashMap<String, String>();
            fieldMap.put("name","myName");
            myTeamMapper.setFieldMappings(fieldMap);

			System.out.println("MAPPER LOGIC\n");
			Team tA = myTeamMapper.doMap(teamA);
			Team tB = myTeamMapper.doMap(teamB);
			Team tC = myTeamMapper.doMap(teamC);
			System.out.println("\n");



			System.out.println("Mapped");
			System.out.println("  TEAM A");
			System.out.println(tA.toString());
			System.out.println("  TEAM B");
			System.out.println(tB.toString());
			System.out.println("  TEAM C");
			System.out.println(tC.toString() + "\n\n");

			System.out.println("RULE LOGIC\n");
			KieSession kieSession = kieBase.newKieSession();
			kieSession.insert(tA);
			kieSession.insert(tB);
			kieSession.insert(tC);

			kieSession.getAgenda().getAgendaGroup("properties").setFocus();
			kieSession.fireAllRules();
			kieSession.dispose();

			System.out.println("\n\nPost-evaluation");
			System.out.println("  TEAM A");
			System.out.println(tA.toString());
			System.out.println("  TEAM B");
			System.out.println(tB.toString());
			System.out.println("  TEAM C");
			System.out.println(tC.toString() + "\n");

			System.out.println("UNMAPPER LOGIC\n");
			teamA = myTeamMapper.unMap(tA);
			teamB = myTeamMapper.unMap(tB);
			teamC = myTeamMapper.unMap(tC);
			System.out.println("\n");

			System.out.println("UnMapped");
			System.out.println("  TEAM A");
			System.out.println(teamA.toString());
			System.out.println("  TEAM B");
			System.out.println(teamB.toString());
			System.out.println("  TEAM C");
			System.out.println(teamC.toString() + "\n");
			return;
		} catch (MappingException e) {
			e.printStackTrace();
		}

	}
}
