package com.example.tuvybe.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.models.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseTicketViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;
    private EventsSearchResponse mEvent;
    List<EventsSearchResponse> mListEvents;
    public FirebaseTicketViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        Log.i("view", String.valueOf(itemView.getContext()));
    }
    public void bindReview(Ticket ticket) {
        firebaseAdd();

        Log.i("ticket", ticket.getUserName());
        TextView userNameTextView = (TextView) mView.findViewById(R.id.userName);
        TextView eventIdTextView = (TextView) mView.findViewById(R.id.eventId);
        TextView emailTextView = (TextView) mView.findViewById(R.id.userEmail);
        TextView ticketsTextView = (TextView) mView.findViewById(R.id.numTickets);


        TextView eventName= (TextView) mView.findViewById(R.id.eventName);
        TextView eventStart= (TextView) mView.findViewById(R.id.eventStart);
        TextView eventEnd = (TextView) mView.findViewById(R.id.eventEnd);
        TextView eventUrl = (TextView) mView.findViewById(R.id.eventUrl);

        userNameTextView.setText("Username: "+ticket.getUserName());
        emailTextView.setText("Email: " +ticket.getUserEmail());
        ticketsTextView.setText("No Tickets: "+ticket.getNum_tickets());

        eventIdTextView.setText("Event Id: "+ ticket.getEventId());
        eventName.setText("Event Name: "+ticket.getEventName());
        eventStart.setText("Start: "+ticket.getEventStart());
        eventEnd.setText("End: "+ticket.getEventEnd());
        eventUrl.setText(ticket.getEventPlace());
    }
    public void firebaseAdd(){
        final ArrayList<Ticket> ticketList = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        int itemPosition = getLayoutPosition();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_TICKETS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ticketList.add(snapshot.getValue(Ticket.class));

                    for (int i = 0; i < ticketList.size(); i++) {

                        Log.i("tickets",ticketList.get(i).toString());

                    }
                    Log.i("tckets", ticketList.get(0).toString());



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("database","The read failed: " + error.getCode());
            }
        });


    }


}
