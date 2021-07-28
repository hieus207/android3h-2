package com.example.mototest.View.Review;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewPagerReviewAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private final ArrayList<String> fragmenttitle=new ArrayList<>();

    public ViewPagerReviewAdapter(@NonNull @NotNull FragmentManager fm,int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
        public  void addFragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        fragmenttitle.add(title);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitle.get(position);
    }



}
