package com.karla.learningverbs.kotlin.utils.firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

abstract class CustomOnCompleteListener<T> : OnCompleteListener<T> {
    init {
        showLoaging()
    }

    override fun onComplete(task: Task<T>) {
        hideLoading()
        if (task != null && task.isSuccessful) {
            onSuccess(task.result)
        } else {
            onFailure(task.exception)
        }
    }

    abstract fun onSuccess(task: T)
    abstract fun onFailure(throwable: Throwable?)
    abstract fun showLoaging()
    abstract fun hideLoading()
}