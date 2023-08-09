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
import com.example.learningverbs.utils.CustomListEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListFavoriteVerbs = new MutableLiveData<>();
    private FavoritesRepository favoritesRepository;
    private SplashRepository splashRepository;
    private MutableLiveData<Boolean> getVerbFavorite = new MutableLiveData<>(false);

    public FavoritesViewModel() {
        favoritesRepository = FavoritesRepository.getInstance();
        splashRepository = SplashRepository.getInstance();
    }

    public void getFavoriteVerbListElement(){
        favoritesRepository.getListElementsDataBase(splashRepository.getUserId(),new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                getVerbFavorite.setValue(true);
                getResultListFavoriteVerbs.postValue(response);

            }
            @Override
            public void onFailed(Throwable throwable) {
                getResultListFavoriteVerbs.postValue(null);
                getVerbFavorite.setValue(false);

            }

            @Override
            public void showLoaging() {
                loading.postValue(true);
            }

            @Override
            public void hideLoading() {
                loading.postValue(false);
            }
        });
    }

    public LiveData<List<Verb>> getListResultsFavoriteVerbs() {
        return getResultListFavoriteVerbs;
    }
    public LiveData<Boolean> getListVerbsFavorites() {
        return getVerbFavorite;
    }
}