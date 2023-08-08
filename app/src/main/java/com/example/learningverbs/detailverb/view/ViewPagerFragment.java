package com.example.learningverbs.detailverb.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.learningverbs.R;
import com.example.learningverbs.databinding.FragmentVerbListBinding;
import com.example.learningverbs.databinding.FragmentViewPagerBinding;
import com.example.learningverbs.detailverb.viewmodel.VerbDetailViewModel;
import com.example.learningverbs.listverbs.viewmodel.VerbListViewModel;
import com.example.learningverbs.model.ExampleVerb;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.utils.BaseFragment;
import com.example.learningverbs.utils.constants.Constants;

import java.util.ArrayList;

public class ViewPagerFragment extends BaseFragment<FragmentViewPagerBinding, VerbDetailViewModel> {

    public static final String ARG_OBJECT_VERB_EXAMPLE = "verbExample";


    @Override
    protected VerbDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(VerbDetailViewModel.class);
    }

    @NonNull
    @Override
    protected FragmentViewPagerBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentViewPagerBinding.inflate(layoutInflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null && args.containsKey(ARG_OBJECT_VERB_EXAMPLE)) {
            ExampleVerb exampleVerb = (ExampleVerb) getArguments().getSerializable(ARG_OBJECT_VERB_EXAMPLE);

            if(exampleVerb != null){
                binding.verbSpanish.setText(exampleVerb.getVerbSpanish()) ;
                binding.verbEnglish.setText(exampleVerb.getVerbEnglish());
                binding.txtExampleAffirmativeSpanish.setText(exampleVerb.getPhraseAffirmativeSpanish());
                binding.txtExampleAffirmativeEnglish.setText(exampleVerb.getPhraseAffirmativeEnglish());
                binding.txtExampleNegativeSpanish.setText(exampleVerb.getPhraseNegativeSpanish());
                binding.txtExampleNegativeEnglish.setText(exampleVerb.getPhraseNegativeEnglish());
                binding.txtExampleQuestionSpanish.setText(exampleVerb.getPhraseQuestionSpanish());
                binding.txtExampleQuestionEnglish.setText(exampleVerb.getPhraseQuestionEnglish());
            }



        } else {
            Log.e("NO Contiene llave", "NO Contiene la Key");
        }

    }


}
