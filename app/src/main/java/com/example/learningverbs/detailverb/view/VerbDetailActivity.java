package com.example.learningverbs.detailverb.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.R;
import com.example.learningverbs.adapter.Title;
import com.example.learningverbs.adapter.ViewPagerAdapter;
import com.example.learningverbs.databinding.ActivityVerbDetailBinding;
import com.example.learningverbs.detailverb.viewmodel.VerbDetailViewModel;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseActivity;
import com.example.learningverbs.utils.constants.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class VerbDetailActivity extends BaseActivity<ActivityVerbDetailBinding, VerbDetailViewModel> {
    ArrayList<Title> arrayTitleViewPager = new ArrayList();
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
        readExtra();

        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        arrayTitleViewPager.add(new Title("Presente"));
        arrayTitleViewPager.add(new Title("Pasado"));
        arrayTitleViewPager.add(new Title("Futuro"));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), arrayTitleViewPager);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void readExtra() {
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.VERB)) {
            Verb verbDetail = (Verb) getIntent().getExtras().getSerializable(Constants.VERB);
            binding.verbSpanish.setText(verbDetail.getVerbSpanishPresent());
            binding.verbEnglish.setText(verbDetail.getVerbEnglishPresent());

            if (verbDetail.getRegular()) {
                binding.txtIsRegular.setText("Regular");
            } else {
                binding.txtIsRegular.setText("Irregular");
            }
        }else{
            Log.e("NO Contiene llave", "NO Contiene la Key");

        }

    }

}