package com.example.tuvybe.network;

import com.example.tuvybe.models.EventsSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventsAPI {
    @GET("events")
    Call<EventsSearchResponse> getEvents( @Query("event_id") String id,@Query("api_key") String key);
}
