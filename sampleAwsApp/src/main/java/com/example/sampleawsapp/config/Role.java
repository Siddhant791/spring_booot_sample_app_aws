package com.example.sampleawsapp.config;

public class Role {
    private String lob;
    private String message;

    // Getters and Setters
    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Role{" +
                "lob='" + lob + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}