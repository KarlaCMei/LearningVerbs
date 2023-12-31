package com.karla.learningverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.repository.userrepository.UserRepository;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.karla.learningverbs.utils.base.BaseViewModel;
import com.google.firebase.auth.AuthResult;

public class LoginViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> isLogin;
    private UserRepository repository;

    public LoginViewModel() {
        this.repository = UserRepository.getInstance();
        repository = UserRepository.getInstance();
        this.isLogin = new MutableLiveData<>();
    }

    public void doLogin(String email, String password) {
        repository.login(new CustomOnCompleteListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult task) {
                isLogin.postValue(true);
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
        }, email, password);

    }

    public LiveData<Boolean> getFireBaseUser() {
        return isLogin;
    }

}
