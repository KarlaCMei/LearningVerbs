package com.example.learningverbs.splash.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.utils.BaseViewModel;

public class SplashViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> isLoginStatus;
    private SplashRepository splashScreenRepository;

    public SplashViewModel() {
        this.isLoginStatus = new MutableLiveData<>();
        this.splashScreenRepository = SplashRepository.getInstance();
    }

    public void isLogin() {
        isLoginStatus.setValue(splashScreenRepository.isLogin());
    }

    public LiveData<Boolean> getIsLogin() {
        return isLoginStatus;
    }
}
