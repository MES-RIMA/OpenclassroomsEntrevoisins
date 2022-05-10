package com.example.goforlunch.utils;

import com.example.goforlunch.modele.nearby.NearbyResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface GoogleStreams {

    public static Observable<NearbyResponse> streamFetchNearbySearch(String location,
                                                                     int radius,
                                                                     String type,
                                                                     String apiKey){
        GoogleService googleService = GoogleService.retrofit.create(GoogleService.class);
        return googleService.getNearbySearch(location, radius, type, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
