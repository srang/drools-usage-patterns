package com.rhc.drools.example.persistence;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by srang on 8/3/16.
 */
public class Team {
    private String teamName;
    private List<Person> members;
    private List<Problem> problems;
    private BigDecimal budget;
    private TeamEvaluation evaluation;

    public Team(String name) {
        this.budget = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_CEILING);
        this.teamName = name;
        this.members = new ArrayList<Person>();
        this.problems = new ArrayList<Problem>();
        this.evaluation = new TeamEvaluation("Unevaluated Submitted");
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void addMember(Person member) {
        this.budget = budget.add(member.getDonation()).setScale(2, BigDecimal.ROUND_CEILING);
        this.members.add(member);
    }

    public TeamEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = new TeamEvaluation(evaluation);
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team: {\n");
        sb.append("  status: ").append(evaluation).append(",\n");
        sb.append("  name: ").append(teamName).append(",\n");
        sb.append("  budget: ").append(budget).append(",\n");
        sb.append("  members: {\n");
        for(Person p : members) {
            sb.append("    ").append(p.toString()).append(",\n");
        }
        sb.append("  }\n");
        sb.append("  problems: {\n");
        for(Problem p : problems) {
            sb.append("    ").append(p.toString()).append(",\n");
        }
        sb.append("  }\n");
        sb.append("}");
        return sb.toString();
    }
}
