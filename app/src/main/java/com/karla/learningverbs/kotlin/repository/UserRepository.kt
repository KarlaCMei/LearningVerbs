package com.karla.learningverbs.kotlin.repository

import android.net.Uri
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.karla.learningverbs.kotlin.utils.firebase.CustomOnCompleteListener
import com.karla.learningverbs.utils.LearningApplication
import com.karla.learningverbs.utils.Tools

class UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun isLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun getUserId(): String? {
        return if (firebaseAuth.currentUser != null) firebaseAuth.currentUser!!.uid else ""
    }


    fun getUserUrlImage(): Uri? {
        return if (firebaseAuth.currentUser != null) firebaseAuth.currentUser!!.photoUrl else null
    }

    fun login(
        onCompleteListener: CustomOnCompleteListener<AuthResult?>,
        email: String?,
        password: String?
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(
                onCompleteListener
            )
    }

    fun singUp(
        onCompleteListener: CustomOnCompleteListener<AuthResult?>,
        email: String?,
        password: String?
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(
                onCompleteListener
            )
    }

    fun recoverPassword(email: String?, onCompleteListener: CustomOnCompleteListener<Void?>) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email!!).addOnCompleteListener(
            onCompleteListener
        )
    }


    fun updateProfile(
        userId: String,
        urlImage: String,
        postListener: CustomOnCompleteListener<Uri>
    ) {
        val storage = FirebaseStorage.getInstance()
        val mPostReference =
            storage.reference.child(LearningApplication.getInstance().applicationName).child(
                "$userId.jpg"
            )
        val data = Tools.getImage(LearningApplication.getMyApplicationContext(), urlImage, 150, 150)
        if (data != null) {
            val uploadTask = mPostReference.putBytes(data)
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful && task.exception != null) {
                    throw task.exception!!
                }
                mPostReference.downloadUrl
            }.addOnCompleteListener(postListener)
        }
    }

    fun uploadProfile(url: String?, onCompleteListener: CustomOnCompleteListener<Void>) {
        val user = FirebaseAuth.getInstance().currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setPhotoUri(Uri.parse(url))
            .build()
        user!!.updateProfile(profileUpdates).addOnCompleteListener(onCompleteListener)
    }

}