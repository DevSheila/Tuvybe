package com.example.tuvybe.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tuvybe.R;
import com.example.tuvybe.adapters.EventsListAdapter;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.network.EventsAPI;
import com.example.tuvybe.network.EventsClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.widget.Toolbar;
import static com.example.tuvybe.Constants.EVENT_API_KEY;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.all)TextView mAll;

    @BindView(R.id.music)TextView mMusic;
    @BindView(R.id.business)TextView mBusiness;
    @BindView(R.id.tech)TextView mTech;
    @BindView(R.id.arts)TextView mArts;
    @BindView(R.id.food)TextView mFood;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private EventsListAdapter mAdapter;
    private DrawerLayout drawer;

    private String[] eventsId = new String[]{"153504196183","115430197829","156742457911","153685608793","158192906241","154031360947","154772080459","151248804245","151172405735","63049080497","152141827301","143791601551","156939948611","151937774975","158364475409","158871646371","63166502710","870138196771","143850844749","157692353073"};

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

        mMusic.setOnClickListener(this);
        mFood.setOnClickListener(this);
        mBusiness.setOnClickListener(this);
        mArts.setOnClickListener(this);
        mTech.setOnClickListener(this);
        mAll.setOnClickListener(this);


        for (int i=0;i< eventsId.length; i++){
            EventsAPI client = EventsClient.getClient();
            Call<EventsSearchResponse> call = client.getEvents(eventsId[i],EVENT_API_KEY);

            call.enqueue(new Callback<EventsSearchResponse>() {

                @Override
                public void onResponse(Call<EventsSearchResponse> call, Response<EventsSearchResponse> response) {
                    hideProgressBar();
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
                    Log.d("msg","api response unsuccessful");
                    hideProgressBar();


                }
            });
            drawer = findViewById(R.id.drawer_layout);
//        setSupportActionBar(mToolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
//        mToolbar.getNavigationIcon().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
//        mToolbar.setNavigationIcon(R.drawable.ic_toggle);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            drawer.addDrawerListener(toggle);
            toggle.syncState();

        }
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
           drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_profile:
                Intent intent = new Intent(EventsActivity.this, EventsDetailActivity.class);
                startActivity(intent);
                break;
        }

//        int id = item.getItemId();
        Log.i("item select", item.getItemId()+"");
        Log.i("message", R.id.nav_message+"");
//
//        if(id == R.id.nav_message){
//                Intent intent = new Intent(EventsActivity.this, SavedTicketListActivity.class);
//                startActivity(intent);
//        }
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public void sortByCategoryName(String categoryId) {
        List<EventsSearchResponse> foundEvents = new ArrayList<>() ;

       if(categoryId != null){
           for (int i = 0; i < eventsList.size(); i++) {

               if (eventsList.get(i).getCategoryId().equals(categoryId)) {
                   foundEvents.add(eventsList.get(i));
               }
           }
       }if(categoryId == ""){
            for (int i = 0; i < eventsList.size(); i++) {
                    foundEvents.add(eventsList.get(i));

            }
        }


        Log.i("events", (eventsList.size() +""));
        mAdapter = new EventsListAdapter(EventsActivity.this, foundEvents);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(EventsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View v) {
        String categoryId;
        if(v == mAll){
            categoryId = "";
            sortByCategoryName(categoryId);
        }
        if(v == mMusic){
            categoryId = "103";
            sortByCategoryName(categoryId);
        }
        if(v == mFood){
            categoryId = "110";
            sortByCategoryName(categoryId);
        }
        if(v == mBusiness){
            categoryId = "101";
            sortByCategoryName(categoryId);
        }
        if(v == mArts){
            categoryId = "105";
            sortByCategoryName(categoryId);
        }
        if(v == mTech){
            categoryId = "102";
            sortByCategoryName(categoryId);
        }

    }
    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }



}