package com.kevinchou.android.navigationtemplate.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinchou.android.navigationtemplate.DemoFragment;
import com.kevinchou.android.navigationtemplate.R;

public class SimpleViewPagerFragment extends Fragment {

    DemoViewPagerAdapter mAdapter;
    int mArgInt;

    public static final String ARG_INT = "arg_int";

    public static SimpleViewPagerFragment newInstance(int i) {
        SimpleViewPagerFragment fragment = new SimpleViewPagerFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_INT, i);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Regular ViewPager");

        Bundle args = getArguments();
        mArgInt = args.getInt(ARG_INT);

        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);

        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        mAdapter = new DemoViewPagerAdapter(getActivity().getSupportFragmentManager());

        pager.setAdapter(mAdapter);

        return v;
    }

    public class DemoViewPagerAdapter extends FragmentStatePagerAdapter {
        public DemoViewPagerAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = DemoFragment.newInstance(i, "ViewPager Number " + mArgInt + "\npage ");
            return fragment;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }
}
