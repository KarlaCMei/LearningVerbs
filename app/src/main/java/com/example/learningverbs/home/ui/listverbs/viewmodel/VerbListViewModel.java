package com.example.learningverbs.home.ui.listverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learningverbs.utils.BaseViewModel;

public class VerbListViewModel extends BaseViewModel {

    private final MutableLiveData<String> mText;

    public VerbListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}