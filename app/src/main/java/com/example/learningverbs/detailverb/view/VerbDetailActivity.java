package com.example.learningverbs.detailverb.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.learningverbs.R;
import com.example.learningverbs.adapter.Title;
import com.example.learningverbs.adapter.ViewPagerAdapter;
import com.example.learningverbs.databinding.ActivityVerbDetailBinding;
import com.example.learningverbs.detailverb.viewmodel.VerbDetailViewModel;
import com.example.learningverbs.model.ExampleVerb;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseActivity;
import com.example.learningverbs.utils.constants.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class VerbDetailActivity extends BaseActivity<ActivityVerbDetailBinding, VerbDetailViewModel> {
    ArrayList<ExampleVerb> arrayExampleVerb = new ArrayList();
    ViewPagerAdapter viewPagerAdapter;

    private Verb verbDetail;

    private boolean isFavorite;

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
            if(Boolean.TRUE.equals(viewModel.getIsFavoriteVerb().getValue())){
                deleteElement();
            }else{
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
                binding.txtIsRegular.setText("Regular");
            } else {
                binding.txtIsRegular.setText("Irregular");
            }
        }else{
            Log.e("NO Contiene llave", "NO Contiene la Key");
        }
    }

    private void observers(){
        viewModel.getIsFavoriteVerb().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Log.e("Response aBoolean", "True");
                    binding.btnFavoriteVerb.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }else{
                    Log.e("Response aBoolean", "False");
                   binding.btnFavoriteVerb.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.teal_800)));
                }
            }
        });
    }

    private void deleteElement() {
        viewModel.deleteelement(verbDetail.getVerbId());
    }

}