package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karla.learningverbs.kotlin.repository.userrepository.UserRepository
import com.karla.learningverbs.kotlin.repository.verblist.VerbRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomListEventListener
import com.karla.learningverbs.model.Verb

class FavoritesViewModel: BaseViewModel()  {
    private val getResultListFavoriteVerbs = MutableLiveData<ArrayList<Verb?>>()
    private var favoritesRepository: VerbRepository = VerbRepository()
    private var userRepository2: UserRepository = UserRepository()

    fun getFavoriteVerbListElement() {
        favoritesRepository.getFavoritesListUser(
            userRepository2.getUserId(),
            object : CustomListEventListener<Verb>(Verb::class.java) {
                override fun onSuccess(response: ArrayList<Verb?>?) {
                    getResultListFavoriteVerbs.postValue(response!!)
                }

                override fun onFailed(throwable: Throwable?) {
                    //getResultListFavoriteVerbs.postValue(null)
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

    fun getListResultsFavoriteVerbs(): LiveData<ArrayList<Verb?>> {
        return getResultListFavoriteVerbs
    }

}