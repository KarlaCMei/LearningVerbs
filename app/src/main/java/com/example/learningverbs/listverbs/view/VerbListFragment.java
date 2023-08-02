package com.example.learningverbs.listverbs.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class VerbListFragment extends BaseFragment<FragmentVerbListBinding, VerbListViewModel> {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //inicializateElements();
        //fillDataBase();
        getListDataBase();
        observers();

        /*binding.btnCreateVerb.setOnClickListener(new View.OnClickListener() {
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
        });*/

        binding.txtSearchVerb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getHandler().removeCallbacksAndMessages(null);
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(charSequence.toString() != null){
                            viewModel.getSearchVerb(charSequence.toString());
                        }
                    }
                }, 1000);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public Handler getHandler() {
        if(handler==null){
            handler = new Handler();
        }
        return handler;
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
    /*public void fillDataBase() {
        long currentTimeMillis = System.currentTimeMillis();
        Verb verb = new Verb();

        verb.setVerbId(String.valueOf(currentTimeMillis));
        verb.setImage("https://img.freepik.com/vector-premium/llamar-personas-traves-comunicacion-linea-telefono-internet-mano-que-sostiene-telefono_373887-1652.jpg");
        verb.setVerbSpanishPresent("Llamar");
        verb.setVerbEnglishPresent("Call");
        verb.setRegular(true);

        ExampleVerb exampleVerbPresent = new ExampleVerb();
        exampleVerbPresent.setVerbSpanish("Llamar");
        exampleVerbPresent.setVerbEnglish("Call");
        exampleVerbPresent.setPhraseAffirmativeSpanish("Quiero hacer una llamada.");
        exampleVerbPresent.setPhraseAffirmativeEnglish("I want to make a call.");
        exampleVerbPresent.setPhraseNegativeSpanish("No quiero hacer una llamada");
        exampleVerbPresent.setPhraseNegativeEnglish("I don't want to make a call.");
        exampleVerbPresent.setPhraseQuestionSpanish("¿Puedo hacer una llamada?");
        exampleVerbPresent.setPhraseQuestionEnglish("Can i make a call?");


        ExampleVerbPast exampleVerbPast= new ExampleVerbPast();
        exampleVerbPast.setVerbSpanishPast("Llamé");
        exampleVerbPast.setVerbEnglishPast("Called");
        exampleVerbPast.setPhrasePastAffirmativeSpanish("Llamé a tu casa.");
        exampleVerbPast.setPhrasePastAffirmativeEnglish("I called at your house.");
        exampleVerbPast.setPhrasePastNegativeSpanish("No llamé a tu casa");
        exampleVerbPast.setPhrasePastNegativeEnglish("I didn't call your house");
        exampleVerbPast.setPhrasePastQuestionSpanish("¿Llamé a tu casa?");
        exampleVerbPast.setPhrasePastQuestionEnglish("Did I call your house?");

        ExampleVerbFuture exampleVerbFuture = new ExampleVerbFuture();
        exampleVerbFuture.setVerbSpanishFuture("Llamaré");
        exampleVerbFuture.setVerbEnglishFuture("Will call");
        exampleVerbFuture.setPhraseFutureAffirmativeSpanish("Te llamaré mañana.");
        exampleVerbFuture.setPhraseFutureAffirmativeSpanish("I will call you tomorrow.");
        exampleVerbFuture.setPhraseFutureNegativeSpanish("No te llamare mañana");
        exampleVerbFuture.setPhraseFutureNegativeEnglish("I won't call you tomorrow.");
        exampleVerbFuture.setPhraseFutureQuestionSpanish("¿Me llamarás mañana?");
        exampleVerbFuture.setPhraseFutureQuestionEnglish("Will you call me tomorrow?");

        verb.setExampleVerbPresent(exampleVerbPresent);
        verb.setExampleVerbPast(exampleVerbPast);
        verb.setExampleVerbFuture(exampleVerbFuture);
        viewModel.fillDb(verb);
    }*/
}