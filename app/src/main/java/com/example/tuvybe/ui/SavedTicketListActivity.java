package com.example.tuvybe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.models.Ticket;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedTicketListActivity extends AppCompatActivity {
    private DatabaseReference mTicketReference;
    private FirebaseRecyclerAdapter<Ticket, FirebaseTicketViewHolder> mFirebaseAdapter;
    private FirebaseAuth mAuth;
    private Ticket ticket;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tickets);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        mTicketReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_TICKETS).child(uid);


        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter(){


        FirebaseRecyclerOptions<Ticket> options =
                new FirebaseRecyclerOptions.Builder<Ticket>()
                        .setQuery(mTicketReference, Ticket.class)
                        .build();


        mFirebaseAdapter = new FirebaseRecyclerAdapter<Ticket, FirebaseTicketViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull FirebaseTicketViewHolder firebaseTicketViewHolder, int position, @NonNull Ticket ticket) {
                firebaseTicketViewHolder.bindReview(ticket);
            }

            @NonNull
            @Override
            public FirebaseTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);
                return new FirebaseTicketViewHolder(view);
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


}

