package com.example.learningverbs.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.learningverbs.databinding.FragmentHomeBinding;

public abstract class BaseFragment <BINDING extends ViewBinding, VM extends BaseViewModel> extends Fragment {

    protected VM viewModel;
    protected BINDING binding;

    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater,ViewGroup container);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = createViewBinding(LayoutInflater.from(requireContext()),container);
        viewModel = createViewModel();
        return  binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
