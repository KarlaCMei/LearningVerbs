package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.R
import com.karla.learningverbs.kotlin.repository.UserRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomOnCompleteListener
import com.karla.learningverbs.utils.LearningApplication

class GetPasswordViewModel: BaseViewModel() {

    private val isSendEmail = MutableLiveData<String>()
    private val repository: UserRepository = UserRepository()


    fun getRecoveryPassword(email: String?) {
        repository.recoverPassword(email, object : CustomOnCompleteListener<Void?>() {

            override fun onSuccess(task: Void?) {
                isSendEmail.postValue(
                    LearningApplication.getInstance().getString(R.string.msg_email_send)
                )            }

            override fun onFailure(throwable: Throwable?) {
                msgError.postValue(
                    LearningApplication.getInstance().getString(R.string.msg_no_email_send)
                )
            }

            override fun showLoaging() {
                loading.postValue(true)
            }

            override fun hideLoading() {
                loading.postValue(false)
            }
        })
    }

    fun sendEmail(): LiveData<String?> {
        return isSendEmail
    }
}