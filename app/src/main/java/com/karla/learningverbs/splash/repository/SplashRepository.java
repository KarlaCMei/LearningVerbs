package com.karla.learningverbs.splash.repository;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;

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

}
