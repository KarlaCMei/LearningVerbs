package com.example.learningverbs.detailverb.repository;

import com.example.learningverbs.model.Verb;
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

    public VerbDetailRepository(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child("LearningVerbs").child("Favorites");
    }

    public void fillUserVerbFavorites(DatabaseReference.CompletionListener listener, String idUser, Verb verb){
        mPostReference.child(idUser).child(verb.getVerbId()).setValue(verb,listener);
    }

}
