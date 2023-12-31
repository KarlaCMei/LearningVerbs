package com.karla.learningverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.verblist.VerbRepository;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.base.BaseViewModel;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragmentViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListVerbs = new MutableLiveData<>();
    private MutableLiveData<Verb> verbRandom = new MutableLiveData<>();
    private VerbRepository verbRepository;

    public HomeFragmentViewModel() {
        verbRepository = VerbRepository.getInstance();

    }

    public void getListElement() {
        verbRepository.getListVerbsDataBase(new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                getResultListVerbs.postValue(response);
            }

            @Override
            public void onFailed(Throwable throwable) {
                msgError.postValue(throwable.getMessage());
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

    public LiveData<List<Verb>> getListResultsVerbs() {
        return getResultListVerbs;
    }


    public void getListVerbs() {
        verbRepository.getListVerbs(new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                Random random = new Random();
                int randomIndex = random.nextInt(response.size());
                Verb randomElement = response.get(randomIndex);
                verbRandom.setValue(randomElement);
            }

            @Override
            public void onFailed(Throwable throwable) {
                msgError.postValue(throwable.getMessage());
            }

            @Override
            public void showLoaging() {

            }

            @Override
            public void hideLoading() {

            }
        });
    }

    public LiveData<Verb> getElementRandom() {
        return verbRandom;
    }


}