package com.karla.learningverbs.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.databinding.ActivityCreateAccountBinding
import com.karla.learningverbs.utils.base.BaseActivity2
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel

class CreateAccountActivity2 : BaseActivity2<ActivityCreateAccountBinding?, BaseViewModel?>() {
    override fun createViewModel(): BaseViewModel {
        return ViewModelProvider(this).get(BaseViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivityCreateAccountBinding {
        return ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding!!.btnLogin.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity2,
                    LoginActivity2::class.java
                )
            )
        }
        binding!!.btnCreateAcount.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity2,
                    SignUpActivity2::class.java
                )
            )
        }
    }
}