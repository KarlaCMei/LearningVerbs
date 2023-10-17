package com.karla.learningverbs.kotlin.utils.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
abstract class CustomListEventListener<T>(GenericTypeIndicator: Class<T>) :
    ValueEventListener {
    val verbList = ArrayList<T?>()
    private val customClass: Class<T>

    init {
        showLoaging()
        customClass = GenericTypeIndicator
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        hideLoading()
        verbList.clear()
        for (postSnapshot in snapshot.children) {
            verbList.add(postSnapshot.getValue(customClass))
        }
        if (verbList.isEmpty()) {
            onFailed(Throwable("Error al obtener los datos"))
        } else {
            onSuccess(verbList)
        }
    }

    override fun onCancelled(error: DatabaseError) {
        hideLoading()
        onFailed(Throwable(error.message))
    }

    abstract fun onSuccess(response: ArrayList<T?>?)
    abstract fun onFailed(throwable: Throwable?)
    abstract fun showLoaging()
    abstract fun hideLoading()
}