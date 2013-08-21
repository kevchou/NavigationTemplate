package com.kevinchou.android.navigationtemplate.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinchou.android.navigationtemplate.DemoFragment;
import com.kevinchou.android.navigationtemplate.R;

public class ViewPagerTabsFragment extends Fragment {

    ViewPagerTabsHandler mCallback;

    ViewPager mViewPager;
    DemoViewPagerAdapter mAdapter;
    int mArgInt;

    public static final String ARG_INT = "arg_int";
    public static final int NUM_OF_PAGES = 4;

    public interface ViewPagerTabsHandler {
        public ActionBar getActivityActionBar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (ViewPagerTabsHandler) activity;
        } catch (ClassCastException e) {
            Log.d("TODO", "proper error message about implementing interface");
        }
    }


    public static ViewPagerTabsFragment newInstance(int i) {
        ViewPagerTabsFragment fragment = new ViewPagerTabsFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_INT, i);

        fragment.setArguments(args);

        return fragment;
    }

    ActionBar mActionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("ViewPager /w ab tabs");

        Bundle args = getArguments();
        mArgInt = args.getInt(ARG_INT);

        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mAdapter = new DemoViewPagerAdapter(getActivity().getSupportFragmentManager());

        mViewPager.setAdapter(mAdapter);


        mActionBar = mCallback.getActivityActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };
        for (int i = 0; i < NUM_OF_PAGES; i++) {
            mActionBar.addTab(
                    mActionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        mActionBar.setSelectedNavigationItem(position);
                    }
                });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mActionBar.removeAllTabs();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
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
            return NUM_OF_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }
}
