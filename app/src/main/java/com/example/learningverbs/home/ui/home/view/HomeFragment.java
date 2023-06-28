package com.example.learningverbs.home.ui.home.view;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import com.example.learningverbs.databinding.FragmentHomeBinding;
import com.example.learningverbs.home.viewmodel.HomeViewModel;
import com.example.learningverbs.utils.BaseFragment;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    @Override
    protected HomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @NonNull
    @Override
    protected FragmentHomeBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(layoutInflater, container, false);

    }

}