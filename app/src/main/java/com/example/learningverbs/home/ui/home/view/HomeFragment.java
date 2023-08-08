package com.example.learningverbs.home.ui.home.view;
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

import com.bumptech.glide.Glide;
import com.example.learningverbs.databinding.FragmentHomeBinding;
import com.example.learningverbs.detailverb.view.VerbDetailActivity;
import com.example.learningverbs.home.ui.home.viewmodel.HomeFragmentViewModel;
import com.example.learningverbs.model.ExampleVerb;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseFragment;
import com.example.learningverbs.utils.constants.Constants;

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

        //fillDataBase();
        getElementRandom();
        getListDataBase();
        observers();
        observer();
        binding.btnVerbHave.cardVerbHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verbResultHave != null){
                    Intent intent = new Intent(requireActivity(), VerbDetailActivity.class);
                    intent.putExtra(Constants.VERB, verbResultHave);
                    startActivity(intent);
                }
            }
        });

        binding.btnVerbToBe.cardVerbToBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verbResultBe != null){
                    Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                    intent.putExtra(Constants.VERB, verbResultBe);
                    startActivity(intent);
                }

            }
        });
        binding.btnVerbOfTheDay.btnSignUp.setOnClickListener(view1 -> {
            if(verbDay != null){
                Intent intent = new Intent(requireContext(), VerbDetailActivity.class);
                intent.putExtra(Constants.VERB, verbDay);
                startActivity(intent);
            }
        });

        binding.btnVerbDo.cardVerbDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verbResultDo != null){
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
                    Log.e("Verb ID", "ID" + verbList.getVerbId());
                    if(Objects.equals(verbList.getVerbId(), "1690850399354")){
                        verbResultDo = verbList;
                        Glide.with(binding.btnVerbDo.imgVerb.getContext()).load(verbList.getImage()).into(binding.btnVerbDo.imgVerb);
                    }else if(Objects.equals(verbList.getVerbId(), "1690850850666")){
                        verbResultHave = verbList;
                        Glide.with(binding.btnVerbHave.imgVerb.getContext()).load(verbList.getImage()).into(binding.btnVerbHave.imgVerb);
                    }else{
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

    public void observer(){
        viewModel.getElementRandom().observe(this, new Observer<Verb>() {
            @Override
            public void onChanged(Verb verb) {
                verbDay = verb;
                binding.btnVerbOfTheDay.btnSignUp.setText(verb.getVerbSpanishPresent());
                Log.e("Verbo del dia", "Verbo " + verb.getVerbSpanishPresent());
            }
        });
    }


}



    /*public void fillDataBase() {
        long currentTimeMillis = System.currentTimeMillis();
        Verb verb = new Verb();

        verb.setVerbId(String.valueOf(currentTimeMillis));
        verb.setImage("https://alponiente.com/wp-content/uploads/2020/06/concepto-autocuidado-hombre-actividades_23-2148530061-626x556.jpg");
        verb.setVerbSpanishPresent("Ser/Estar");
        verb.setVerbEnglishPresent("Be");
        verb.setRegular(false);

        ExampleVerb exampleVerbPresent = new ExampleVerb();
        exampleVerbPresent.setVerbSpanish("Ser/Estar");
        exampleVerbPresent.setVerbEnglish("Be");
        exampleVerbPresent.setPhraseAffirmativeSpanish("Tom quiere ser admirado.");
        exampleVerbPresent.setPhraseAffirmativeEnglish("Tom wants to be admired.");
        exampleVerbPresent.setPhraseNegativeSpanish("Tom no quiere ser admirado.");
        exampleVerbPresent.setPhraseNegativeEnglish("Tom doesn't want to be admired.");
        exampleVerbPresent.setPhraseQuestionSpanish("¿Tom quiere ser admirado?");
        exampleVerbPresent.setPhraseQuestionEnglish("Does Tom want to be admired?");


        ExampleVerb exampleVerbPast= new ExampleVerb();
        exampleVerbPast.setVerbSpanish("Was - Were");
        exampleVerbPast.setVerbEnglish("Fui/Estuve");
        exampleVerbPast.setPhraseAffirmativeSpanish("Yo estaba en la fiesta.");
        exampleVerbPast.setPhraseAffirmativeEnglish("I was at the party.");
        exampleVerbPast.setPhraseNegativeSpanish("Yo no estaba en la fiesta.");
        exampleVerbPast.setPhraseNegativeEnglish("I was not at the party");
        exampleVerbPast.setPhraseQuestionSpanish("¿Yo estaba en la fiesta?");
        exampleVerbPast.setPhraseQuestionEnglish("Was I at the party?");

        ExampleVerb exampleVerbFuture = new ExampleVerb();
        exampleVerbFuture.setVerbSpanish("Seré/Estaré");
        exampleVerbFuture.setVerbEnglish("Will be");
        exampleVerbFuture.setPhraseAffirmativeSpanish("Creo que mañana va a hacer sol.");
        exampleVerbFuture.setPhraseAffirmativeEnglish("I think it will be sunny tomorrow.");
        exampleVerbFuture.setPhraseNegativeSpanish("Creo que mañana no va a hacer sol.");
        exampleVerbFuture.setPhraseNegativeEnglish("I think tomorrow will not be sunny.");
        exampleVerbFuture.setPhraseQuestionSpanish("¿Les dijiste a todos dónde será la siguiente reunión?");
        exampleVerbFuture.setPhraseQuestionEnglish("Have you told everyone where the meeting will be?");

        verb.setExampleVerbPresent(exampleVerbPresent);
        verb.setExampleVerbPast(exampleVerbPast);
        verb.setExampleVerbFuture(exampleVerbFuture);
        viewModel.fillDb(verb);
    }*/