package com.karla.learningverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.verblist.VerbRepository;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel;
import com.karla.learningverbs.utils.firebase.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerbListViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListVerbs = new MutableLiveData<>();
    private VerbRepository repository;
    //private MutableLiveData<Boolean> getVerbAdd = new MutableLiveData<>(false);

    public VerbListViewModel() {
        repository = VerbRepository.getInstance();
    }

    public void getSearchVerb(String verbName) {

        String searchName = verbName != null ? verbName : "";
        repository.getSearchVerb(searchName, new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                //getVerbAdd.setValue(true);
                getResultListVerbs.postValue(response);
            }

            @Override
            public void onFailed(Throwable throwable) {
                getResultListVerbs.postValue(null);
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

    /*public LiveData<Boolean> getResultVerbAdd() {
        return getVerbAdd;
    }*/

}