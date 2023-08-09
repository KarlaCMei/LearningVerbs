package com.example.learningverbs.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public abstract class CustomOnCompleteListener<T> implements OnCompleteListener<T> {
    public CustomOnCompleteListener() {
        showLoaging();
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        hideLoading();

        if (task.isSuccessful()) {
            onSuccess(task.getResult());

        } else {
            onFailure(task.getException());

        }


    }

    public abstract void onSuccess(T task);

    public abstract void onFailure(Throwable throwable);

    public abstract void showLoaging();

    public abstract void hideLoading();

}
