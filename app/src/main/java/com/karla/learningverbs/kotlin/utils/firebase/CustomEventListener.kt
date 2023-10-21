package com.karla.learningverbs.kotlin.utils.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.karla.learningverbs.R
import com.karla.learningverbs.utils.LearningApplication2

abstract class CustomEventListener<T>(GenericTypeIndicator: Class<T>) :
    ValueEventListener {
    private val customClass: Class<T>

    init {
        showLoaging()
        customClass = GenericTypeIndicator
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        hideLoading()
        var currentCustomClass: T? = null
        for (postSnapshot in snapshot.children) {
            currentCustomClass = postSnapshot.getValue(customClass)
        }
        if (currentCustomClass != null) {
            onSuccess(currentCustomClass)
        } else {
            onFailed(Throwable(LearningApplication2.getInstance().getString(R.string.msg_throwable)))
        }
    }

    override fun onCancelled(error: DatabaseError) {
        hideLoading()
        onFailed(Throwable(error.message))
    }

    abstract fun onSuccess(response: T)
    abstract fun onFailed(throwable: Throwable?)
    abstract fun showLoaging()
    abstract fun hideLoading()
}