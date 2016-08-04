package com.rhc.drools.example.persistence;

/**
 * Created by srang on 8/4/16.
 */
public class TeamEvaluation {
    private String status;

    public TeamEvaluation(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{ " + status +  " }";
    }
}

