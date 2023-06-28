package com.example.learningverbs.home.ui.listverbs.view;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import com.example.learningverbs.databinding.FragmentVerbListBinding;
import com.example.learningverbs.home.ui.listverbs.viewmodel.VerbListViewModel;
import com.example.learningverbs.utils.BaseFragment;

public class VerbListFragment extends BaseFragment<FragmentVerbListBinding, VerbListViewModel> {

    @Override
    protected VerbListViewModel createViewModel() {
        return new ViewModelProvider(this).get(VerbListViewModel.class);
    }

    @NonNull
    @Override
    protected FragmentVerbListBinding createViewBinding(LayoutInflater layoutInflater, ViewGroup container) {
        return FragmentVerbListBinding.inflate(layoutInflater, container, false);
    }
}