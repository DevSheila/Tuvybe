package com.example.tuvybe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.network.EventsAPI;
import com.example.tuvybe.network.EventsClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tuvybe.Constants.EVENT_API_KEY;

public class EventsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private EventsListAdapter mAdapter;

    private List<EventsSearchResponse> eventsList;

    private String[] eventsId = new String[]{"153504196183","115430197829","156742457911"};

    private EventsSearchResponse mEventsSearchResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);


        for (int i=0;i< eventsId.length; i++){
            EventsAPI client = EventsClient.getClient();
            Call<EventsSearchResponse> call = client.getEvents(eventsId[i],EVENT_API_KEY);

            call.enqueue(new Callback<EventsSearchResponse>() {

                @Override
                public void onResponse(Call<EventsSearchResponse> call, Response<EventsSearchResponse> response) {
                    if (response.isSuccessful()) {
                        mEventsSearchResponse= response.body();
                        eventsList.add(mEventsSearchResponse);

//                    showGames();
                    }else{
                        Log.d("msg","api response unsuccessful");
                    }


                }

                @Override
                public void onFailure(Call<EventsSearchResponse> call, Throwable t) {
                    Log.d("msg",t.getMessage());
//                hideProgressBar();
                    Log.d("msg","api response unsuccessful");


                }
            });
        }

        mAdapter = new EventsListAdapter(EventsActivity.this, eventsList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(EventsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}