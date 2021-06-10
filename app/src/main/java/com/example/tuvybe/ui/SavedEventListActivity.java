package com.example.tuvybe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity {
    private DatabaseReference mEventReference;
    private FirebaseRecyclerAdapter<EventsSearchResponse, FirebaseEventViewHolder> mFirebaseAdapter;
    private FirebaseAuth mAuth;

    @BindView(R.id.usernameTextView)TextView mUsernameTextView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_detail);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        mEventReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_FAVOURITE_EVENTS).child(uid);

        setUpFirebaseAdapter();
        showEvents();
    }
    private void setUpFirebaseAdapter(){


        FirebaseRecyclerOptions<EventsSearchResponse> options =
                new FirebaseRecyclerOptions.Builder<EventsSearchResponse>()
                        .setQuery(mEventReference, EventsSearchResponse.class)
                        .build();


        mFirebaseAdapter = new FirebaseRecyclerAdapter<EventsSearchResponse, FirebaseEventViewHolder >(options) {

            @Override
            protected void onBindViewHolder(@NonNull FirebaseEventViewHolder firebaseEventViewHolder, int position, @NonNull EventsSearchResponse event) {
                firebaseEventViewHolder.bindEvent(event);
            }

            @NonNull
            @Override
            public FirebaseEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_item, parent, false);
                return new FirebaseEventViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showEvents() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
