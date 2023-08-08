package com.example.learningverbs.splash.repository;

import android.net.Uri;
import android.util.Log;

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
       // Log.e("Email user", "Email" + firebaseAuth.getCurrentUser().getPhotoUrl());
        return firebaseAuth.getCurrentUser() != null;
    }

    public String getUserId() {
        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getUid() : "";

    }

    public Uri getUserUrlImage(){
        Log.e("Email user", "Email" + firebaseAuth.getCurrentUser().getPhotoUrl());

        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getPhotoUrl() : null;
    }

}
