package com.karla.learningverbs.kotlin.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivityGetPasswordBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.viewmodel.GetPasswordViewModel
import com.karla.learningverbs.kotlin.utils.StringUtils

class GetPasswordActivity : BaseActivity<ActivityGetPasswordBinding, GetPasswordViewModel>() {

    override fun createViewModel(): GetPasswordViewModel {
        return ViewModelProvider(this).get(GetPasswordViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityGetPasswordBinding{
        return ActivityGetPasswordBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding!!.btnGetPassword.setOnClickListener { validate() }
    }

    fun validate() {
        val email = binding!!.editTextEmail.text.toString().trim { it <= ' ' }
        if (email.isEmpty() || !StringUtils.validateEmail(binding!!.editTextEmail.text.toString())) {
            binding!!.editTextEmail.error = getString(R.string.msg_no_email_send)
        } else {
            binding!!.editTextEmail.error = null
            viewModel!!.getRecoveryPassword(binding!!.editTextEmail.text.toString())
        }
        recoverPassword()
    }

    private fun recoverPassword() {
        viewModel?.sendEmail()?.observe(this@GetPasswordActivity, Observer { string ->
            if (string != null) {
                Toast.makeText(this@GetPasswordActivity, string, Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        })
    }

}