package com.karla.learningverbs.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.karla.learningverbs.R;
import com.karla.learningverbs.detailverb.view.ViewPagerFragment;
import com.karla.learningverbs.model.ExampleVerb;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.constants.Constants;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] arrayTitle = {LearningApplication.getInstance().getString(R.string.title_pager_present), LearningApplication.getInstance().getString(R.string.title_pager_past),LearningApplication.getInstance().getString(R.string.title_pager_future)};
    private ArrayList<ExampleVerb> exampleVerbs = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<ExampleVerb> exampleVerbs) {
        super(fm);
        this.exampleVerbs = exampleVerbs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_OBJECT_VERB_EXAMPLE, exampleVerbs.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return arrayTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle[position];
    }
}
