package com.karla.learningverbs.repository.userrepository;

import android.net.Uri;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;

public class SplashRepository {
    private FirebaseAuth firebaseAuth;
    private static SplashRepository instance;

    public SplashRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static SplashRepository getInstance() {
        if (instance == null) instance = new SplashRepository();
        return instance;
    }

    public boolean isLogin() {
        return firebaseAuth.getCurrentUser() != null;
    }

    public String getUserId() {
        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getUid() : "";

    }

    public Uri getUserUrlImage() {
        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getPhotoUrl() : null;
    }

    public void login(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    public void singUp(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);

    }

}
