package com.example.learningverbs.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.learningverbs.detailverb.view.ViewPagerFragment;
import com.example.learningverbs.model.ExampleVerb;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Title> arrayTitle ;
    public ViewPagerAdapter(@NonNull FragmentManager fm,ArrayList<Title> arrayTitle) {
        super(fm);
        this.arrayTitle = arrayTitle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ViewPagerFragment.ARG_OBJECT, arrayTitle.get(position).titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return arrayTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position).titulo;
    }
}
