package com.rhc.drools.example.persistence;

/**
 * Created by srang on 8/3/16.
 */
public class Problem {
    private String status;
    private String reason;

    public Problem(String reason, String status) {
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
        sb.append("Problem { ").append(status).append(", ").append(reason).append(" }");
        return sb.toString();
    }
}
