package com.example.learningverbs.favorites.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.favorites.repository.FavoritesRepository;
import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.utils.BaseViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class FavoritesViewModel extends BaseViewModel {
    private FavoritesRepository favoritesRepository;
    private SplashRepository splashRepository;

    public FavoritesViewModel() {
        splashRepository = SplashRepository.getInstance();
        favoritesRepository = FavoritesRepository.getInstance();
    }

    public void fillDb(Verb verb) {
        favoritesRepository.fillUserVerbFavorites(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Log.e("TestFirebase", "fillDataBase error:" + error.getMessage());
                } else {
                    Log.e("TestFirebase", "Se agrego correctamente");
                }
            }

        }, splashRepository.getUserId(), verb);
    }

}