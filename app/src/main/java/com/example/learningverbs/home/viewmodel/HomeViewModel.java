package com.example.learningverbs.home.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.utils.BaseViewModel;

public class HomeViewModel extends BaseViewModel {
    private SplashRepository splashRepository;
    private MutableLiveData<Uri> urlImage;


    public HomeViewModel(){
        this.urlImage = new MutableLiveData<>();
        splashRepository = SplashRepository.getInstance();
    }

    public void responseImageUser(){
        urlImage.setValue(splashRepository.getUserUrlImage());
    }

    public LiveData<Uri> getUrlImage(){
        return urlImage;
    }


}
