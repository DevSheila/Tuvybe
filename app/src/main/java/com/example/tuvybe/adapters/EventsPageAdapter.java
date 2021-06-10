package com.example.tuvybe.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.tuvybe.models.EventsSearchResponse;
import com.example.tuvybe.ui.EventsDetailFragment;

import java.util.List;

public class EventsPageAdapter extends FragmentPagerAdapter {
    private EventsSearchResponse mEventsSearchResponse ;
    private List<EventsSearchResponse > mEvents;

    public EventsPageAdapter(@NonNull FragmentManager fm, int behavior, List<EventsSearchResponse> results) {
        super(fm, behavior);
        mEvents= results;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return EventsDetailFragment.newInstance(mEvents.get(position));
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }
    @Override
    public String getPageTitle(int position) {
        return mEvents.get(position).getName().getText();
    }
}
