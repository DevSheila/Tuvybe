package com.example.tuvybe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.adapters.EventsPageAdapter;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.models.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsDetailActivity extends AppCompatActivity implements TicketsActivity.ExampleDialogListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private EventsPageAdapter adapterViewPager;
    private EventsSearchResponse mEventsSearchResponse;

    List<EventsSearchResponse> mEventsList;
    private Ticket mTicket;
    List<Ticket> mTicketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_detail);
        ButterKnife.bind(this);

        mEventsList = Parcels.unwrap(getIntent().getParcelableExtra("events"));
        int startingPosition = getIntent().getIntExtra("position",0);
        for(int i=0;i<mEventsList.size();i++){
            Log.i("click",mEventsList.get(i).getName().toString());
        }

        adapterViewPager = new EventsPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mEventsList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
    @Override
    public void applyText( String num_tickets) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String username = user.getDisplayName();
        String useremail = user.getEmail();

        mEventsList= Parcels.unwrap(getIntent().getParcelableExtra("events"));
        int startingPosition = getIntent().getIntExtra("position",0);
        String eventId =mEventsList.get(startingPosition).getId();


        mTicket=new Ticket(eventId,num_tickets,uid,username,useremail);


        DatabaseReference reviewsRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_TICKETS)
                .child(uid);
        reviewsRef.push().setValue(mTicket);

        Intent intent = getIntent();
        finish();
        startActivity(intent);
        Toast.makeText(EventsDetailActivity.this, "Event Registered", Toast.LENGTH_SHORT).show();
    }

}