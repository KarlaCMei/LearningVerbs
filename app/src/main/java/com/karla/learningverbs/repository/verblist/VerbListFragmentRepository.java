package com.karla.learningverbs.repository.verblist;

import com.google.android.gms.tasks.OnSuccessListener;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.firebase.CustomEventListener;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;
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
        mPostReference = dataBaseBookReference.child(LearningApplication.getInstance().getApplicationName());
    }

    public void getSearchVerb(String verbName, CustomListEventListener<Verb> postListener) {
        String paramName = verbName != null && !verbName.isEmpty() ? verbName : "";
        mPostReference.child("VerbList").orderByChild("verbSpanishPresent").startAt(paramName).endAt(paramName + "\uf8ff").addValueEventListener(postListener);
    }

    public void getListVerbs(CustomListEventListener<Verb> postListener) {
        mPostReference.child("VerbList").addValueEventListener(postListener);
    }
    public void getListVerbsDataBase(CustomListEventListener<Verb> postListener) {
        mPostReference.child("MainVerbs").addValueEventListener(postListener);
    }

    public void getFavoritesListUser(String userId, CustomListEventListener<Verb> postListener) {
        mPostReference.child("Favorites").child(userId).addValueEventListener(postListener);
    }

    public void addVerbFavoriteUser(String idUser, String verbId, Verb verb) {
        mPostReference.child("Favorites").child(idUser).child(verbId).setValue(verb);
    }

    public void getUserVerbFavorite(String idUser, String verbId, CustomEventListener<Verb> postListener) {
        mPostReference.child("Favorites").child(idUser).orderByChild("verbId").equalTo(verbId).addValueEventListener(postListener);
    }

    public void deleteElement(String iduser, String verbid, OnSuccessListener onSuccessListener) {
        mPostReference.child("Favorites").child(iduser).child(verbid).removeValue().addOnSuccessListener(onSuccessListener);
    }

}
