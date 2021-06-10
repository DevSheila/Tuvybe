package com.example.tuvybe.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.eventImageView)
    ImageView mImageLabel;
    @BindView(R.id.eventNameTextView)
    TextView mNameLabel;
    @BindView(R.id.descriptionTextView)
    TextView mDescriptionLabel;
    @BindView(R.id.eventTime)
    TextView mEventTime;



        private EventsSearchResponse mEvent;


public EventsDetailFragment(){

        }

public static EventsDetailFragment newInstance(EventsSearchResponse event) {
        EventsDetailFragment gameDetailFragment= new EventsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        args.putParcelable("position", Parcels.wrap(event.getId()));

        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                assert getArguments() != null;
                mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                View view =  inflater.inflate(R.layout.fragment_events_detail, container, false);
                ButterKnife.bind(this, view);


                Picasso.get().load(mEvent.getLogo().getOriginal().getUrl()).into(mImageLabel);
                mNameLabel.setText(mEvent.getName().getText());
                mDescriptionLabel.setText(mEvent.getDescription().getText());


                mEventTime.setText(mEvent.getStart().getLocal());

                return view;
        }

        @Override
        public void onClick(View v) {

        }
}