package com.karla.learningverbs.view.profile;

import static com.karla.learningverbs.utils.StringUtils.validateEmail;
import static com.karla.learningverbs.utils.StringUtils.validatePassword;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;

import com.karla.learningverbs.R;
import com.karla.learningverbs.databinding.ActivityLoginBinding;
import com.karla.learningverbs.view.home.HomeActivity;
import com.karla.learningverbs.viewmodel.LoginViewModel;
import com.karla.learningverbs.utils.base.BaseActivity;
import com.karla.learningverbs.utils.Tools;
import com.karla.learningverbs.utils.constants.Constants;

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

        binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, GetPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateEmail(binding.editTextEmail.getText().toString())) {
                    binding.editTextEmail.setError(null);
                    if (validatePassword(binding.editTextPassword.getText().toString())) {
                        binding.editTextPassword.setError(null);
                        viewModel.doLogin(binding.editTextEmail.getText().toString(), binding.editTextPassword.getText().toString());

                    } else {
                        binding.editTextPassword.setError(getString(R.string.msg_add_password));
                    }
                } else {
                    binding.editTextEmail.setError(getString(R.string.msg_email_not_valid));
                }
            }
        });

        createUserWithEmail();
        configSwitchSaveUser();
        getSaveData();

    }

    private void createUserWithEmail() {
        viewModel.getFireBaseUser().observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        });

    }

    private void getSaveData() {
        String userName = Tools.getStringPreference(Constants.USERNAME_SAVE);
        if (!userName.isEmpty()) {
            binding.editTextEmail.setText(userName);
            binding.checkBoxRememberUser.setChecked(true);
        }
    }


    private void configSwitchSaveUser() {
        binding.checkBoxRememberUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String userName = binding.editTextEmail.getText().toString();
                if (!userName.isEmpty()) {
                    if (compoundButton.isChecked()) {
                        Tools.setStringPreference(Constants.USERNAME_SAVE, userName);
                    } else {
                        Tools.setStringPreference(Constants.USERNAME_SAVE, "");
                    }
                } else {
                    binding.checkBoxRememberUser.setChecked(false);
                    Tools.showSnackMessage(binding.CoordinatorLayoutContainerLogin, getString(R.string.lbl_email));
                }

            }
        });
    }


}