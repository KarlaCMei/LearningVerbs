package com.example.learningverbs.home.ui.home.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.home.ui.home.repository.HomeFragmentRepository;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseViewModel;
import com.example.learningverbs.utils.CustomListEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends BaseViewModel {
    private MutableLiveData<List<Verb>> getResultListVerbs = new MutableLiveData<>();
    private HomeFragmentRepository repository;

    public HomeFragmentViewModel() {
        repository = HomeFragmentRepository.getInstance();
    }

    public void fillDb(Verb verb) {
        repository.fillDataBase(verb);
    }

    public void getListElement(){
        repository.getListVerbsDataBase(new CustomListEventListener<Verb>(Verb.class) {
            @Override
            public void onSuccess(ArrayList<Verb> response) {
                    //Log.e("Response", "" + verbList.getVerbSpanishPresent());
                    getResultListVerbs.postValue(response);
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