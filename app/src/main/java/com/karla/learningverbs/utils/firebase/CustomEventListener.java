package com.karla.learningverbs.utils.firebase;

import androidx.annotation.NonNull;

import com.karla.learningverbs.R;
import com.karla.learningverbs.utils.LearningApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public abstract class CustomEventListener<T> implements ValueEventListener {
    private Class<T> customClass;

    public CustomEventListener(Class<T> GenericTypeIndicator) {
        showLoaging();
        this.customClass = GenericTypeIndicator;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        hideLoading();
        T currentCustomClass = null;
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
            currentCustomClass = postSnapshot.getValue(customClass);
        }
        if (currentCustomClass != null) {
            onSuccess(currentCustomClass);
        } else {
            onFailed(new Throwable(LearningApplication.getInstance().getString(R.string.msg_throwable)));
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        hideLoading();
        onFailed(new Throwable(error.getMessage()));
    }


    public abstract void onSuccess(T response);

    public abstract void onFailed(Throwable throwable);

    public abstract void showLoaging();

    public abstract void hideLoading();

}
