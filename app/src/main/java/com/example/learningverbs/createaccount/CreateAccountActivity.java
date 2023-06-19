package com.example.learningverbs.createaccount;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.databinding.ActivityCreateAccountBinding;
import com.example.learningverbs.login.viewmodel.LoginViewModel;
import com.example.learningverbs.signup.view.SignUpActivity;
import com.example.learningverbs.utils.BaseActivity;

public class CreateAccountActivity extends BaseActivity<ActivityCreateAccountBinding, LoginViewModel> {

    @Override
    protected LoginViewModel createViewModel() {
        return new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityCreateAccountBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityCreateAccountBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this, SignUpActivity.class));

            }
        });

    }
}