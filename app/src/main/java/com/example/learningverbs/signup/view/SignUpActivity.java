package com.example.learningverbs.signup.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.ActivityCreateAccountBinding;
import com.example.learningverbs.databinding.ActivitySignUpBinding;
import com.example.learningverbs.login.viewmodel.LoginViewModel;
import com.example.learningverbs.signup.viewmodel.SignUpViewModel;
import com.example.learningverbs.splash.viewmodel.SplashViewModel;
import com.example.learningverbs.utils.BaseActivity;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel> {

    @Override
    protected SignUpViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignUpViewModel.class);
    }

    @NonNull
    @Override
    protected ActivitySignUpBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivitySignUpBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}