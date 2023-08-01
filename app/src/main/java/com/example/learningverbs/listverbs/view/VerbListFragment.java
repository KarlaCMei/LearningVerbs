package com.example.learningverbs.listverbs.view;

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
import com.example.learningverbs.databinding.FragmentVerbListBinding;
import com.example.learningverbs.detailverb.view.VerbDetailActivity;
import com.example.learningverbs.listverbs.viewmodel.VerbListViewModel;
import com.example.learningverbs.model.ExampleVerb;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.tools.LearningVerbsDialogGlobal;
import com.example.learningverbs.utils.BaseFragment;
import com.example.learningverbs.utils.constants.Constants;

import java.util.List;

public class VerbListFragment extends BaseFragment<FragmentVerbListBinding, VerbListViewModel> {
    private VerbAdapter adapterListVerbs;

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

        //fillDataBase();
        getListDataBase();
        observers();

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


    public void fillDataBase() {
        long currentTimeMillis = System.currentTimeMillis();
        Verb verb = new Verb();
        ExampleVerb phrase = new ExampleVerb();
        /*verb.setVerbSpanishPresent("Añadir");
        verb.setVerbSpanishPast("Añadí");
        verb.setVerbSpanishFuture("Añadiré");
        verb.setVerbEnglishPresent("Add");
        verb.setVerbEnglishPast("Added");
        verb.setVerbEnglishFuture("Will add");
        verb.setRegular(true);
        verb.setVerbId(String.valueOf(currentTimeMillis));

        phrase.setPhraseAffirmativeSpanish("Debes de añadir dos cucharadas de azucar.");
        phrase.setPhraseAffirmativeEnglish("You must add two tablespoons of sugar.");
        phrase.setPhraseNegativeSpanish("No debes añadir do cucharadas de azucar.");
        phrase.setPhraseNegativeEnglish("You should not add two tablespoons of sugar.");
        phrase.setPhraseQuestionSpanish("¿Debes añadir dos cucharadas de azucar?");
        phrase.setPhraseQuestionEnglish("Should you add two tablespoons of sugar?");

        phrase.setPhrasePastAffirmativeSpanish("Añadí este sitio web a mis favoritos.");
        phrase.setPhrasePastAffirmativeEnglish("I added this website to my favorites.");
        phrase.setPhrasePastNegativeSpanish("No añadí este sitio web a mis favoritos.");
        phrase.setPhrasePastNegativeEnglish("I did not add this website to my favorites.");
        phrase.setPhrasePastQuestionSpanish("¿Añadí este sitio web a mis favoritos?");
        phrase.setPhrasePastQuestionEnglish("Did I add this website to my favorites?");

        phrase.setPhraseFutureAffirmativeSpanish("Añadiré los detalles a lo que he dicho anteriormente.");
        phrase.setPhraseFutureAffirmativeSpanish("I will add the details to what I have said above.");
        phrase.setPhraseFutureNegativeSpanish("No Añadiré los detalles a lo que he dicho anteriormente.");
        phrase.setPhraseFutureNegativeEnglish("I will not add the details to what I have said above.");
        phrase.setPhraseFutureQuestionSpanish("¿Añadiré los detalles a lo que he dicho anteriormente?");
        phrase.setPhraseFutureQuestionEnglish("Will I add the details to what I have said above?");


        verb.setExampleVerb(phrase);*/
        viewModel.fillDb(verb);

    }

    private void getListDataBase() {
        viewModel.getListElement();
    }

    private void observers() {
            viewModel.getListResultsVerbs().observe(this, new Observer<List<Verb>>() {
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
                    binding.listVerbs.setAdapter(adapterListVerbs);
                }
            });

    }

}