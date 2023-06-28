package com.example.learningverbs.home.ui.favorites;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningverbs.databinding.FragmentFavoritesBinding;
import com.example.learningverbs.utils.BaseFragment;

public class FavoritesFragment extends BaseFragment<FragmentFavoritesBinding, FavoritesViewModel> {

    @Override
    protected FavoritesViewModel createViewModel() {
        return new ViewModelProvider(this).get(FavoritesViewModel.class);
    }

    @NonNull
    @Override
    protected FragmentFavoritesBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentFavoritesBinding.inflate(layoutInflater, container, false);
    }
}