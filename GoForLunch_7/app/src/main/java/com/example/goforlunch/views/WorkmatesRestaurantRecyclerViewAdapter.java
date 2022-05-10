package com.example.goforlunch.views;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.goforlunch.R;
import com.example.goforlunch.modele.firebase.User;

import java.util.ArrayList;

public class WorkmatesRestaurantRecyclerViewAdapter extends BaseRecyclerViewAdapterWorkmates {

    private ArrayList<User> listOfUserWithSameChoice;
    private RequestManager glide;
    private Context context;

    public WorkmatesRestaurantRecyclerViewAdapter(ArrayList<User> listOfUserWithSameChoice,
                                                  RequestManager glide,
                                                  Context context) {
        this.listOfUserWithSameChoice = listOfUserWithSameChoice;
        this.glide = glide;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderWorkmates holder, int position) {
        holder.user = listOfUserWithSameChoice.get(position);

        String workmateIsJoining = holder.user.getUserName() + context.getString(R.string.is_joining);
        holder.workmateText.setText(workmateIsJoining);
        if (holder.user.getUserUrlImage() != null) {
            glide.load(holder.user.getUserUrlImage()).apply(RequestOptions.circleCropTransform()).into(holder.workmateImage);
        }
    }

    @Override
    public int getItemCount() {
        return listOfUserWithSameChoice.size();
    }

}
