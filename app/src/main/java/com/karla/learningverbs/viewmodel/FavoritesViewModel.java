package com.karla.learningverbs.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.repository.userrepository.UserRepository;
import com.karla.learningverbs.repository.verblist.VerbRepository;
import com.karla.learningverbs.utils.base.BaseViewModel;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListFavoriteVerbs = new MutableLiveData<>();
    private VerbRepository favoritesRepository;
    private UserRepository userRepository;

    public FavoritesViewModel() {
        favoritesRepository = VerbRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public void getFavoriteVerbListElement() {
        favoritesRepository.getFavoritesListUser(userRepository.getUserId(), new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                getResultListFavoriteVerbs.postValue(response);

            }

            @Override
            public void onFailed(Throwable throwable) {
                getResultListFavoriteVerbs.postValue(null);
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

}