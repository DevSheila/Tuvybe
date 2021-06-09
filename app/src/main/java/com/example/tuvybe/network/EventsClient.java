package com.example.tuvybe.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tuvybe.Constants.EVENT_BRITE_BASE_URL;

public class EventsClient {
    private static Retrofit retrofit;
    public static EventsAPI getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)

                .build();
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(EVENT_BRITE_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        return retrofit.create(EventsAPI.class);
    }
}
