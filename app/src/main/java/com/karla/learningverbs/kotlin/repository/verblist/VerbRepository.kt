package com.karla.learningverbs.kotlin.repository.verblist

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.karla.learningverbs.kotlin.utils.firebase.CustomEventListener
import com.karla.learningverbs.kotlin.utils.firebase.CustomListEventListener
import com.karla.learningverbs.model.Verb
import com.karla.learningverbs.utils.LearningApplication
class VerbRepository {

    val database = FirebaseDatabase.getInstance()
    private var dataBaseBookReference = database.reference
    private var mPostReference = dataBaseBookReference!!.child(LearningApplication.getInstance().applicationName)

    fun getSearchVerb(verbName: String?, postListener: CustomListEventListener<Verb>?) {
        val paramName = if (verbName != null && !verbName.isEmpty()) verbName else ""
        mPostReference!!.child("VerbList").orderByChild("verbSpanishPresent").startAt(paramName)
            .endAt(paramName + "\uf8ff").addValueEventListener(
                postListener!!
            )
    }

    fun getListVerbs(postListener: CustomListEventListener<Verb>?) {
        mPostReference!!.child("VerbList").addValueEventListener(postListener!!)
    }

    fun getListVerbsDataBase(postListener: CustomListEventListener<Verb>?) {
        mPostReference!!.child("MainVerbs").addValueEventListener(postListener!!)
    }

    fun getFavoritesListUser(userId: String?, postListener: CustomListEventListener<Verb>?) {
        mPostReference!!.child("Favorites").child(userId!!).addValueEventListener(postListener!!)
    }

    fun addVerbFavoriteUser(idUser: String?, verbId: String?, verb: Verb?) {
        mPostReference!!.child("Favorites").child(idUser!!).child(verbId!!).setValue(verb)
    }

    fun getUserVerbFavorite(
        idUser: String?,
        verbId: String?,
        postListener: CustomEventListener<Verb>?
    ) {
        mPostReference!!.child("Favorites").child(idUser!!).orderByChild("verbId").equalTo(verbId)
            .addValueEventListener(
                postListener!!
            )
    }

    fun removeUserVerbFavorite(
        iduser: String?,
        verbid: String?,
        onSuccessListener: OnSuccessListener<Void>?
    ) {
        mPostReference!!.child("Favorites").child(iduser!!).child(verbid!!).removeValue()
            .addOnSuccessListener(onSuccessListener!!)
    }
}