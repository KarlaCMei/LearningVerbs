package com.karla.learningverbs.utils.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.karla.learningverbs.kotlin.utils.base.BaseViewModel;

public abstract class BaseFragment<BINDING extends ViewBinding, VM extends BaseViewModel> extends Fragment {

    protected VM viewModel;
    protected BINDING binding;

    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater, ViewGroup container);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = createViewBinding(LayoutInflater.from(requireContext()), container);
        viewModel = createViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observers();
    }

    private void observers() {
        viewModel.loading.observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });

        viewModel.msgError.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (getActivity() instanceof BaseActivity2) {
                    ((BaseActivity2) getActivity()).showMessageError(s);
                }
            }
        });
    }

    private void showProgress() {

        if (getActivity() instanceof BaseActivity2) {
            ((BaseActivity2) getActivity()).showProgress();
        }

    }

    private void hideProgress() {

        if (getActivity() instanceof BaseActivity2) {
            ((BaseActivity2) getActivity()).hideProgress();
        }
    }

}
