package com.example.goforlunch.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.goforlunch.controller.HomeActivity;
import com.example.goforlunch.modele.ParcelableRestaurantDetails;
import com.example.goforlunch.modele.details.PlaceDetailsResponse;
import com.example.goforlunch.modele.firebase.User;
import com.example.goforlunch.modele.nearby.Result;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {

    protected OnListFragmentInteractionListener mListener;
    protected static Double currentLat;
    protected static Double currentLng;
    protected ParcelableRestaurantDetails mParcelableRestaurantDetails;
    protected ArrayList<User> usersList;

    //CUSTOM\\
    protected abstract void notifyFragment();
    protected abstract void updateWithPosition();

    protected void recoversData() {
        ((HomeActivity) requireActivity()).recoversData();
    }

    public void setPosition(Double currentLat, Double currentLng) {
        this.currentLat = currentLat;
        this.currentLng = currentLng;
        updateWithPosition();
    }

    public void shareDataToFragment(Double currentLat,
                                    Double currentLng,
                                    ParcelableRestaurantDetails mParcelableRestaurantDetails,
                                    ArrayList<User> usersList) {
        setPosition(currentLat, currentLng);
        this.mParcelableRestaurantDetails = new ParcelableRestaurantDetails();
        this.mParcelableRestaurantDetails = mParcelableRestaurantDetails;
        this.usersList.clear();
        this.usersList.addAll(usersList);
        notifyFragment();
    }

    /**
     * A method to receive a new ParcelableRestaurantDetails from HomeActivity for notify the current fragment.
     * @param mParcelableRestaurantDetailsAutocomplete Result of autocomplete.
     */
    public void setParcelableRestaurantDetails(ParcelableRestaurantDetails mParcelableRestaurantDetailsAutocomplete) {
        this.mParcelableRestaurantDetails = new ParcelableRestaurantDetails();
        this.mParcelableRestaurantDetails = mParcelableRestaurantDetailsAutocomplete;
        notifyFragment();
    }



    //OVERRIDE\\

    /**
     * Call at the creation of the fragment.
     * @param savedInstanceState Bundle who contains extra data for the fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usersList = new ArrayList<>();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Result result, PlaceDetailsResponse placeDetailsResponse);
    }
}
