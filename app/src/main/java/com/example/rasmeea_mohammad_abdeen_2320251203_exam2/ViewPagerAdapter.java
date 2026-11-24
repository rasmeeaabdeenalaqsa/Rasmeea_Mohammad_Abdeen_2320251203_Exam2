package com.example.rasmeea_mohammad_abdeen_2320251203_exam2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new SecondFragment();
        } else if (position == 2) {
            return new ThirdFragment();
        }
        return new FirstFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
