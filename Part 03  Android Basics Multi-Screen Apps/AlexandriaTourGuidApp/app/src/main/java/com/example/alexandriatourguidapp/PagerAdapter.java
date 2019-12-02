package com.example.alexandriatourguidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                 return new RestaurantFragment();
            case 1:
                return new CafeFragment();
            case 2:
                return new CinemaFragment();
            default:
                return new HistoricalFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Restaurants";
            case 1:
                return "Cafes";
            case 2:
                return "Cinemas";
            default:
                return "Historical";

        }
    }
}
