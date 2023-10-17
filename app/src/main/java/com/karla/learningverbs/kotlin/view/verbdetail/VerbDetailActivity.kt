package com.karla.learningverbs.kotlin.view.verbdetail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivityVerbDetailBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.utils.constants.Constants
import com.karla.learningverbs.kotlin.view.verbdetail.adapters.ViewPagerAdapter
import com.karla.learningverbs.kotlin.viewmodel.VerbDetailViewModel
import com.karla.learningverbs.model.ExampleVerb
import com.karla.learningverbs.model.Verb

class VerbDetailActivity : BaseActivity<ActivityVerbDetailBinding, VerbDetailViewModel>() {
    var arrayExampleVerb: ArrayList<ExampleVerb> = ArrayList()
    var viewPagerAdapter: ViewPagerAdapter? = null
    private var verbDetail: Verb? = null
    override fun createViewModel(): VerbDetailViewModel {
        return ViewModelProvider(this)[VerbDetailViewModel::class.java]
    }
    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityVerbDetailBinding {
        return ActivityVerbDetailBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observers()
        readExtra()
        binding.icBack.setOnClickListener { onBackPressed() }
        binding.btnFavoriteVerb.setOnClickListener { view ->
            if (java.lang.Boolean.TRUE == viewModel.getIsFavoriteVerb().value) {
                deleteElement()
            } else {
                viewModel.fillDb(verbDetail, verbDetail!!.verbId)
            }
        }
    }

    private fun readExtra() {
        if (intent.extras!!.containsKey(Constants.VERB)) {
            verbDetail = intent.extras!!.getSerializable(Constants.VERB) as Verb?
            binding.verbSpanish.text = verbDetail!!.verbSpanishPresent
            binding.verbEnglish.text = verbDetail!!.verbEnglishPresent
            Glide.with(binding!!.imgVerb.context).load(verbDetail!!.image).into(
                binding!!.imgVerb
            )
            viewModel!!.responseVerbFavoriteUser(verbDetail!!.verbId)
            arrayExampleVerb.add(verbDetail!!.exampleVerbPresent)
            arrayExampleVerb.add(verbDetail!!.exampleVerbPast)
            arrayExampleVerb.add(verbDetail!!.exampleVerbFuture)
            viewPagerAdapter =
                ViewPagerAdapter(
                    this@VerbDetailActivity,
                    arrayExampleVerb
                )
            binding!!.pager.adapter = viewPagerAdapter
            val tabLayoutMediator = TabLayoutMediator(
                binding!!.tabLayout, binding!!.pager, true
            ) { tab, position -> tab.text = viewPagerAdapter!!.getPageTitle(position) }
            tabLayoutMediator.attach()
            if (verbDetail!!.regular) {
                binding!!.txtIsRegular.setText(R.string.msg_regular)
            } else {
                binding!!.txtIsRegular.setText(R.string.msg_irregular)
            }
        }
    }

    private fun observers() {
        viewModel.getIsFavoriteVerb().observe(this, object : Observer<Boolean> {
            override fun onChanged(aBoolean: Boolean) {
                if (aBoolean) {
                    binding!!.btnFavoriteVerb.imageTintList =
                        ColorStateList.valueOf(resources.getColor(R.color.red))
                } else {
                    binding!!.btnFavoriteVerb.imageTintList =
                        ColorStateList.valueOf(resources.getColor(R.color.teal_800))
                }
            }
        })
    }

    private fun deleteElement() {
        viewModel!!.deleteelement(verbDetail!!.verbId)
    }
}