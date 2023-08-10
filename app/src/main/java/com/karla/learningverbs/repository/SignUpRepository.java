package com.karla.learningverbs.repository;

import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpRepository {
    private static SignUpRepository instance;

    public static SignUpRepository getInstance() {
        if (instance == null) instance = new SignUpRepository();
        return instance;
    }

    public void singUp(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);

    }

}


