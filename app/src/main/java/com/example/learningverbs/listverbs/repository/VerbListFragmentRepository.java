package com.example.learningverbs.listverbs.repository;

import com.example.learningverbs.model.Verb;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
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
        String documentoId = "verbos";
        mFireBaseData = FirebaseFirestore.getInstance();

        return mFireBaseData.collection("learningVerbs").document(documentoId).collection("add");

        //return  mFireBaseData.collection("learningVerbs").document("verbos").collection("call");

        //return mFireBaseData.collection("add");
    }

}
