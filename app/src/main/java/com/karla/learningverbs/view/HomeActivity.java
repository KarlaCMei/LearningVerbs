package com.karla.learningverbs.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
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
import com.karla.learningverbs.R;
import com.karla.learningverbs.databinding.ActivityHomeBinding;
import com.karla.learningverbs.viewmodel.HomeViewModel;
import com.karla.learningverbs.utils.BaseActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
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
        getUrlUserImage();
        observer();

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

    private void getUrlUserImage() {
        viewModel.responseImageUser();
    }

    private void observer() {
        viewModel.getUrlImage().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                Glide.with(HomeActivity.this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.customToolbar.imgProfile);
            }
        });
    }


}