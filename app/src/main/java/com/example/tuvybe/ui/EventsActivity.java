package com.example.tuvybe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tuvybe.R;
import com.example.tuvybe.adapters.EventsListAdapter;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.network.EventsAPI;
import com.example.tuvybe.network.EventsClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
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


    private String[] eventsId = new String[]{"153504196183","115430197829","156742457911","153685608793","158192906241","154031360947","154772080459","151248804245","151172405735","63049080497","152141827301","143791601551","156939948611","151937774975"};

    private EventsSearchResponse mEventsSearchResponse;
    private List<EventsSearchResponse> eventsList = new ArrayList<>();
    @BindView(R.id.usernameTextView)
    TextView mUsernameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mUsernameTextView.setText("Hello "+username);


        for (int i=0;i< eventsId.length; i++){
            EventsAPI client = EventsClient.getClient();
            Call<EventsSearchResponse> call = client.getEvents(eventsId[i],EVENT_API_KEY);

            call.enqueue(new Callback<EventsSearchResponse>() {

                @Override
                public void onResponse(Call<EventsSearchResponse> call, Response<EventsSearchResponse> response) {
                    if (response.isSuccessful()) {
                        mEventsSearchResponse= response.body();
                        eventsList.add(mEventsSearchResponse);
                        mAdapter = new EventsListAdapter(EventsActivity.this, eventsList);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(EventsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView)searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<EventsSearchResponse> foundEvents = new ArrayList<>() ;

                for (int i=0; i<eventsList.size(); i++){
                    if( eventsList.get(i).getName().getText().toLowerCase().contains(newText.toLowerCase().trim())){
                        foundEvents.add(eventsList.get(i));
                    }
                }

                mAdapter = new EventsListAdapter(EventsActivity.this, foundEvents);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(EventsActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
//                showGames();
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(EventsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}