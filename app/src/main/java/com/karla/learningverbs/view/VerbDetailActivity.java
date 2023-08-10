package com.karla.learningverbs.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.karla.learningverbs.R;
import com.karla.learningverbs.adapter.ViewPagerAdapter;
import com.karla.learningverbs.databinding.ActivityVerbDetailBinding;
import com.karla.learningverbs.viewmodel.VerbDetailViewModel;
import com.karla.learningverbs.model.ExampleVerb;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.BaseActivity;
import com.karla.learningverbs.utils.constants.Constants;

import java.util.ArrayList;

public class VerbDetailActivity extends BaseActivity<ActivityVerbDetailBinding, VerbDetailViewModel> {
    ArrayList<ExampleVerb> arrayExampleVerb = new ArrayList();
    ViewPagerAdapter viewPagerAdapter;
    private Verb verbDetail;


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
        observers();
        readExtra();

        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnFavoriteVerb.setOnClickListener(view -> {
            if (Boolean.TRUE.equals(viewModel.getIsFavoriteVerb().getValue())) {
                deleteElement();
            } else {
                viewModel.fillDb(verbDetail, verbDetail.getVerbId());
            }
        });


    }

    private void readExtra() {
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.VERB)) {
            verbDetail = (Verb) getIntent().getExtras().getSerializable(Constants.VERB);
            binding.verbSpanish.setText(verbDetail.getVerbSpanishPresent());
            binding.verbEnglish.setText(verbDetail.getVerbEnglishPresent());
            Glide.with(binding.imgVerb.getContext()).load(verbDetail.getImage()).into(binding.imgVerb);

            viewModel.responseVerbFavoriteUser(verbDetail.getVerbId());

            arrayExampleVerb.add(verbDetail.getExampleVerbPresent());
            arrayExampleVerb.add(verbDetail.getExampleVerbPast());
            arrayExampleVerb.add(verbDetail.getExampleVerbFuture());

            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), arrayExampleVerb);
            binding.pager.setAdapter(viewPagerAdapter);
            binding.tabLayout.setupWithViewPager(binding.pager);

            if (verbDetail.getRegular()) {
                binding.txtIsRegular.setText(R.string.msg_regular);
            } else {
                binding.txtIsRegular.setText(R.string.msg_irregular);
            }
        }
    }

    private void observers() {
        viewModel.getIsFavoriteVerb().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.btnFavoriteVerb.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                } else {
                    binding.btnFavoriteVerb.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.teal_800)));
                }
            }
        });
    }

    private void deleteElement() {
        viewModel.deleteelement(verbDetail.getVerbId());
    }

}