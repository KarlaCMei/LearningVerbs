package com.karla.learningverbs.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.repository.userrepository.SplashRepository;
import com.karla.learningverbs.repository.verblist.VerbListFragmentRepository;
import com.karla.learningverbs.utils.BaseViewModel;
import com.karla.learningverbs.utils.firebase.CustomEventListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class VerbDetailViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isVerbFavorite = new MutableLiveData<>();
    private VerbListFragmentRepository verbDetailRepository;
    private SplashRepository splashRepository;

    public VerbDetailViewModel() {
        verbDetailRepository = VerbListFragmentRepository.getInstance();
        splashRepository = SplashRepository.getInstance();
    }

    public void responseVerbFavoriteUser(String verbId) {
        verbDetailRepository.getUserVerbFavorite(splashRepository.getUserId(), verbId, new CustomEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(Verb response) {
                isVerbFavorite.setValue(response != null);
            }

            @Override
            public void onFailed(Throwable throwable) {
                isVerbFavorite.setValue(false);
            }

            @Override
            public void showLoaging() {
                loading.setValue(true);
            }

            @Override
            public void hideLoading() {
                loading.setValue(false);
            }
        });
    }

    public void deleteelement(String verbid) {
        verbDetailRepository.deleteElement(splashRepository.getUserId(), verbid, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
            }
        });
    }

    public void fillDb(Verb verb, String verbId) {
        verbDetailRepository.addVerbFavoriteUser(splashRepository.getUserId(), verbId, verb);
    }

    public LiveData<Boolean> getIsFavoriteVerb() {
        return isVerbFavorite;
    }
}
