package com.karla.learningverbs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnSuccessListener
import com.karla.learningverbs.kotlin.repository.userrepository.UserRepository
import com.karla.learningverbs.kotlin.repository.verblist.VerbRepository
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel
import com.karla.learningverbs.kotlin.utils.firebase.CustomEventListener
import com.karla.learningverbs.model.Verb

class VerbDetailViewModel: BaseViewModel() {
    private val isVerbFavorite = MutableLiveData<Boolean>()
    private val verbDetailRepository: VerbRepository = VerbRepository()
    private val userRepository: UserRepository = UserRepository()

    fun responseVerbFavoriteUser(verbId: String?) {
        verbDetailRepository.getUserVerbFavorite(
            userRepository.getUserId(),
            verbId, object : CustomEventListener<Verb>(Verb::class.java) {

                override fun onSuccess(response: Verb) {
                    isVerbFavorite.value = response != null
                }

                override fun onFailed(throwable: Throwable?) {
                    isVerbFavorite.value = false
                }

                override fun showLoaging() {
                    loading.value = true
                }

                override fun hideLoading() {
                    loading.value = false
                }
            })
    }

    fun deleteelement(verbid: String?) {
        verbDetailRepository!!.removeUserVerbFavorite(userRepository!!.getUserId(), verbid,
            OnSuccessListener<Void> {

            })
    }

    fun fillDb(verb: Verb?, verbId: String?) {
        verbDetailRepository!!.addVerbFavoriteUser(userRepository!!.getUserId(), verbId, verb)
    }

    fun getIsFavoriteVerb(): LiveData<Boolean> {
        return isVerbFavorite
    }
}