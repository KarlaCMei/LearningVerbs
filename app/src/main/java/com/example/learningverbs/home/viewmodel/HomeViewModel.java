package com.example.learningverbs.home.viewmodel;

import android.net.Uri;

import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.utils.BaseViewModel;

public class HomeViewModel extends BaseViewModel {
    private SplashRepository splashRepository;

    public HomeViewModel(){
        splashRepository = SplashRepository.getInstance();
    }

    public void responseImageUser(){
        splashRepository.getUserUrlImage();
    }

}
