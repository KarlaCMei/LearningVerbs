package com.example.learningverbs.splash.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.learningverbs.databinding.ActivitySplashBinding;
import com.example.learningverbs.home.HomeActivity;
import com.example.learningverbs.login.view.LoginActivity;
import com.example.learningverbs.splash.viewmodel.SplashViewModel;
import com.example.learningverbs.utils.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected SplashViewModel createViewModel() {
        return new ViewModelProvider(this).get(SplashViewModel.class);
    }

    @NonNull
    @Override
    protected ActivitySplashBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivitySplashBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewModel.getIsLogin().observe(SplashActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLogin) {
                if(isLogin){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        });



        binding.splashLottie.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {


            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                viewModel.isLogin();

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });


    }

}