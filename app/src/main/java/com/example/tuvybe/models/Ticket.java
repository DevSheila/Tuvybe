package com.example.tuvybe.models;

public class Ticket {
    private String num_tickets;
    private String userId;
    private String userName;

    public Ticket( String num_tickets, String userId, String userName) {

        this.num_tickets = num_tickets;
        this.userId = userId;
        this.userName = userName;
    }

    public String getNum_tickets() {
        return num_tickets;
    }

    public void setNum_tickets(String num_tickets) {
        this.num_tickets = num_tickets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
