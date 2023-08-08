package com.example.learningverbs.home.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.learningverbs.R;
import com.example.learningverbs.databinding.ActivityGetPasswordBinding;
import com.example.learningverbs.databinding.ActivityHomeBinding;
import com.example.learningverbs.home.ui.home.view.HomeFragment;
import com.example.learningverbs.home.viewmodel.HomeViewModel;
import com.example.learningverbs.userdetail.view.UserDetailActivity;
import com.example.learningverbs.utils.BaseActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
    private Uri uri;

    @Override
    protected HomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityHomeBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityHomeBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configToolbar();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(HomeActivity.this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        binding.customToolbar.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    private void configToolbar() {
        setSupportActionBar(binding.customToolbar.appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getImageUser(){
        //Glide.with(this).load().apply(RequestOptions.circleCropTransform()).into(binding.customToolbar.imgProfile);
    }


}