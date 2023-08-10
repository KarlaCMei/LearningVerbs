package com.karla.learningverbs.viewmodel;

import android.net.Uri;

import com.karla.learningverbs.R;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.Tools;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.karla.learningverbs.repository.userrepository.SplashRepository;
import com.karla.learningverbs.repository.UserDetailRepository;
import com.karla.learningverbs.utils.BaseViewModel;

public class UserDetailViewModel extends BaseViewModel {
    private SplashRepository splashRepository;
    private UserDetailRepository userDetailRepository;

    public UserDetailViewModel() {
        userDetailRepository = UserDetailRepository.getInstance();
        splashRepository = SplashRepository.getInstance();
    }

    public void updateProfile(String urlImage) {
        userDetailRepository.updateProfile(splashRepository.getUserId(), urlImage, new CustomOnCompleteListener<Uri>() {
            @Override
            public void onSuccess(Uri task) {
                uploadProfile(task + task.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                msgError.postValue(throwable.getMessage());

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

    public void uploadProfile(String url) {
        userDetailRepository.uploadProfile(url, new CustomOnCompleteListener() {
            @Override
            public void onSuccess(Object task) {
                Tools.showToastMessage(LearningApplication.getMyApplicationContext().getString(R.string.msg_image_updated));
            }

            @Override
            public void onFailure(Throwable throwable) {
                msgError.postValue(throwable.getMessage());
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

}
