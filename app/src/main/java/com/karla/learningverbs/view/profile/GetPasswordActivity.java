package com.karla.learningverbs.view.profile;

import static com.karla.learningverbs.utils.StringUtils.validateEmail;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.karla.learningverbs.R;
import com.karla.learningverbs.databinding.ActivityGetPasswordBinding;
import com.karla.learningverbs.viewmodel.GetPasswordViewModel;
import com.karla.learningverbs.utils.base.BaseActivity;

public class GetPasswordActivity extends BaseActivity<ActivityGetPasswordBinding, GetPasswordViewModel> {

    @Override
    protected GetPasswordViewModel createViewModel() {
        return new ViewModelProvider(this).get(GetPasswordViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityGetPasswordBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityGetPasswordBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.btnGetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    public void validate() {
        String email = binding.editTextEmail.getText().toString().trim();
        if (email.isEmpty() || !validateEmail(binding.editTextEmail.getText().toString())) {
            binding.editTextEmail.setError(getString(R.string.msg_no_email_send));
        } else {
            binding.editTextEmail.setError(null);
            viewModel.getRecoveryPassword(binding.editTextEmail.getText().toString());
        }

        recoverPassword();
    }

    private void recoverPassword() {
        viewModel.sendEmail().observe(GetPasswordActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                if (string != null) {
                    Toast.makeText(GetPasswordActivity.this, string, Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

}