package com.example.learningverbs.listverbs.repository;

import com.example.learningverbs.model.Verb;
import com.example.learningverbs.tools.LearningApplication;
import com.example.learningverbs.utils.CustomListEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VerbListFragmentRepository {
    private DatabaseReference dataBaseBookReference;
    private DatabaseReference mPostReference;
    public static VerbListFragmentRepository instance;

    public static VerbListFragmentRepository getInstance() {
        if (instance == null) instance = new VerbListFragmentRepository();
        return instance;
    }

    public VerbListFragmentRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataBaseBookReference = database.getReference();
        mPostReference = dataBaseBookReference.child(LearningApplication.getInstance().getApplicationName()).child("VerbList");
    }

    public void getSearchVerb(String verbName, CustomListEventListener<Verb> postListener) {
        String paramName = verbName != null && !verbName.isEmpty() ? verbName : "";
        mPostReference.orderByChild("verbSpanishPresent").startAt(paramName).endAt(paramName + "\uf8ff").addValueEventListener(postListener);
    }

    public void getListVerbs(CustomListEventListener<Verb> postListener) {
        mPostReference.addValueEventListener(postListener);
    }

}
