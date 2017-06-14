package com.laioffer.laiofferproject;


import android.graphics.Color;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    GridView gridView;

    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // take R.layout.fragment_comment layout and convert it to java object View
        // and then we can manipulate the object View
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        // convert it to gridView
        gridView = (GridView) view.findViewById(R.id.comment_grid);
        // Adapter is a 模式，每一个grid要现实什么东西，EventAdapter来操作
        gridView.setAdapter(new EventAdapter(getActivity()));
        return view;

    }

    public void onItemSelected(int position){
        for (int i = 0; i < gridView.getChildCount(); i++){
            if (position == i) {
                gridView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                gridView.getChildAt(i).setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
        }
    }

}
