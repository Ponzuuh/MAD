package com.sp.mobileappliancesdirector;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        CardView cardView = view.findViewById(R.id.cardView);
        ViewPager aboutViewPager = view.findViewById(R.id.aboutViewPager);
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        aboutViewPager.setAdapter(adapter);

        return view;
    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return the fragment based on the position
            switch (position) {
                case 0:
                    return new InfoFragment1();
                case 1:
                    return new InfoFragment2();
                // Add more cases for additional fragments
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Return the total number of fragments
            return 2; // Change to the actual number of fragments you have
        }
    }
}