package com.karla.learningverbs.utils.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.karla.learningverbs.R;

public abstract class BaseActivity<BINDING extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected BINDING binding;
    private Dialog dialog;

    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        dialog = new Dialog(BaseActivity.this, R.style.customLottie);
        viewModel = createViewModel();
        observers();
    }


    private void observers() {
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });

        viewModel.msgError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String msgError) {
                showMessageError(msgError);
            }
        });
    }

    void showProgress() {
        dialog.setContentView(R.layout.loading_view);
        if (!dialog.isShowing()) dialog.show();
    }

    void hideProgress() {
        if (dialog.isShowing()) dialog.dismiss();
    }


    public void showMessageError(String msgError) {
        Toast.makeText(BaseActivity.this, msgError, Toast.LENGTH_SHORT).show();
    }

}
