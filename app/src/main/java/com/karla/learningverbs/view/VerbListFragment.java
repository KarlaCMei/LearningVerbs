package com.karla.learningverbs.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.karla.learningverbs.adapter.OnClicVerbListener;
import com.karla.learningverbs.adapter.VerbAdapter;
import com.karla.learningverbs.databinding.FragmentVerbListBinding;
import com.karla.learningverbs.viewmodel.VerbListViewModel;
import com.karla.learningverbs.model.Verb;
import com.karla.learningverbs.utils.BaseFragment;
import com.karla.learningverbs.utils.constants.Constants;

import java.util.List;

public class VerbListFragment extends BaseFragment<FragmentVerbListBinding, VerbListViewModel> implements TextWatcher {
    private VerbAdapter adapterListVerbs;
    private Handler handler;

    @Override
    protected VerbListViewModel createViewModel() {
        return new ViewModelProvider(this).get(VerbListViewModel.class);
    }

    @NonNull
    @Override
    protected FragmentVerbListBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentVerbListBinding.inflate(layoutInflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.txtSearchVerb.addTextChangedListener(this);
        getVerbs(null);
        observers();


    }


    public void getVerbs(String name) {
        getHandler().removeCallbacksAndMessages(null);
        if (name != null) {


            getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    viewModel.getSearchVerb(name);

                }
            }, 1000);
        } else {
            viewModel.getSearchVerb(null);
        }

    }

    public Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }


    private void observers() {
        viewModel.getListResultsVerbs().observe(this, new Observer<List<Verb>>() {
            @Override
            public void onChanged(List<Verb> verbs) {

                if (verbs.size() > 0) {
                    binding.msgNoResultsFound.setVisibility(View.GONE);
                    binding.imgNoResultsFound.setVisibility(View.GONE);
                    binding.listVerbs.setVisibility(View.VISIBLE);
                    adapterListVerbs = new VerbAdapter(verbs, new OnClicVerbListener() {
                        @Override
                        public void onVerbClicListener(Verb verb) {
                            Intent detailActivity = new Intent(requireActivity(), VerbDetailActivity.class);
                            detailActivity.putExtra(Constants.VERB, verb);
                            startActivity(detailActivity);
                        }
                    });
                    binding.listVerbs.setAdapter(adapterListVerbs);
                } else {
                    binding.msgNoResultsFound.setVisibility(View.VISIBLE);
                    binding.imgNoResultsFound.setVisibility(View.VISIBLE);
                    binding.listVerbs.setVisibility(View.GONE);
                }
            }
        });


        viewModel.getResultVerbAdd().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.msgNoResultsFound.setVisibility(View.GONE);
                    binding.imgNoResultsFound.setVisibility(View.GONE);
                    binding.listVerbs.setVisibility(View.VISIBLE);
                } else {
                    binding.msgNoResultsFound.setVisibility(View.VISIBLE);
                    binding.imgNoResultsFound.setVisibility(View.VISIBLE);
                    binding.listVerbs.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (binding.txtSearchVerb.hasFocus()) getVerbs(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}