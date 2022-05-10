package com.example.goforlunch.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.goforlunch.R;
import com.example.goforlunch.views.WorkmatesRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class WorkmatesListFragment extends BaseFragment {

    private WorkmatesRecyclerViewAdapter mRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WorkmatesListFragment() {
    }

    public static WorkmatesListFragment newInstance() {
        return new WorkmatesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workmates_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            this.mRecyclerViewAdapter = new WorkmatesRecyclerViewAdapter(mListener,
                    Glide.with(this),
                    context);
            recyclerView.setAdapter(this.mRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recoversData();
    }

    /**
     * Notify fragment that the data has changed.
     */
    @Override
    protected void notifyFragment() {
        if (mParcelableRestaurantDetails != null
                && !usersList.isEmpty()) {
            mRecyclerViewAdapter.updateResources(mParcelableRestaurantDetails, usersList);
        }
    }

    @Override
    protected void updateWithPosition() { }

}
