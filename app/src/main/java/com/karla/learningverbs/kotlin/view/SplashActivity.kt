package com.karla.learningverbs.kotlin.view

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.databinding.ActivitySplashBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.view.home.HomeActivity
import com.karla.learningverbs.kotlin.view.profile.CreateAccountActivity
import com.karla.learningverbs.kotlin.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {


    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater!!)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getIsLogin().observe(this, Observer { isLogin ->
            if (isLogin) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, CreateAccountActivity::class.java))
            }
            finish()
        })


        binding.splashLottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                // Implementación del método si es necesario
            }

            override fun onAnimationEnd(animator: Animator) {
                viewModel.isLogin()
            }

            override fun onAnimationCancel(animator: Animator) {
                // Implementación del método si es necesario
            }

            override fun onAnimationRepeat(animator: Animator) {
                // Implementación del método si es necesario
            }
        })

    }
}