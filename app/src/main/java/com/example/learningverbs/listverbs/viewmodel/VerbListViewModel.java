package com.example.learningverbs.listverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.utils.BaseViewModel;
import com.google.firebase.firestore.Query;

public class VerbListViewModel extends BaseViewModel {

    private final MutableLiveData<Query> queryMutableLiveData;
    private VerbListFragmentRepository repository;

    public VerbListViewModel() {
        this.queryMutableLiveData = new MutableLiveData<>();
        this.repository = VerbListFragmentRepository.getInstance();
    }

    public void query(){
        queryMutableLiveData.postValue(repository.obtainQuery());

    }



    public LiveData<Query> getQuery() {
        return queryMutableLiveData;
    }
}