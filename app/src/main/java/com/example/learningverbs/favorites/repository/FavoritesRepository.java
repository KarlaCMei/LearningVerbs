package com.example.learningverbs.favorites.repository;

import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.model.Verb;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
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

    public FavoritesRepository(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child("LearningVerbs").child("Favorites");
    }

    public void fillUserVerbFavorites(DatabaseReference.CompletionListener listener, String idUser, Verb verb){
        mPostReference.child(idUser).child(verb.getVerbId()).setValue(verb);
    }



}
