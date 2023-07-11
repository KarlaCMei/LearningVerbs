package com.example.learningverbs.listverbs.repository;

import com.example.learningverbs.model.Verb;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class VerbListFragmentRepository {
    FirebaseFirestore mFireBaseData;
    private static VerbListFragmentRepository instance;

    public static VerbListFragmentRepository getInstance(){
        if(instance == null ) instance = new VerbListFragmentRepository();
        return instance;
    }

    public Query obtainQuery() {
        mFireBaseData = FirebaseFirestore.getInstance();
        return mFireBaseData.collection("verbos");
    }

}
