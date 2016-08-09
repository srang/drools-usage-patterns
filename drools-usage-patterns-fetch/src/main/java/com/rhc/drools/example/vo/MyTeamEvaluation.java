package com.rhc.drools.example.vo;

/**
 * Created by srang on 8/4/16.
 */
public class MyTeamEvaluation {
    private String status;

    public MyTeamEvaluation(String status) {
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

