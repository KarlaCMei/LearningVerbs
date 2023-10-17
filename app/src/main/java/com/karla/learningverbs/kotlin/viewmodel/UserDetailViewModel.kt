package com.karla.learningverbs.kotlin.viewmodel

import android.net.Uri
import com.karla.learningverbs.R
import com.karla.learningverbs.kotlin.repository.userrepository.UserRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomOnCompleteListener
import com.karla.learningverbs.utils.LearningApplication
import com.karla.learningverbs.utils.Tools

class UserDetailViewModel: BaseViewModel()  {
    private val userRepository: UserRepository = UserRepository()

    fun updateProfile(urlImage: String?) {
        userRepository.updateProfile(userRepository.getUserId() ?:"", urlImage ?:"", object : CustomOnCompleteListener<Uri>() {

            override fun onSuccess(task: Uri) {
                uploadProfile(task.toString() + task.toString())
            }

            override fun onFailure(throwable: Throwable?) {
                    msgError.postValue(throwable!!.message)
                }

                override fun showLoaging() {
                    loading.postValue(true)
                }

                override fun hideLoading() {
                    loading.postValue(false)
                }
            })
    }

    fun uploadProfile(url: String?) {
        userRepository.uploadProfile(url, object : CustomOnCompleteListener<Void>() {

            override fun onSuccess(task: Void) {
                Tools.showToastMessage(
                    LearningApplication.getMyApplicationContext()
                        .getString(R.string.msg_image_updated)
                )            }

            override fun onFailure(throwable: Throwable?) {
                msgError.postValue(throwable!!.message)
            }

            override fun showLoaging() {
                loading.postValue(true)
            }

            override fun hideLoading() {
                loading.postValue(false)
            }
        })
    }
}