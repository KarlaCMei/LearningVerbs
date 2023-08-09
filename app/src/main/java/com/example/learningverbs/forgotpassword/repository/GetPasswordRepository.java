package com.example.learningverbs.forgotpassword.repository;

import com.example.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class GetPasswordRepository {
    private static GetPasswordRepository instance;

    public static GetPasswordRepository getInstance() {
        if (instance == null) instance = new GetPasswordRepository();
        return instance;
    }

    public void recoverPassword(String email, CustomOnCompleteListener<Void> onCompleteListener) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(onCompleteListener);

    }

}
