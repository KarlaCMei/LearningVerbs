package com.karla.learningverbs.kotlin.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivityHomeBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.view.profile.UserDetailActivity
import com.karla.learningverbs.kotlin.viewmodel.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater!!)
    }




    override fun onResume() {
        super.onResume()
        configToolbar()
        urlUserImage
        observer()

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )
            .build()

        val navController = findNavController(this, R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController( navController, appBarConfiguration)
        setupWithNavController(binding!!.navView, navController)
        binding!!.customToolbar.imgProfile.setOnClickListener {
            val intent = Intent(this@HomeActivity, UserDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configToolbar() {
        setSupportActionBar(binding!!.customToolbar.appBar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private val urlUserImage: Unit
        private get() {
            viewModel!!.responseImageUser()
        }

    private fun observer() {
        viewModel.getUrlImage().observe(this, Observer { uri ->
            if (uri != null) {
                Glide.with(this@HomeActivity)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding?.customToolbar?.imgProfile ?: return@Observer)
            } else {
                Glide.with(this@HomeActivity)
                    .load(R.drawable.ic_icon_user)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding?.customToolbar?.imgProfile ?: return@Observer)
            }
        })
    }

}