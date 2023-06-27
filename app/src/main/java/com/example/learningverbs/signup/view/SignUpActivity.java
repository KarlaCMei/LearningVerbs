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
import com.example.learningverbs.databinding.ActivityCreateAccountBinding;
import com.example.learningverbs.databinding.ActivitySignUpBinding;
import com.example.learningverbs.home.HomeActivity;
import com.example.learningverbs.login.viewmodel.LoginViewModel;
import com.example.learningverbs.signup.viewmodel.SignUpViewModel;
import com.example.learningverbs.splash.viewmodel.SplashViewModel;
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

                    if (validatePassword(binding.editTextPassword.getText().toString()) ) {
                        binding.editTextPassword.setError(null);

                        if(validateSamePassword(binding.editTextPassword.getText().toString(), binding.editTextVerifyPassword.getText().toString())){
                            binding.editTextVerifyPassword.setError("La contraseña no coincide");
                        }else{
                            viewModel.singUp(binding.editTextEmail.getText().toString(), binding.editTextPassword.getText().toString());
                        }
                    } else {
                        binding.editTextPassword.setError("La contraseña tiene que ser mayor a 6 digitos");
                    }

                } else {
                    binding.editTextEmail.setError("Email no valido");
                }
            }
        });

        viewModel.getfirebaseUser().observe(SignUpActivity.this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                Log.e("Email", firebaseUser.getEmail());

                startActivity(new Intent(SignUpActivity.this, HomeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

    }
}