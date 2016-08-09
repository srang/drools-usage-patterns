package com.rhc.drools.example.vo;

/**
 * Created by srang on 8/3/16.
 */
public class MyProblem {
    private String status;
    private String reason;

    public MyProblem() {}
    public MyProblem(String reason, String status) {
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyProblem { ").append(status).append(", ").append(reason).append(" }");
        return sb.toString();
    }
}
