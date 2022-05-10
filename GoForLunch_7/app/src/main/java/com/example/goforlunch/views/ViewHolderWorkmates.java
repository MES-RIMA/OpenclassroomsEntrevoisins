package com.example.goforlunch.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.goforlunch.R;
import com.example.goforlunch.modele.firebase.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderWorkmates extends RecyclerView.ViewHolder {
    final View mView;
    @BindView(R.id.workmate_image) ImageView workmateImage;
    @BindView(R.id.workmate_text) TextView workmateText;
    public User user;

    ViewHolderWorkmates(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }
}
