package com.karla.learningverbs.view.verd_detail.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.karla.learningverbs.R;
import com.karla.learningverbs.view.verd_detail.ViewPagerFragment;
import com.karla.learningverbs.model.ExampleVerb;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.constants.Constants;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] arrayTitle = {LearningApplication.getInstance().getString(R.string.title_pager_present), LearningApplication.getInstance().getString(R.string.title_pager_past),LearningApplication.getInstance().getString(R.string.title_pager_future)};
    private ArrayList<ExampleVerb> exampleVerbs = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fm, ArrayList<ExampleVerb> exampleVerbs) {
        super(fm);
        this.exampleVerbs = exampleVerbs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_OBJECT_VERB_EXAMPLE, exampleVerbs.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return arrayTitle.length;
    }

    public CharSequence getPageTitle(int position) {
        return arrayTitle[position];
    }
}
