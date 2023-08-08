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
import com.google.firebase.database.FirebaseDatabase;

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
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
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
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
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


    public void fillDataBase() {
        long currentTimeMillis = System.currentTimeMillis();
        Verb verb = new Verb();

        verb.setVerbId(String.valueOf(currentTimeMillis));
        verb.setImage("https://i.pinimg.com/564x/ff/70/4c/ff704cf23ce4e6107a54e4b657256aad.jpg");
        verb.setVerbSpanishPresent("Caminar");
        verb.setVerbEnglishPresent("Walk");
        verb.setRegular(true);

        ExampleVerb exampleVerbPresent = new ExampleVerb();
        exampleVerbPresent.setVerbSpanish("Caminar");
        exampleVerbPresent.setVerbEnglish("Walk");
        exampleVerbPresent.setPhraseAffirmativeSpanish("Ella se fue a dar un paseo.");
        exampleVerbPresent.setPhraseAffirmativeEnglish("She went for a walk.");
        exampleVerbPresent.setPhraseNegativeSpanish("Ella no se fue a dar un paseo.");
        exampleVerbPresent.setPhraseNegativeEnglish("She didn't go for a walk.");
        exampleVerbPresent.setPhraseQuestionSpanish("¿Ella se fue a dar un paseo?");
        exampleVerbPresent.setPhraseQuestionEnglish("Did she go for a walk?");


        ExampleVerb exampleVerbPast= new ExampleVerb();
        exampleVerbPast.setVerbSpanish("Caminé");
        exampleVerbPast.setVerbEnglish("Walked");
        exampleVerbPast.setPhraseAffirmativeSpanish("Él y yo caminamos juntos.");
        exampleVerbPast.setPhraseAffirmativeEnglish("He and I walked together..");
        exampleVerbPast.setPhraseNegativeSpanish("Él y yo no caminamos juntos.");
        exampleVerbPast.setPhraseNegativeEnglish("He and I don't walk together.");
        exampleVerbPast.setPhraseQuestionSpanish("¿El caminó por el bosque?");
        exampleVerbPast.setPhraseQuestionEnglish("Did he walk through the woods?");

        ExampleVerb exampleVerbFuture = new ExampleVerb();
        exampleVerbFuture.setVerbSpanish("Caminaré");
        exampleVerbFuture.setVerbEnglish("Will walk");
        exampleVerbFuture.setPhraseAffirmativeSpanish("Caminaré hacia mi casa");
        exampleVerbFuture.setPhraseAffirmativeEnglish("I will walk to my house");
        exampleVerbFuture.setPhraseNegativeSpanish("No caminaré hacia mi casa");
        exampleVerbFuture.setPhraseNegativeEnglish("I won't walk home");
        exampleVerbFuture.setPhraseQuestionSpanish("¿Caminarás a la tienda?");
        exampleVerbFuture.setPhraseQuestionEnglish("Will you walk to the store?");

        verb.setExampleVerbPresent(exampleVerbPresent);
        verb.setExampleVerbPast(exampleVerbPast);
        verb.setExampleVerbFuture(exampleVerbFuture);
        viewModel.fillDb(verb);
    }
}