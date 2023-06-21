package com.example.learningverbs.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.ActivityCreateAccountBinding;
import com.example.learningverbs.databinding.ActivityLoginBinding;
import com.example.learningverbs.login.viewmodel.LoginViewModel;
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
        setContentView(R.layout.activity_login);
    }
}