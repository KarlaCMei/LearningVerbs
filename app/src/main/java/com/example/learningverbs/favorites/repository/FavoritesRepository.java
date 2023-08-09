package com.example.learningverbs.favorites.repository;

import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.LearningApplication;
import com.example.learningverbs.utils.firebase.CustomListEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavoritesRepository {
    private DatabaseReference dataBaseBookReference;
    private DatabaseReference mPostReference;
    public static FavoritesRepository instance;

    public static FavoritesRepository getInstance() {
        if (instance == null) instance = new FavoritesRepository();
        return instance;
    }

    public FavoritesRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child(LearningApplication.getInstance().getApplicationName()).child("Favorites");
    }

    public void getFavoritesListUser(String userId, CustomListEventListener<Verb> postListener) {
        mPostReference.child(userId).addValueEventListener(postListener);
    }

}
