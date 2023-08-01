package com.example.learningverbs.detailverb.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.FragmentVerbListBinding;
import com.example.learningverbs.databinding.FragmentViewPagerBinding;
import com.example.learningverbs.detailverb.viewmodel.VerbDetailViewModel;
import com.example.learningverbs.listverbs.viewmodel.VerbListViewModel;
import com.example.learningverbs.utils.BaseFragment;

public class ViewPagerFragment extends BaseFragment<FragmentViewPagerBinding, VerbDetailViewModel> {

    public static final String ARG_OBJECT = "objet";

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

        if(args != null){
        }

    }
}
