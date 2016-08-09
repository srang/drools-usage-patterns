package com.rhc.drools.example.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by srang on 8/3/16.
 */
public class MyTeam {
    private String teamName;
    private List<MyPerson> members;
    private List<MyProblem> myProblems;
    private BigDecimal budget;
    private MyTeamEvaluation evaluation;

    public MyTeam(String name) {
        this.budget = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_CEILING);
        this.teamName = name;
        this.members = new ArrayList<MyPerson>();
        this.myProblems = new ArrayList<MyProblem>();
        this.evaluation = new MyTeamEvaluation("Unevaluated Submitted");
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<MyPerson> getMembers() {
        return members;
    }

    public void addMember(MyPerson member) {
        this.budget = budget.add(member.getDonation()).setScale(2, BigDecimal.ROUND_CEILING);
        this.members.add(member);
    }

    public MyTeamEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = new MyTeamEvaluation(evaluation);
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public List<MyProblem> getProblems() {
        return myProblems;
    }

    public void addProblem(MyProblem myProblem) {
        this.myProblems.add(myProblem);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyTeam: {\n");
        sb.append("  status: ").append(evaluation).append(",\n");
        sb.append("  name: ").append(teamName).append(",\n");
        sb.append("  budget: ").append(budget).append(",\n");
        sb.append("  members: {\n");
        for(MyPerson p : members) {
            sb.append("    ").append(p.toString()).append(",\n");
        }
        sb.append("  }\n");
        sb.append("  myProblems: {\n");
        for(MyProblem p : myProblems) {
            sb.append("    ").append(p.toString()).append(",\n");
        }
        sb.append("  }\n");
        sb.append("}");
        return sb.toString();
    }
}
