package com.example.learningverbs.userdetail.viewmodel;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.learningverbs.R;
import com.example.learningverbs.utils.LearningApplication;
import com.example.learningverbs.utils.Tools;
import com.example.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.example.learningverbs.splash.repository.SplashRepository;
import com.example.learningverbs.userdetail.repository.UserDetailRepository;
import com.example.learningverbs.utils.BaseViewModel;

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
