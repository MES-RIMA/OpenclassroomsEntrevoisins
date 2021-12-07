package com.example.maru.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.DI.DI;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityAddMeetingBinding binding;
    private MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    private void initUI() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("New Meeting");
    }

    private void setButton() { binding.submitButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.submitButton) {
            onSubmit();
        }
    }

    private void onSubmit() {
        String place = binding.textFieldPlace.getEditText().getText().toString();
        String hour = binding.textFieldHour.getEditText().getText().toString();
        String subject = binding.textFieldSubject.getEditText().getText().toString();
        String recipient = binding.textFieldRecipient.getEditText().getText().toString();

        if (place.isEmpty()) {
            binding.textFieldPlace.setError("Please type a place");
            return;
        }
        if (hour.isEmpty()) {
            binding.textFieldPlace.setError("Please type a hour");
            return;
        }
        if (subject.isEmpty()) {
            binding.textFieldSubject.setError("Please type a subject");
            return;
        }
        if (recipient.isEmpty()) {
            binding.textFieldRecipient.setError("Please type a recipient");
            return;
        }

        mMeetingApiService.createMeeting(new Meeting (place,hour,subject,recipient));
        Toast.makeText(this, "Mail sent !", Toast.LENGTH_SHORT).show();
        finish();

    }

}
