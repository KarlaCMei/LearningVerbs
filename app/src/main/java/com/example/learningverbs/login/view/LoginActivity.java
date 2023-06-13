package com.example.learningverbs.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.ActivityLoginBinding;
import com.example.learningverbs.databinding.ActivitySplashBinding;
import com.example.learningverbs.home.HomeActivity;
import com.example.learningverbs.login.viewmodel.LoginViewModel;
import com.example.learningverbs.signup.view.SignUpActivity;
import com.example.learningverbs.splash.view.SplashActivity;
import com.example.learningverbs.splash.viewmodel.SplashViewModel;
import com.example.learningverbs.utils.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected LoginViewModel createViewModel() {
        return new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityLoginBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityLoginBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));

            }
        });

    }
}