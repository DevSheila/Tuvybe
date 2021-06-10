package com.example.tuvybe.models;

public class Ticket {
    private String num_tickets;
    private String userId;
    private String userName;
    private String userEmail;
    private String eventId;
    private String eventName;
    private String eventStart;
    private String eventEnd;
    private String eventPlace;


    public Ticket() {


    }

    public Ticket( String num_tickets, String userId, String userName, String email,String eventid,String eventName,String eventStart,String eventEnd,String eventPlace) {

        this.num_tickets = num_tickets;
        this.userId = userId;
        this.userName = userName;
        this.userEmail= email;
        this.eventId=eventid;
        this.eventName=eventName;
        this.eventStart=eventStart;
        this.eventEnd=eventEnd;
        this.eventPlace=eventPlace;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }
}
