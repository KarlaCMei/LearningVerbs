package com.example.learningverbs.listverbs.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningverbs.adapter.OnClicVerbListener;
import com.example.learningverbs.adapter.VerbAdapter;
import com.example.learningverbs.databinding.FragmentVerbListBinding;
import com.example.learningverbs.detailverb.view.VerbDetailActivity;
import com.example.learningverbs.listverbs.viewmodel.VerbListViewModel;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.tools.LearningVerbsDialogGlobal;
import com.example.learningverbs.userdetail.view.UserDetailActivity;
import com.example.learningverbs.utils.BaseFragment;
import com.example.learningverbs.utils.constants.Constants;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import java.io.Serializable;

public class VerbListFragment extends BaseFragment<FragmentVerbListBinding, VerbListViewModel> {
    private VerbAdapter adapterList;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel.query();

        viewModel.getQuery().observe(this, new Observer<Query>() {
            @Override
            public void onChanged(Query query) {
                FirestoreRecyclerOptions<Verb> firestoreRecyclerAdapter = new FirestoreRecyclerOptions.Builder<Verb>().setQuery(query, Verb.class).build();
                adapterList = new VerbAdapter(firestoreRecyclerAdapter);
                adapterList.setListener(new OnClicVerbListener() {
                    @Override
                    public void onVerbClicListener(Verb verb) {
                        /*Bundle detailActivity = new Bundle();
                        detailActivity.putSerializable(Constants.VERB, verb);
                        Intent intent = new Intent(requireActivity(), VerbDetailActivity.class);
                        startActivity(intent);*/


                        Intent detailActivity = new Intent(requireActivity(), VerbDetailActivity.class);
                        detailActivity.putExtra(Constants.VERB, verb);
                        startActivity(detailActivity);

                    }
                });

                binding.listVerbs.setAdapter(adapterList);
                adapterList.notifyDataSetChanged();
                adapterList.startListening();

            }
        });

        binding.btnCreateVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LearningVerbsDialogGlobal.showDialogChange(VerbListFragment.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapterList != null){
            adapterList.startListening();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapterList != null){
            adapterList.stopListening();
        }
    }
}