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
import com.example.learningverbs.utils.BaseFragment;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

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

                Log.e("query","queryqueryqueryquery"+query);
                FirestoreRecyclerOptions<Verb> firestoreRecyclerAdapter = new FirestoreRecyclerOptions.Builder<Verb>().setQuery(query, Verb.class).build();
                adapterList = new VerbAdapter(firestoreRecyclerAdapter);
                adapterList.setListener(new OnClicVerbListener() {
                    @Override
                    public void onVerbClicListener(Verb verb) {
                        Log.e("Mensaje de intent", "INTENT PARA EL DETALLE DEL VERBO" + verb.getVerboEspañol());
                        Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                        startActivity(intent);

                    }
                });

                binding.listVerbs.setAdapter(adapterList);
                adapterList.notifyDataSetChanged();
                adapterList.startListening();

            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapterList != null){
            adapterList.stopListening();
        }
    }
}