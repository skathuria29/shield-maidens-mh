package com.example.shieldmaidens;

import com.example.shieldmaidens.TonePojo.Tones;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ToneAnalyzer {

    @GET("v3/tone")
    Observable<Tones> fetchToneMetric(@Header("Authorization") String user, @Query("version") String date, @Query("text") String text);
}
