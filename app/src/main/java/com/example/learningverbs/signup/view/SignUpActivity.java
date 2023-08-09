package com.example.learningverbs.signup.view;

import static com.example.learningverbs.utils.StringUtils.validateEmail;
import static com.example.learningverbs.utils.StringUtils.validatePassword;
import static com.example.learningverbs.utils.StringUtils.validateSamePassword;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.ActivitySignUpBinding;
import com.example.learningverbs.home.view.HomeActivity;
import com.example.learningverbs.signup.viewmodel.SignUpViewModel;
import com.example.learningverbs.utils.BaseActivity;
import com.google.firebase.auth.FirebaseUser;

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

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validateEmail(binding.editTextEmail.getText().toString())) {
                    binding.editTextEmail.setError(null);

                    if (validatePassword(binding.editTextPassword.getText().toString())) {
                        binding.editTextPassword.setError(null);

                        if (validateSamePassword(binding.editTextPassword.getText().toString(), binding.editTextVerifyPassword.getText().toString())) {
                            binding.editTextVerifyPassword.setError(getString(R.string.msg_verify_password));
                        } else {
                            viewModel.singUp(binding.editTextEmail.getText().toString(), binding.editTextPassword.getText().toString());
                        }
                    } else {
                        binding.editTextPassword.setError(getString(R.string.msg_add_password));
                    }

                } else {
                    binding.editTextEmail.setError(getString(R.string.msg_email_not_valid));
                }
            }
        });

        viewModel.getfirebaseUser().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    startActivity(new Intent(SignUpActivity.this, HomeActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        });

    }
}