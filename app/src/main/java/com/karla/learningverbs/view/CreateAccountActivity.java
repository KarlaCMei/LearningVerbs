package com.karla.learningverbs.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.karla.learningverbs.databinding.ActivityCreateAccountBinding;
import com.karla.learningverbs.utils.BaseActivity;
import com.karla.learningverbs.utils.BaseViewModel;

public class CreateAccountActivity extends BaseActivity<ActivityCreateAccountBinding, BaseViewModel> {

    @Override
    protected BaseViewModel createViewModel() {
        return new ViewModelProvider(this).get(BaseViewModel.class);
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