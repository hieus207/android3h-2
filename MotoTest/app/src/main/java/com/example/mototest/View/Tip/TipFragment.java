package com.example.mototest.View.Tip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.mototest.R;
import com.example.mototest.View.Review.ViewPagerReviewAdapter;
import com.example.mototest.databinding.FragmentTipBinding;
import com.google.android.material.tabs.TabLayout;


public class TipFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tip, container, false);
        viewPager=(ViewPager)v.findViewById(R.id.vp_tip);
        tabLayout=(TabLayout)v.findViewById(R.id.tablayout_tip);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerReviewAdapter viewPagerReviewAdapter=new ViewPagerReviewAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerReviewAdapter.addFragment(new MeothilythuyetFragment(),"Mẹo thi lý thuyết");
        viewPagerReviewAdapter.addFragment(new MeothithuchanhFragment(),"Mẹo thi thực hành");
        viewPager.setAdapter(viewPagerReviewAdapter);

        return v;
    }
}