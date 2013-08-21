package com.kevinchou.android.navigationtemplate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoFragment extends Fragment {

    public static final String ARG_INT = "object_number";
    public static final String ARG_STRING = "object_string";


    public static DemoFragment newInstance(int i, String s) {

        DemoFragment df = new DemoFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_INT, i);
        args.putString(ARG_STRING, s);

        df.setArguments(args);

        return df;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // get arguments
        int i = getArguments().getInt(ARG_INT);
        String s = getArguments().getString(ARG_STRING);

        // inflate view
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

        ((TextView) rootView.findViewById(R.id.demoTextView)).setText( "Demo Fragment\n" + s + i);

        //getActivity().setTitle(s); // sets Action Bar title

        return rootView;
    }
}
