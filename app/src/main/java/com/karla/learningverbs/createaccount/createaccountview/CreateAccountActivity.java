package com.karla.learningverbs.createaccount.createaccountview;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.karla.learningverbs.createaccount.createaccountviewmodel.CreateAccountViewModel;
import com.karla.learningverbs.databinding.ActivityCreateAccountBinding;
import com.karla.learningverbs.login.view.LoginActivity;
import com.karla.learningverbs.signup.view.SignUpActivity;
import com.karla.learningverbs.utils.BaseActivity;

public class CreateAccountActivity extends BaseActivity<ActivityCreateAccountBinding, CreateAccountViewModel> {

    @Override
    protected CreateAccountViewModel createViewModel() {
        return new ViewModelProvider(this).get(CreateAccountViewModel.class);
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
                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));

            }
        });

        binding.btnCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this, SignUpActivity.class));

            }
        });

    }
}