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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tuvybe.Constants;
import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.models.Ticket;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class EventsDetailFragment extends Fragment  implements View.OnClickListener ,TicketsActivity.ExampleDialogListener{
    @BindView(R.id.eventImageView)
    ImageView mImageLabel;
    @BindView(R.id.eventNameTextView)
    TextView mNameLabel;
    @BindView(R.id.descriptionTextView)
    TextView mDescriptionLabel;
    @BindView(R.id.eventTime)
    TextView mEventTime;
    @BindView(R.id.addEventToFavourites)
    TextView mFavouritesLabel;
    @BindView(R.id.attendButton) Button mAttendButton;

    private EventsSearchResponse mEvent;
    private Ticket mTicket;
    private List<Ticket> mTicketsList;


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
                mFavouritesLabel.setOnClickListener(this);
                mAttendButton.setOnClickListener(this);

                return view;
        }

        @Override
        public void onClick(View v) {
            if(v == mFavouritesLabel){


                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(mFavouritesLabel);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference eventsRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_FAVOURITE_EVENTS)
                        .child(uid);

                DatabaseReference pushRef = eventsRef.push();
                String pushId = pushRef.getKey();
                mEvent.setPushId(pushId);
                pushRef.setValue(mEvent);

                Log.i("fav click","item clicked"+pushId);
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

            }
            if(v == mAttendButton){
                TicketsActivity exampleDialog = new TicketsActivity();
                exampleDialog.show(getFragmentManager(),"example dialog");
            }

        }

    @Override
    public void applyText(String num_tickets) {
//

    }
}