package com.karla.learningverbs.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.repository.userrepository.SplashRepository;
import com.karla.learningverbs.repository.verblist.VerbListFragmentRepository;
import com.karla.learningverbs.utils.BaseViewModel;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListFavoriteVerbs = new MutableLiveData<>();
    private VerbListFragmentRepository favoritesRepository;
    private SplashRepository splashRepository;
    private MutableLiveData<Boolean> getVerbFavorite = new MutableLiveData<>(false);

    public FavoritesViewModel() {
        favoritesRepository = VerbListFragmentRepository.getInstance();
        splashRepository = SplashRepository.getInstance();
    }

    public void getFavoriteVerbListElement() {
        favoritesRepository.getFavoritesListUser(splashRepository.getUserId(), new CustomListEventListener<Verb>(Verb.class) {
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