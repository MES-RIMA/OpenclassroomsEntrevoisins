package com.example.maru.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.DI.DI;
import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MeetingAdapter.DeleteListener {
    private ActivityMainBinding binding;
    private MeetingAdapter mAdapter;
    private List<Meeting> mMeetingArrayList;
    public final MeetingApiService mMeetingApiService = DI.getMeetingApiService();


    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        mAdapter = new MeetingAdapter((ArrayList<Meeting>) mMeetingArrayList, this);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void resetFilter() {
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingApiService.getMeetings());
        Objects.requireNonNull(binding.recyclerview.getAdapter()).notifyDataSetChanged();
    }

    private void dateDialog() {
        int selectedYear = 2021;
        int selectedMonth = 6;
        int selectedDayOfMonth = 16;

// Date Select Listener.
        @SuppressLint("NotifyDataSetChanged") DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(i, i1, i2);
            mMeetingArrayList.clear();
            mMeetingArrayList.addAll(mMeetingApiService.getMeetingsFilteredByDate(cal.getTime()));
            Objects.requireNonNull(binding.recyclerview.getAdapter()).notifyDataSetChanged();
        };

// Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

// Show
        datePickerDialog.show();
    }

    private void initData() {
        mMeetingArrayList = new ArrayList<>(mMeetingApiService.getMeetings());
    }

    private void setButton() {
        binding.startAddActivity.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetFilter();
    }


    @Override
    public void onClick(View view) {
        if (view == binding.startAddActivity) {
            startActivity(new Intent(this, AddMeetingActivity.class));
        }
    }

    // action delete item

    @SuppressLint("NotifyDataSetChanged")
    public void onClickDelete(Meeting meeting) {
        Log.d(MainActivity.class.getName(), " delete item.");
        mMeetingApiService.deleteMeeting(meeting);
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingApiService.getMeetings());
        Objects.requireNonNull(binding.recyclerview.getAdapter()).notifyDataSetChanged();
    }
}

