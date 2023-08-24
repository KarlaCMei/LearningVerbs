package com.karla.learningverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.userrepository.UserRepository;
import com.karla.learningverbs.utils.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> isLoginStatus;
    private UserRepository splashScreenRepository;

    public SplashViewModel() {
        this.isLoginStatus = new MutableLiveData<>();
        this.splashScreenRepository = UserRepository.getInstance();
    }

    public void isLogin() {
        isLoginStatus.setValue(splashScreenRepository.isLogin());
    }

    public LiveData<Boolean> getIsLogin() {
        return isLoginStatus;
    }
}
