package com.karla.learningverbs.repository;

import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragmentRepository {
    private DatabaseReference dataBaseBookReference;
    private DatabaseReference mPostReference;
    public static HomeFragmentRepository instance;

    public static HomeFragmentRepository getInstance() {
        if (instance == null) instance = new HomeFragmentRepository();
        return instance;
    }

    public HomeFragmentRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child(LearningApplication.getInstance().getApplicationName()).child("MainVerbs");
    }

    public void getListVerbsDataBase(CustomListEventListener<Verb> postListener) {
        mPostReference.addValueEventListener(postListener);
    }

}