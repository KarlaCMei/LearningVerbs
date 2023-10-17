package com.karla.learningverbs.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivitySignUpBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.view.home.HomeActivity
import com.karla.learningverbs.kotlin.viewmodel.SignUpViewModel
import com.karla.learningverbs.utils.StringUtils.validateEmail
import com.karla.learningverbs.utils.StringUtils.validatePassword
import com.karla.learningverbs.utils.StringUtils.validateSamePassword

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override fun createViewModel(): SignUpViewModel {
        return ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater!!)
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
            if (aBoolean!!) {
                startActivity(
                    Intent(this@SignUpActivity, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        })
    }
}
