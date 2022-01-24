package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingApiService {
    /**
     * Get all my Meetings
     *
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a meeting
     *
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a reunion
     *
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetingsFilteredByDate(Date date);

}
