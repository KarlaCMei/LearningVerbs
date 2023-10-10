package com.karla.learningverbs.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.userrepository.UserRepository2;
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel {
    private UserRepository2 userRepository2;
    private MutableLiveData<Uri> urlImage;


    public HomeViewModel() {
        this.urlImage = new MutableLiveData<>();
        userRepository2 = UserRepository2.getInstance();
    }

    public void responseImageUser() {
        urlImage.setValue(userRepository2.getUserUrlImage());
    }

    public LiveData<Uri> getUrlImage() {
        return urlImage;
    }


}
