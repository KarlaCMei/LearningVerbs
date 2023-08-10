package com.karla.learningverbs.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.karla.learningverbs.databinding.FragmentHomeBinding;
import com.karla.learningverbs.detailverb.view.VerbDetailActivity;
import com.karla.learningverbs.viewmodel.HomeFragmentViewModel;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.BaseFragment;
import com.karla.learningverbs.utils.constants.Constants;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel> {
    private Verb verbResultDo = null;
    private Verb verbResultHave = null;
    private Verb verbResultBe = null;
    private Verb verbDay = null;

    @Override
    protected HomeFragmentViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeFragmentViewModel.class);

    }

    @NonNull
    @Override
    protected FragmentHomeBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(layoutInflater, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getElementRandom();
        getListDataBase();
        observers();
        observer();
        binding.btnVerbHave.cardVerbHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verbResultHave != null) {
                    Intent intent = new Intent(requireActivity(), VerbDetailActivity.class);
                    intent.putExtra(Constants.VERB, verbResultHave);
                    startActivity(intent);
                }
            }
        });

        binding.btnVerbToBe.cardVerbToBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verbResultBe != null) {
                    Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                    intent.putExtra(Constants.VERB, verbResultBe);
                    startActivity(intent);
                }

            }
        });
        binding.btnVerbOfTheDay.btnSignUp.setOnClickListener(view1 -> {
            if (verbDay != null) {
                Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                intent.putExtra(Constants.VERB, verbDay);
                startActivity(intent);
            }
        });

        binding.btnVerbDo.cardVerbDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verbResultDo != null) {
                    Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                    intent.putExtra(Constants.VERB, verbResultDo);
                    startActivity(intent);
                }

            }
        });

    }

    private void getListDataBase() {
        viewModel.getListElement();
    }

    private void observers() {
        viewModel.getListResultsVerbs().observe(this, new Observer<List<Verb>>() {
            @Override
            public void onChanged(List<Verb> verbs) {
                for (Verb verbList : verbs) {
                    if (Objects.equals(verbList.getVerbId(), "1690850399354")) {
                        verbResultDo = verbList;
                        Glide.with(binding.btnVerbDo.imgVerb.getContext()).load(verbList.getImage()).into(binding.btnVerbDo.imgVerb);
                    } else if (Objects.equals(verbList.getVerbId(), "1690850850666")) {
                        verbResultHave = verbList;
                        Glide.with(binding.btnVerbHave.imgVerb.getContext()).load(verbList.getImage()).into(binding.btnVerbHave.imgVerb);
                    } else {
                        verbResultBe = verbList;
                        Glide.with(binding.btnVerbToBe.imgVerb.getContext()).load(verbList.getImage()).into(binding.btnVerbToBe.imgVerb);
                    }

                }

            }

        });

    }

    private void getElementRandom() {
        viewModel.getListVerbs();
    }

    public void observer() {
        viewModel.getElementRandom().observe(this, new Observer<Verb>() {
            @Override
            public void onChanged(Verb verb) {
                verbDay = verb;
                binding.btnVerbOfTheDay.btnSignUp.setText(verb.getVerbSpanishPresent());
            }
        });
    }


}