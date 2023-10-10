package com.karla.learningverbs.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivitySignUpBinding
import com.karla.learningverbs.utils.StringUtils.validateEmail
import com.karla.learningverbs.utils.StringUtils.validatePassword
import com.karla.learningverbs.utils.StringUtils.validateSamePassword
import com.karla.learningverbs.utils.base.BaseActivity2
import com.karla.learningverbs.view.home.HomeActivity2
import com.karla.learningverbs.viewmodel.SignUpViewModel

class SignUpActivity2 : BaseActivity2<ActivitySignUpBinding, SignUpViewModel>() {

    override fun createViewModel(): SignUpViewModel {
        return ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSignUp.setOnClickListener(View.OnClickListener {
            if (validateEmail(binding.editTextEmail.text.toString())) {
                binding.editTextEmail.error = null

                if (validatePassword(binding.editTextPassword.text.toString())) {
                    binding.editTextPassword.error = null

                    if (validateSamePassword(
                            binding.editTextPassword.text.toString(),
                            binding.editTextVerifyPassword.text.toString()
                        )
                    ) {
                        binding.editTextVerifyPassword.error = getString(R.string.msg_verify_password)
                    } else {
                        viewModel.singUp(
                            binding.editTextEmail.text.toString(),
                            binding.editTextPassword.text.toString()
                        )
                    }
                } else {
                    binding.editTextPassword.error = getString(R.string.msg_add_password)
                }

            } else {
                binding.editTextEmail.error = getString(R.string.msg_email_not_valid)
            }
        })

        viewModel.getfirebaseUser().observe(this, Observer { aBoolean ->
            if (aBoolean) {
                startActivity(
                    Intent(this@SignUpActivity2, HomeActivity2::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        })
    }
}