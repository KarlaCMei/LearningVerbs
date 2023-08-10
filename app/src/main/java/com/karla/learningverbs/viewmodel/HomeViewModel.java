package com.karla.learningverbs.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.SplashRepository;
import com.karla.learningverbs.utils.BaseViewModel;

public class HomeViewModel extends BaseViewModel {
    private SplashRepository splashRepository;
    private MutableLiveData<Uri> urlImage;


    public HomeViewModel() {
        this.urlImage = new MutableLiveData<>();
        splashRepository = SplashRepository.getInstance();
    }

    public void responseImageUser() {
        urlImage.setValue(splashRepository.getUserUrlImage());
    }

    public LiveData<Uri> getUrlImage() {
        return urlImage;
    }


}
