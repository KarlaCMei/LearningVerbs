package com.example.learningverbs.detailverb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningverbs.detailverb.repository.VerbDetailRepository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.utils.BaseViewModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class VerbDetailViewModel extends BaseViewModel {
    private VerbDetailRepository verbDetailRepository;
    private SplashRepository splashRepository;
    public VerbDetailViewModel() {
        verbDetailRepository = VerbDetailRepository.getInstance();
        splashRepository = SplashRepository.getInstance();
    }
    public void responseVerbFavoriteUser(Verb verb){
        verbDetailRepository.fillUserVerbFavorites(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Log.e("TestFirebase", "fillDataBase error:" + error.getMessage());
                } else {
                    splashRepository.getUserId();
                    Log.e("UserId", "ID"+ splashRepository.getUserId());
                    Log.e("TestFirebase", "Se agrego correctamente");
                }
            }
        },splashRepository.getUserId(), verb);

    }
}
