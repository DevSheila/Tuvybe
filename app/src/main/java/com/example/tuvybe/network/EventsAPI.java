package com.example.tuvybe.network;

import com.example.tuvybe.models.EventsSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsAPI {
    @GET("events/{event_id}")
    Call<EventsSearchResponse> getEvents(@Path("event_id") String id,@Query("token") String key);
}
