package com.example.learningverbs.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.example.learningverbs.R;
import com.example.learningverbs.databinding.FragmentHomeBinding;

public abstract class BaseFragment<BINDING extends ViewBinding, VM extends BaseViewModel> extends Fragment {

    protected VM viewModel;
    protected BINDING binding;
    private Dialog dialog;

    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater, ViewGroup container);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = createViewBinding(LayoutInflater.from(requireContext()), container);
        viewModel = createViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observers();
    }

    private void observers() {
        viewModel.loading.observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });

        viewModel.msgError.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (getActivity() instanceof BaseActivity) {
                    ((BaseActivity) getActivity()).showMessageError(s);
                }
            }
        });
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/

    private void showProgress() {

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgress();
        }

        /*if(isAdded()){
            dialog = new Dialog(requireActivity(), R.style.customLottie);
            dialog.setContentView(R.layout.loading_view);
            if(!dialog.isShowing())dialog.show();
        }*/
    }

    private void hideProgress() {

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideProgress();
        }
       // if (dialog != null) dialog.dismiss();
    }


    public void showMessageError(String msgError) {
        Toast.makeText(requireContext(), msgError, Toast.LENGTH_SHORT).show();
    }

}
