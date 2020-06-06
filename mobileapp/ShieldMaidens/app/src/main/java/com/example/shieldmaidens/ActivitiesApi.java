package com.example.shieldmaidens;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ActivitiesApi {

    @GET("v1/activities")
    Observable<List<UserActivity>> getActivities();
}
