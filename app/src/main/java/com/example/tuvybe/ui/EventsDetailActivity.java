package com.example.tuvybe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tuvybe.R;
import com.example.tuvybe.adapters.EventsPageAdapter;
import com.example.tuvybe.models.EventsSearchResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsDetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private EventsPageAdapter adapterViewPager;
    private EventsSearchResponse mEventsSearchResponse;

    List<EventsSearchResponse> mEventsList;

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

}