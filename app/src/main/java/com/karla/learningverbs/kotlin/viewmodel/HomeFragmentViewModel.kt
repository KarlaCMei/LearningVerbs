package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.kotlin.repository.verblist.VerbRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomListEventListener
import com.karla.learningverbs.model.Verb
import java.util.*
import kotlin.collections.ArrayList

class HomeFragmentViewModel: BaseViewModel() {
    private val getResultListVerbs = MutableLiveData<ArrayList<Verb?>>()
    private val verbRandom = MutableLiveData<Verb>()
    private val verbRepository : VerbRepository =  VerbRepository()


    fun getListElement() {
        verbRepository.getListVerbsDataBase(object : CustomListEventListener<Verb>(Verb::class.java) {

            override fun onSuccess(response: ArrayList<Verb?>?) {
                getResultListVerbs.postValue(response!!)
            }

            override fun onFailed(throwable: Throwable?) {
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

    fun getListResultsVerbs(): LiveData<ArrayList<Verb?>> {
        return getResultListVerbs
    }


    fun getListVerbs() {
        verbRepository.getListVerbs(object : CustomListEventListener<Verb>(Verb::class.java) {
            override fun onSuccess(response: ArrayList<Verb?>?) {
                val random = Random()
                val randomIndex = random.nextInt(response!!.size)
                val randomElement = response[randomIndex]
                verbRandom.setValue(randomElement!!)
            }

            override fun onFailed(throwable: Throwable?) {
                msgError.postValue(throwable!!.message)
            }

            override fun showLoaging() {}
            override fun hideLoading() {}
        })
    }

    fun getElementRandom(): LiveData<Verb> {
        return verbRandom
    }
}