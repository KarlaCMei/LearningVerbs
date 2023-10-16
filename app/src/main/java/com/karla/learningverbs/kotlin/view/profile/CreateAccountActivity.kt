package com.karla.learningverbs.kotlin.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.databinding.ActivityCreateAccountBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.viewmodel.CreateAccountViewModel
import com.karla.learningverbs.view.profile.SignUpActivity

class CreateAccountActivity : BaseActivity<ActivityCreateAccountBinding, CreateAccountViewModel>() {

    override fun createViewModel(): CreateAccountViewModel {
        return ViewModelProvider(this).get(CreateAccountViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityCreateAccountBinding {
        return ActivityCreateAccountBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding!!.btnLogin.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    LoginActivity::class.java
                )
            )
        }
        binding!!.btnCreateAcount.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    SignUpActivity::class.java
                )
            )
        }
    }
}