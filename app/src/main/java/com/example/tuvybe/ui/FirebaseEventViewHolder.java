package com.example.tuvybe.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseEventViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindEvent(EventsSearchResponse mEvent) {
        ImageView eventImageView = (ImageView) mView.findViewById(R.id.eventImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.eventNameTextView);
        TextView eventTimeTextView = (TextView) mView.findViewById(R.id.eventTimeTextView);


        Picasso.get().load(mEvent.getLogo().getOriginal().getUrl()).into(eventImageView);
        nameTextView.setText(mEvent.getName().getText());
        eventTimeTextView.setText(mEvent.getStart().getLocal());

    }

    @Override
    public void onClick(View v) {
        final ArrayList<EventsSearchResponse> events = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_FAVOURITE_EVENTS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    events.add(snapshot.getValue(EventsSearchResponse.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, EventsDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("events", Parcels.wrap(events));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}