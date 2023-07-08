package com.example.learningverbs.listverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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