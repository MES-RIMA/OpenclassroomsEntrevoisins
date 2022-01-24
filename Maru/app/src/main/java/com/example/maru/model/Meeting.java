package com.example.maru.model;

import java.util.Date;

public class Meeting {

    String place, hour, subject, recipient;
    Date mDate;

    public Meeting(String place, String hour, String subject, String recipient) {
        this.place = place;
        this.hour = hour;
        this.subject = subject;
        this.recipient = recipient;
        this.mDate = new Date();
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


    public Meeting(String place, String hour, String subject, String recipient, Date date) {
        this.place = place;
        this.hour = hour;
        this.subject = subject;
        this.recipient = recipient;
        mDate = date;
    }

}
