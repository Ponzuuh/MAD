package com.sp.mobileappliancesdirector;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment1();
            case 1:
                return new InfoFragment2();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2; //According to the number of fragments I have
    }
}
