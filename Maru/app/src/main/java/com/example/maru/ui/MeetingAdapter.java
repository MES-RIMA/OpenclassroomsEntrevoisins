package com.example.maru.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    // FOR DATA ---
    private ArrayList<Meeting> meetings;
    // FOR CALLBACK ---
    private DeleteListener callback;
    public interface DeleteListener {
        void onClickDelete(Meeting meeting);
    }

    public MeetingAdapter(ArrayList<Meeting> meetings,DeleteListener callback) {
        this.meetings = meetings;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        Meeting meeting = meetings.get(position);
        viewHolder.displayMeeting(meeting,callback);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return meetings.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView place;
        public final TextView recipient;
        public final TextView hour;
        public final TextView subject;
        private ImageButton deleteButton;


        public ViewHolder(View view) {
            super(view);
            place = view.findViewById(R.id.place);
            hour = view.findViewById(R.id.hour);
            subject = view.findViewById(R.id.subject);
            recipient = view.findViewById(R.id.recipient);
            deleteButton = view.findViewById(R.id.delete_button);

        }

        public void displayMeeting(Meeting meeting, MeetingAdapter.DeleteListener callback) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

            subject.setText(meeting.getSubject());
            place.setText(meeting.getPlace());
            recipient.setText("To: " + meeting.getRecipient());
           hour.setText(meeting.getHour());
            deleteButton.setOnClickListener(view -> callback.onClickDelete(meeting));
        }


    }
}



