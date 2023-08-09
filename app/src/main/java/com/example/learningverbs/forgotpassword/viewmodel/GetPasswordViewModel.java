package com.example.learningverbs.forgotpassword.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningverbs.R;
import com.example.learningverbs.utils.LearningApplication;
import com.example.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.example.learningverbs.forgotpassword.repository.GetPasswordRepository;
import com.example.learningverbs.utils.BaseViewModel;

public class GetPasswordViewModel extends BaseViewModel {
    private MutableLiveData<String> isSendEmail;
    private GetPasswordRepository repository;

    public GetPasswordViewModel() {
        this.repository = GetPasswordRepository.getInstance();
        repository = GetPasswordRepository.getInstance();
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
