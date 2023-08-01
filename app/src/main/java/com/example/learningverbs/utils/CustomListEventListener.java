package com.example.learningverbs.utils;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public abstract class CustomListEventListener<T> implements ValueEventListener {
    final ArrayList<T> verbList = new ArrayList<>();
    private Class<T> customClass;

    public CustomListEventListener(Class<T> GenericTypeIndicator) {
        showLoaging();
        this.customClass = GenericTypeIndicator;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        hideLoading();
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
            verbList.add(postSnapshot.getValue(customClass));
        }
        if (verbList.isEmpty()) {
            onFailed(new Throwable("Error al obtener los datos"));
        } else {
            onSuccess(verbList);
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        hideLoading();
        onFailed(new Throwable(error.getMessage()));
    }


    public abstract void onSuccess(ArrayList<T> response);
    public abstract void onFailed(Throwable throwable);
    public abstract void showLoaging();
    public abstract void hideLoading();

}