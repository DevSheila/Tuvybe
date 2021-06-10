package com.example.tuvybe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuvybe.R;
import com.example.tuvybe.models.Ticket;
import com.example.tuvybe.ui.EventsDetailActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketsViewHolder>{
    private List<Ticket> mTicketList;
    private Context mContext;

    public TicketListAdapter(Context context, List<Ticket> ticketList) {
        mContext = context;
        mTicketList = ticketList;
    }

    @NonNull
    @Override
    public TicketListAdapter.TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);
        TicketsViewHolder viewHolder = new TicketsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListAdapter.TicketsViewHolder holder, int position) {
        holder.bindTicket(mTicketList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTicketList.size();
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.userName)
        TextView mUsernameTextView;
        @BindView(R.id.userEmail) TextView mEmailTextView;

        private Context mContext;

        public TicketsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindTicket(Ticket ticket) {
            mUsernameTextView.setText(ticket.getUserName() +""+ticket.getUserName());
            mEmailTextView.setText(ticket.getNum_tickets()
            );
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, EventsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("tickets", Parcels.wrap(mTicketList));
            mContext.startActivity(intent);
        }
    }
}
