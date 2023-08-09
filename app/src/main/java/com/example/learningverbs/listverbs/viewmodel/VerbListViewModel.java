package com.example.learningverbs.listverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseViewModel;
import com.example.learningverbs.utils.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerbListViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListVerbs = new MutableLiveData<>();
    private VerbListFragmentRepository repository;
    private MutableLiveData<Boolean> getVerbAdd = new MutableLiveData<>(false);

    public VerbListViewModel() {
        repository = VerbListFragmentRepository.getInstance();
    }

    public void getSearchVerb(String verbName) {

        String searchName = verbName != null ? verbName : "";
        repository.getSearchVerb(searchName, new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                getVerbAdd.setValue(true);
                getResultListVerbs.postValue(response);
            }

            @Override
            public void onFailed(Throwable throwable) {
                getVerbAdd.setValue(false);
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

    public LiveData<Boolean> getResultVerbAdd() {
        return getVerbAdd;
    }

}