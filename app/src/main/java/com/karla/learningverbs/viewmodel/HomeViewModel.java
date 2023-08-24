package com.karla.learningverbs.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.userrepository.UserRepository;
import com.karla.learningverbs.utils.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel {
    private UserRepository userRepository;
    private MutableLiveData<Uri> urlImage;


    public HomeViewModel() {
        this.urlImage = new MutableLiveData<>();
        userRepository = UserRepository.getInstance();
    }

    public void responseImageUser() {
        urlImage.setValue(userRepository.getUserUrlImage());
    }

    public LiveData<Uri> getUrlImage() {
        return urlImage;
    }


}
