package com.example.shieldmaidens;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SpeechToText {

    @Headers({"Content-Type: audio/flac"})
    @POST("v1/recognize?model=en-US_NarrowbandModel")
    Observable<SpeechText> fetchTextFromSpeech(@Body RequestBody file, @Header("Authorization") String user);
}
