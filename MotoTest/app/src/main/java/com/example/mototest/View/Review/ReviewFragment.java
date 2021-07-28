package com.example.mototest.View.Review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.mototest.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;


public class ReviewFragment extends Fragment {
    private View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_review, container, false);
        tabLayout=(TabLayout)v.findViewById(R.id.tablayout);
        viewPager=(ViewPager) v.findViewById(R.id.vp);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerReviewAdapter viewPagerReviewAdapter=new ViewPagerReviewAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerReviewAdapter.addFragment(new LuatFragment(),"Luật Giao Thông");
        viewPagerReviewAdapter.addFragment(new BienbaoFragment(),"Biển Báo");
        viewPagerReviewAdapter.addFragment(new XuphatFragment(),"Mức xử phạt");
        viewPager.setAdapter(viewPagerReviewAdapter);
        return v;
    }


}