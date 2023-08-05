package com.example.learningverbs.favorites.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningverbs.adapter.OnClicVerbListener;
import com.example.learningverbs.adapter.VerbAdapter;
import com.example.learningverbs.databinding.FragmentFavoritesBinding;
import com.example.learningverbs.detailverb.view.VerbDetailActivity;
import com.example.learningverbs.favorites.viewmodel.FavoritesViewModel;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseFragment;
import com.example.learningverbs.utils.constants.Constants;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FavoritesFragment extends BaseFragment<FragmentFavoritesBinding, FavoritesViewModel> {
    private VerbAdapter adapterListVerbs;

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
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
        getListFavoriteVerbDataBase();
        observers();
    }

    private void getListFavoriteVerbDataBase() {
        viewModel.getFavoriteVerbListElement();
    }

    private void observers() {
        viewModel.getListResultsFavoriteVerbs().observe(this, new Observer<List<Verb>>() {
            @Override
            public void onChanged(List<Verb> verbs) {
                adapterListVerbs = new VerbAdapter(verbs, new OnClicVerbListener() {
                    @Override
                    public void onVerbClicListener(Verb verb) {
                        Intent detailActivity = new Intent(requireActivity(), VerbDetailActivity.class);
                        detailActivity.putExtra(Constants.VERB, verb);
                        startActivity(detailActivity);
                    }
                });
                binding.listVerbsFavorites.setAdapter(adapterListVerbs);
            }
        });

    }
}