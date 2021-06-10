package com.example.tuvybe.models;

public class Ticket {
    private String num_tickets;
    private String userId;
    private String userName;
    private String userEmail;
    private String eventId;

    public Ticket( String eventid,String num_tickets, String userId, String userName, String email) {

        this.num_tickets = num_tickets;
        this.userId = userId;
        this.userName = userName;
        this.userEmail= email;
        this.eventId=eventid;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
