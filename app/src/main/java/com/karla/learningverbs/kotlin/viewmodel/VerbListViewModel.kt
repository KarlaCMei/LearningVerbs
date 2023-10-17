package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.kotlin.repository.verblist.VerbRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomListEventListener
import com.karla.learningverbs.model.Verb

class VerbListViewModel: BaseViewModel() {

    private val getResultListVerbs = MutableLiveData<ArrayList<Verb?>>()
    private var repository: VerbRepository = VerbRepository()

    fun getSearchVerb(verbName: String?) {
        val searchName = verbName ?: ""
        repository.getSearchVerb(searchName, object : CustomListEventListener<Verb>(Verb::class.java) {
                override fun onSuccess(response: ArrayList<Verb?>?) {
                    //getVerbAdd.setValue(true);
                    getResultListVerbs.postValue(response!!)
                }

                override fun onFailed(throwable: Throwable?) {
                    //getResultListVerbs.postValue(null)
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

    fun getListResultsVerbs(): LiveData<ArrayList<Verb?>>{
        return getResultListVerbs
    }

    /*public LiveData<Boolean> getResultVerbAdd() {
        return getVerbAdd;
    }*/
}
