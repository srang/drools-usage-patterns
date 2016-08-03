package com.rhc.drools.example.persistence;

import java.math.BigDecimal;
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

    public Team(String name) {
        this.teamName = name;
        this.members = new ArrayList<Person>();
        this.problems = new ArrayList<Problem>();
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
        this.members.add(member);
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
}
