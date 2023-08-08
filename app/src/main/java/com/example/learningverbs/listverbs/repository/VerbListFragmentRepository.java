package com.example.learningverbs.listverbs.repository;

import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.CustomListEventListener;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        mPostReference = dataBaseBookReference.child("LearningVerbs").child("VerbList");
    }

    public void fillDataBase(Verb verb) {
        mPostReference.child(verb.getVerbId()).setValue(verb);
    }

    public void getListVerbsDataBase(CustomListEventListener<Verb> postListener){
        mPostReference.orderByChild("verbSpanishPresent").addValueEventListener(postListener);
    }

    public void getSearchVerb(String verbName, CustomListEventListener<Verb> postListener){
        String paramName = verbName!=null && !verbName.isEmpty() ? verbName : "";
        mPostReference.orderByChild("verbSpanishPresent").startAt(paramName).endAt(paramName+ "\uf8ff").addValueEventListener(postListener);
    }

    public void getListVerbs(CustomListEventListener<Verb> postListener){
        mPostReference.addValueEventListener(postListener);
    }

}
