package com.karla.learningverbs.kotlin.utils.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    @JvmField
    var loading = MutableLiveData<Boolean>()
    @JvmField
    var msgError = MutableLiveData<String>()
}