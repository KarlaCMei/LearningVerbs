package com.example.learningverbs.detailverb.repository;

import com.example.learningverbs.listverbs.repository.VerbListFragmentRepository;

public class VerbDetailRepository {
    public static VerbDetailRepository instance;
    public static VerbDetailRepository getInstance() {
        if (instance == null) instance = new VerbDetailRepository();
        return instance;
    }
}
