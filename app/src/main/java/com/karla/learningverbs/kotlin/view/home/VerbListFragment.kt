package com.karla.learningverbs.kotlin.view.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.learningverbs.databinding.FragmentVerbListBinding
import com.karla.learningverbs.kotlin.listeners.OnClicVerbListener
import com.karla.learningverbs.kotlin.utils.base.BaseFragment
import com.karla.learningverbs.kotlin.utils.constants.Constants
import com.karla.learningverbs.kotlin.view.verbdetail.VerbDetailActivity
import com.karla.learningverbs.kotlin.viewmodel.VerbListViewModel
import com.karla.learningverbs.model.Verb
import com.karla.learningverbs.view.home.adapters.VerbAdapter2

class VerbListFragment :
    BaseFragment<FragmentVerbListBinding, VerbListViewModel>(), TextWatcher {
    private var adapterListVerbs: VerbAdapter2? = null
    var handler: Handler? = null
        get() {
            if (field == null) {
                field = Handler()
            }
            return field
        }
        private set

    override fun createViewModel(): VerbListViewModel {
        return ViewModelProvider(this).get(VerbListViewModel::class.java)
    }

    override fun createViewBinding(
        layoutInflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentVerbListBinding {
        return FragmentVerbListBinding.inflate(layoutInflater!!, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.txtSearchVerb.addTextChangedListener(this)
        getVerbs(null)
        observers()
    }

    fun getVerbs(name: String?) {
        handler!!.removeCallbacksAndMessages(null)
        if (name != null) {
            handler!!.postDelayed({ viewModel!!.getSearchVerb(name) }, 1000)
        } else {
            viewModel!!.getSearchVerb(null)
        }
    }

    private fun observers() {
        viewModel!!.getListResultsVerbs().observe(this, Observer<List<Verb?>> { verbs ->
            if (verbs != null && verbs.isNotEmpty()) {
                binding?.msgNoResultsFound?.visibility = View.GONE
                binding?.imgNoResultsFound?.visibility = View.GONE
                binding?.listVerbs?.visibility = View.VISIBLE
                adapterListVerbs = VerbAdapter2(verbs, object : OnClicVerbListener {
                    override fun onVerbClicListener(verb: Verb?) {
                        val detailActivity = Intent(
                            requireActivity(),
                            VerbDetailActivity::class.java
                        )
                        detailActivity.putExtra(Constants.VERB, verb)
                        startActivity(detailActivity)
                    }
                })
                binding?.listVerbs?.adapter = adapterListVerbs
            } else {
                binding?.msgNoResultsFound?.visibility = View.VISIBLE
                binding?.imgNoResultsFound?.visibility = View.VISIBLE
                binding?.listVerbs?.visibility = View.GONE
            }
        })
    }



        /*viewModel.getResultVerbAdd().observe(this, new Observer<Boolean>() {
             @Override
             public void onChanged(Boolean aBoolean) {
                 if (aBoolean) {
                     binding.msgNoResultsFound.setVisibility(View.GONE);
                     binding.imgNoResultsFound.setVisibility(View.GONE);
                     binding.listVerbs.setVisibility(View.VISIBLE);
                 } else {
                     binding.msgNoResultsFound.setVisibility(View.VISIBLE);
                     binding.imgNoResultsFound.setVisibility(View.VISIBLE);
                     binding.listVerbs.setVisibility(View.GONE);

                 }
             }
         });*/


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        if (binding!!.txtSearchVerb.hasFocus()) getVerbs(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {}
}