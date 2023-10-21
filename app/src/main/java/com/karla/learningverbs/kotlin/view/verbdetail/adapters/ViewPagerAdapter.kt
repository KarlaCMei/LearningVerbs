package com.karla.learningverbs.kotlin.view.verbdetail.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karla.learningverbs.R
import com.karla.learningverbs.kotlin.utils.constants.Constants
import com.karla.learningverbs.kotlin.view.verbdetail.ViewPagerFragment
import com.karla.learningverbs.model.ExampleVerb
import com.karla.learningverbs.utils.LearningApplication2
import io.reactivex.annotations.NonNull

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private var exampleVerbs :ArrayList<ExampleVerb>) : FragmentStateAdapter(fragmentActivity){

    private val arrayTitle = arrayOf(
        LearningApplication2.getInstance().getString(R.string.title_pager_present),
        LearningApplication2.getInstance().getString(R.string.title_pager_past),
        LearningApplication2.getInstance().getString(R.string.title_pager_future)
    )

    override fun getItemCount(): Int {
        return arrayTitle.size
    }

    @NonNull
    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment()
        val args = Bundle()
        args.putSerializable(Constants.ARG_OBJECT_VERB_EXAMPLE, exampleVerbs[position])
        fragment.arguments = args
        return fragment
    }


    fun getPageTitle(position: Int): CharSequence? {
        return arrayTitle[position]
    }


}