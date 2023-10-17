package com.karla.learningverbs.kotlin.view.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.karla.learningverbs.databinding.FragmentHomeBinding
import com.karla.learningverbs.kotlin.utils.base.BaseFragment
import com.karla.learningverbs.kotlin.utils.constants.Constants
import com.karla.learningverbs.kotlin.view.verbdetail.VerbDetailActivity
import com.karla.learningverbs.kotlin.viewmodel.HomeFragmentViewModel
import com.karla.learningverbs.model.Verb


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {
    private var verbResultDo: Verb? = null
    private var verbResultHave: Verb? = null
    private var verbResultBe: Verb? = null
    private var verbDay: Verb? = null
    override fun createViewModel(): HomeFragmentViewModel {
        return ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    override fun createViewBinding(
        layoutInflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater!!, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        elementRandom
        listDataBase
        observers()
        observer()
        binding!!.btnVerbHave.cardVerbHave.setOnClickListener {
            if (verbResultHave != null) {
                val intent = Intent(requireActivity(), VerbDetailActivity::class.java)
                intent.putExtra(Constants.VERB, verbResultHave)
                startActivity(intent)
            }
        }
        binding!!.btnVerbToBe.cardVerbToBe.setOnClickListener {
            if (verbResultBe != null) {
                val intent = Intent(requireContext(), VerbDetailActivity::class.java)
                intent.putExtra(Constants.VERB, verbResultBe)
                startActivity(intent)
            }
        }
        binding!!.btnVerbOfTheDay.btnSignUp.setOnClickListener { view1 ->
            if (verbDay != null) {
                val intent = Intent(requireContext(), VerbDetailActivity::class.java)
                intent.putExtra(Constants.VERB, verbDay)
                startActivity(intent)
            }
        }
        binding!!.btnVerbDo.cardVerbDo.setOnClickListener {
            if (verbResultDo != null) {
                val intent = Intent(requireContext(), VerbDetailActivity::class.java)
                intent.putExtra(Constants.VERB, verbResultDo)
                startActivity(intent)
            }
        }
    }

    private val listDataBase: Unit
        private get() {
            viewModel!!.getListElement()
        }

    private fun observers() {
        viewModel!!.getListResultsVerbs().observe(this, object : Observer<List<Verb?>?> {
            override fun onChanged(verbs: List<Verb?>?) {
                for (verb in verbs!!) {
                    when (verb?.verbId) {
                        "1690850399354" -> {
                            verbResultDo = verb
                            Glide.with(binding!!.btnVerbDo.imgVerb.context)
                                .load(verb?.image)
                                .into(binding!!.btnVerbDo.imgVerb)
                        }
                        "1690850850666" -> {
                            verbResultHave = verb
                            Glide.with(binding!!.btnVerbHave.imgVerb.context)
                                .load(verb?.image)
                                .into(binding!!.btnVerbHave.imgVerb)
                        }
                        else -> {
                            verbResultBe = verb
                            Glide.with(binding!!.btnVerbToBe.imgVerb.context)
                                .load(verb?.image)
                                .into(binding!!.btnVerbToBe.imgVerb)
                        }
                    }
                }
            }
        })
    }


    private val elementRandom: Unit
        private get() {
            viewModel!!.getListVerbs()
        }

    fun observer() {
        viewModel!!.getElementRandom().observe(this, object : Observer<Verb> {
            override fun onChanged(verb: Verb) {
                verbDay = verb
                binding!!.btnVerbOfTheDay.btnSignUp.text = verb.verbSpanishPresent
            }
        })
    }
}