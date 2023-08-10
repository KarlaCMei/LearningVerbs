package com.karla.learningverbs.repository;

import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.firebase.CustomEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VerbDetailRepository {
    private DatabaseReference dataBaseBookReference;
    private DatabaseReference mPostReference;
    public static VerbDetailRepository instance;

    public static VerbDetailRepository getInstance() {
        if (instance == null) instance = new VerbDetailRepository();
        return instance;
    }

    public VerbDetailRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child(LearningApplication.getInstance().getApplicationName()).child("Favorites");
    }

    public void addVerbFavoriteUser(String idUser, String verbId, Verb verb) {
        mPostReference.child(idUser).child(verbId).setValue(verb);
    }

    public void getUserVerbFavorite(String idUser, String verbId, CustomEventListener<Verb> postListener) {
        mPostReference.child(idUser).orderByChild("verbId").equalTo(verbId).addValueEventListener(postListener);
    }

    public void deleteElement(String iduser, String verbid, OnSuccessListener onSuccessListener) {
        mPostReference.child(iduser).child(verbid).removeValue().addOnSuccessListener(onSuccessListener);
    }

}
