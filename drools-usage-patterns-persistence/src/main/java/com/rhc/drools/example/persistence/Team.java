package com.rhc.drools.example.persistence;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by srang on 8/3/16.
 */
public class Teams {
    private String teamName;
    private List<Person> members;
    private BigDecimal budget;

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
}
