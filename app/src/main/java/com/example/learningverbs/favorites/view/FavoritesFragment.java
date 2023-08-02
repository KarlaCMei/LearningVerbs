package com.example.learningverbs.favorites.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningverbs.databinding.FragmentFavoritesBinding;
import com.example.learningverbs.favorites.viewmodel.FavoritesViewModel;
import com.example.learningverbs.model.Verb;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillFavoriteUserVerbDataBase();


    }
    private void fillFavoriteUserVerbDataBase() {
        Verb verb = new Verb();
        viewModel.fillDb(verb);

    }
}