package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.kotlin.repository.UserRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.repository.userrepository.UserRepository2

class SplashViewModel : BaseViewModel() {

    private var isLoginStatus = MutableLiveData<Boolean>()
    private var splashScreenRepository: UserRepository = UserRepository()

    fun isLogin() {
        isLoginStatus.value = splashScreenRepository.isLogin()
    }

    fun getIsLogin(): LiveData<Boolean> {
        return isLoginStatus
    }
}