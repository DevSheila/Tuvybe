package com.example.tuvybe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.R;
import com.example.tuvybe.models.EventsSearchResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsViewHolder> {
    private List<EventsSearchResponse> mEventsList;
    private Context mContext;

    public EventsListAdapter(Context context, List<EventsSearchResponse> events) {
        mContext = context;
        mEventsList = events;
    }

    @NonNull
    @Override
    public EventsListAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_item, parent, false);
        EventsViewHolder viewHolder = new EventsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.EventsViewHolder holder, int position) {
        holder.bindEvent(mEventsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.eventImageView)
        ImageView mEventImageView;
        @BindView(R.id.eventNameTextView)
        TextView mNameTextView;
        @BindView(R.id.eventTimeTextView)
        TextView mEventTimeTextView;
        @BindView(R.id.attendees)
        TextView mOnlineTextView;

        private Context mContext;

        public EventsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindEvent(EventsSearchResponse event) {
            Picasso.get().load(event.getLogo().getOriginal().getUrl()).into(mEventImageView);
            mNameTextView.setText(event.getName().getText());
            mEventTimeTextView.setText(event.getStart().getLocal());
            if (event.getOnlineEvent() == true) {
                mOnlineTextView.setText("Online");
            } else {
                mOnlineTextView.setText("Venue :" + event.getVenueId().toString());

            }


        }

        @Override
        public void onClick(View v) {

        }
    }
}
