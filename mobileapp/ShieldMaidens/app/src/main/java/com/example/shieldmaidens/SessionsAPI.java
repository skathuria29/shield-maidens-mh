package com.example.shieldmaidens;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SessionsAPI {

    @GET("v1/sessions")
    Observable<List<Session>> getSessions();
}
