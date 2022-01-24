package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(new Meeting("Reunion A", " 19h", "Mario", "toto@gmail.com", new Date(1626557205000L)),
            new Meeting("Reunion A", "14H", "Mario", "toto@gmail.com,titi@gmail.com,toto@gmail.com", new Date(1626557205000L)),
            new Meeting("Reunion B", "18H", "Luigi", "titi@gmail.com,toti@yahoo.com", new Date(1626557205000L)),
            new Meeting("Reunion C", "11H", "Peach", "hugue@yahoo.com,titi@gmail.com,", new Date(1626384405000L)),
            new Meeting("Reunion A", "9H", "Mario", "toti@yahoo.com,titi@gmail.com", new Date(1626557205000L)),
            new Meeting("Reunion D", "10H", "Organisation", "toto@gmail.com", new Date(1626470805000L)),
            new Meeting("Reunion B", "13H", "Luigi", "toto@gegemail.com", new Date(1626384405000L)),
            new Meeting("Réunion C", "19H", "organisation", "toto@gegemail.com", new Date(1626384405000L)),
            new Meeting("Reunion D", "17H", "Peach", "toto@gegemail.com,roro@gmail.com", new Date(1626384405000L)));

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
