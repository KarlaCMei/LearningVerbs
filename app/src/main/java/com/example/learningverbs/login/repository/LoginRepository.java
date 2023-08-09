package com.example.learningverbs.login.repository;

import com.example.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepository {

    private static LoginRepository instance;

    public static LoginRepository getInstance() {
        if (instance == null) instance = new LoginRepository();
        return instance;
    }

    public void login(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

}
