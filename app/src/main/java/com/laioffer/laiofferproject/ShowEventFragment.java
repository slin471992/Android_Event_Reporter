package com.laioffer.laiofferproject;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEventFragment extends Fragment {


    public ShowEventFragment() {
        // Required empty public constructor
    }

    ListView listView;
    DatabaseReference database;

    EventListAdapter eventListAdapter;

    List<Event> events;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_event, container, false);
        listView = (ListView) view.findViewById(R.id.event_listview);
        final String username = ((EventActivity)getActivity()).getUsername();
        database = FirebaseDatabase.getInstance().getReference();
        setAdapter();
        return view;

    }

    private void setAdapter() {
        events = new ArrayList<Event>();
        database.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Event event = noteDataSnapshot.getValue(Event.class);
                    events.add(event);
                }
                Collections.sort(events, new Comparator<Event>() {
                   @Override
                    public int compare(Event e1, Event e2) {
                       if (e1.getTime() == e2.getTime()) {
                           return 0;
                       }
                       return e1.getTime() < e2.getTime() ? 1 : -1;
                   }
                });
                eventListAdapter = new EventListAdapter(getContext(), events);
                eventListAdapter.setUserName(((EventActivity)getActivity()).getUsername());
                listView.setAdapter(eventListAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: do something
            }
        });
    }


}
