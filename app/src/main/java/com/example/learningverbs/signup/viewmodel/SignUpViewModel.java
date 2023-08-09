package com.example.learningverbs.signup.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.example.learningverbs.signup.repository.SignUpRepository;
import com.example.learningverbs.utils.BaseViewModel;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> isSignUp;
    private SignUpRepository repository;

    public SignUpViewModel() {
        this.repository = SignUpRepository.getInstance();
        repository = SignUpRepository.getInstance();
        this.isSignUp = new MutableLiveData<>();
    }

    public void singUp(String email, String password) {
        loading.postValue(true);

        repository.singUp(new CustomOnCompleteListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult task) {
                isSignUp.postValue(true);
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

    public LiveData<Boolean> getfirebaseUser() {
        return isSignUp;
    }

}
