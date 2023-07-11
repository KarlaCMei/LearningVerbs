package com.example.learningverbs.home.ui.home.view;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.example.learningverbs.databinding.FragmentHomeBinding;
import com.example.learningverbs.detailverb.view.VerbDetailActivity;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnVerbHave.cardVerbHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                startActivity(intent);
            }
        });

        binding.btnVerbToBe.cardVerbToBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                startActivity(intent);
            }
        });

        binding.btnVerbDo.cardVerbDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                startActivity(intent);
            }
        });

    }


}