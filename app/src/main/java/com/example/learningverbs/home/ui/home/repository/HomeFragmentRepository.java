package com.example.learningverbs.home.ui.home.repository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.CustomListEventListener;
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
        mPostReference = dataBaseBookReference.child("LearningVerbs").child("MainVerbs");
    }
    public void fillDataBase(Verb verb) {
        mPostReference.child(verb.getVerbId()).setValue(verb);
    }

    public void getListVerbsDataBase(CustomListEventListener<Verb> postListener){
        mPostReference.addValueEventListener(postListener);
    }

}
