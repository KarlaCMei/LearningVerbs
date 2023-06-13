package com.example.learningverbs.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public abstract class CustomOnCompleteListener<T> implements OnCompleteListener<T> {

    @Override
    public void onComplete(@NonNull Task<T> task){
        if(task.isSuccessful()){
            onSuccess(task.getResult());

        }else{
            onFailure(task.getException());

        }


    }

    public abstract void onSuccess(T task);
    public abstract void onFailure(Throwable throwable);

}
