package com.example.learningverbs.detailverb.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.R;
import com.example.learningverbs.adapter.Title;
import com.example.learningverbs.adapter.ViewPagerAdapter;
import com.example.learningverbs.databinding.ActivityVerbDetailBinding;
import com.example.learningverbs.detailverb.viewmodel.VerbDetailViewModel;
import com.example.learningverbs.utils.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class VerbDetailActivity extends BaseActivity<ActivityVerbDetailBinding, VerbDetailViewModel> {
    ArrayList<Title> arrayImagenes = new ArrayList();
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    @Override
    protected VerbDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(VerbDetailViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityVerbDetailBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityVerbDetailBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        arrayImagenes.add(new Title("Presente"));
        arrayImagenes.add(new Title("Pasado"));
        arrayImagenes.add(new Title("Futuro"));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),arrayImagenes);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }
}