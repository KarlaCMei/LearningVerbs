package com.karla.learningverbs.kotlin.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.kotlin.repository.userrepository.UserRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel

class HomeViewModel: BaseViewModel() {
    private var userRepository: UserRepository = UserRepository()
    private var urlImage = MutableLiveData<Uri>()

    fun responseImageUser() {
        urlImage.value = userRepository.getUserUrlImage()
    }

    fun getUrlImage(): LiveData<Uri?> {
        return urlImage
    }
}