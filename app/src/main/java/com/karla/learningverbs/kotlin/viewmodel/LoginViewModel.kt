package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.karla.learningverbs.kotlin.repository.UserRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomOnCompleteListener

class LoginViewModel: BaseViewModel() {

    private val isLogin = MutableLiveData<Boolean>()
    private val repository: UserRepository = UserRepository()

    fun doLogin(email: String, password: String) {
        repository.login(object : CustomOnCompleteListener<AuthResult?>() {
            override fun onSuccess(task: AuthResult?) {
                isLogin.postValue(true)
            }

            override fun onFailure(throwable: Throwable?) {
                throwable?.let {
                    msgError.postValue(it.message)

                }
            }

            override fun showLoaging() {
                loading.postValue(true)
            }

            override fun hideLoading() {
                loading.postValue(false)
            }
        }, email, password)
    }

    fun getFireBaseUser(): LiveData<Boolean?> {
        return isLogin
    }

}