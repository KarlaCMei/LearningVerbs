package com.example.learningverbs.listverbs.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseViewModel;
import com.example.learningverbs.utils.CustomListEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class VerbListViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListVerbs = new MutableLiveData<>();
    private VerbListFragmentRepository repository;

    public VerbListViewModel() {
        repository = VerbListFragmentRepository.getInstance();
    }

    public void fillDb(Verb verb) {
        repository.fillDataBase(verb);
    }

    public void getSearchVerb(String verbName){
        repository.getSearchVerb(verbName, new CustomListEventListener<Verb>(Verb.class) {
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

    public void getListElement(){
        repository.getListVerbsDataBase(new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                for (Verb verbList : response) {
                    Log.e("Response", "" + verbList.getVerbSpanishPresent());
                    getResultListVerbs.postValue(response);
                }
            }
            @Override
            public void onFailed(Throwable throwable) {
                msgError.postValue(throwable.getMessage());
                Log.e("Mensaje", "No hay informacion");
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

}