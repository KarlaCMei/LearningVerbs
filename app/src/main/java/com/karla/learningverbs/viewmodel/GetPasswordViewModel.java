package com.karla.learningverbs.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karla.learningverbs.R;
import com.karla.learningverbs.repository.userrepository.UserRepository2;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.karla.learningverbs.kotlin.utils.base.BaseViewModel;

public class GetPasswordViewModel extends BaseViewModel {
    private MutableLiveData<String> isSendEmail;
    private UserRepository2 repository;

    public GetPasswordViewModel() {
        this.repository = UserRepository2.getInstance();
        repository = UserRepository2.getInstance();
        this.isSendEmail = new MutableLiveData<>();
    }

    public void getRecoveryPassword(String email) {

        repository.recoverPassword(email, new CustomOnCompleteListener<Void>() {
            @Override
            public void onSuccess(Void task) {
                isSendEmail.postValue(LearningApplication.getInstance().getString(R.string.msg_email_send));
            }

            @Override
            public void onFailure(Throwable throwable) {
                msgError.postValue(LearningApplication.getInstance().getString(R.string.msg_no_email_send));
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

    public LiveData<String> sendEmail() {
        return isSendEmail;
    }

}
