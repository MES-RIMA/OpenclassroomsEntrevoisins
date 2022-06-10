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
import com.example.goforlunch.modele.firebase.User;
import com.example.goforlunch.utils.UserHelper;
import com.example.goforlunch.views.WorkmatesRecyclerViewAdapter;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class WorkmatesListFragment extends BaseFragment {

    private WorkmatesRecyclerViewAdapter mRecyclerViewAdapter;
    private Boolean isUsersListIsReady = false;

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
        this.setFirestoreListener();
        return view;

    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList.clear();
        this.usersList.addAll(usersList);
        notifyFragment();
    }

    //FIRESTORE\\
    private void setFirestoreListener() {
        UserHelper.listenerUsersCollection().addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (queryDocumentSnapshots != null) {
                List<DocumentSnapshot> documentSnapshotList = new ArrayList<>(queryDocumentSnapshots.getDocuments());
                usersList = new ArrayList<>();
                if (documentSnapshotList.size() != 0) {
                    for (DocumentSnapshot documentSnapshot : documentSnapshotList) {
                        usersList.add(documentSnapshot.toObject(User.class));
                    }
                }
                if (!isUsersListIsReady) {
                    isUsersListIsReady = true;
                    recoversData();
                } else {
                    setUsersList(usersList);
                }
            }

        });
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
    protected void updateWithPosition() {
    }

}
