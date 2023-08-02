package com.example.learningverbs.detailverb.viewmodel;

import com.example.learningverbs.detailverb.repository.VerbDetailRepository;
import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;
import com.example.learningverbs.utils.BaseViewModel;

public class VerbDetailViewModel extends BaseViewModel {

    private VerbDetailRepository verbDetailRepository;
    public VerbDetailViewModel() {
        verbDetailRepository = VerbDetailRepository.getInstance();
    }
}
