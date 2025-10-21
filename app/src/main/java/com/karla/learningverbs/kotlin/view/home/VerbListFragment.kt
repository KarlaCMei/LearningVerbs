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
import com.karla.learningverbs.kotlin.view.home.adapters.VerbAdapter
import com.karla.learningverbs.kotlin.view.verbdetail.VerbDetailActivity
import com.karla.learningverbs.kotlin.viewmodel.VerbListViewModel
import com.karla.learningverbs.model.ExampleVerb
import com.karla.learningverbs.model.Verb

class VerbListFragment :
    BaseFragment<FragmentVerbListBinding, VerbListViewModel>(), TextWatcher {
    private var adapterListVerbs: VerbAdapter? = null
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
        //fillDataBase()
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
        viewModel!!.getListResultsVerbs().observe(this, Observer<List<Verb?>?> { verbs ->
            if (verbs != null && verbs.isNotEmpty()) {
                binding?.msgNoResultsFound?.visibility = View.GONE
                binding?.imgNoResultsFound?.visibility = View.GONE
                binding?.listVerbs?.visibility = View.VISIBLE
                adapterListVerbs = VerbAdapter(verbs, object : OnClicVerbListener {
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

    /*fun fillDataBase() {
        val currentTimeMillis = System.currentTimeMillis()
        val verb = Verb()
        verb.verbId = currentTimeMillis.toString()
        verb.image = "https://previews.123rf.com/images/blueringmedia/blueringmedia1410/blueringmedia141000800/32692194-illustration-of-a-simple-sketch-of-a-man-using-a-mobile-phone-on-a-white-background.jpg"
        verb.verbSpanishPresent = "Usar"
        verb.verbEnglishPresent = "Use"
        verb.regular = true

        val exampleVerbPresent = ExampleVerb().apply {
            verbSpanish = "Usar"
            verbEnglish = "Use"
            phraseAffirmativeSpanish = "Ella usa su computadora todos los días."
            phraseAffirmativeEnglish = "She uses her computer every day."
            phraseNegativeSpanish = "Ella no usa su computadora todos los días."
            phraseNegativeEnglish = "She does not use her computer every day."
            phraseQuestionSpanish = "¿Usa ella su computadora todos los días?"
            phraseQuestionEnglish = "Does she use her computer every day?"
        }

        val exampleVerbPast = ExampleVerb().apply {
            verbSpanish = "Usó"
            verbEnglish = "Used"
            phraseAffirmativeSpanish = "Ayer, usó su teléfono para llamar a su amigo."
            phraseAffirmativeEnglish = "Yesterday, she used her phone to call her friend."
            phraseNegativeSpanish = "Ayer, ella no usó su teléfono para llamar a su amigo."
            phraseNegativeEnglish = "Yesterday, she did not use her phone to call her friend."
            phraseQuestionSpanish = "¿Usó ella su teléfono para llamar a su amigo ayer?"
            phraseQuestionEnglish = "Did she use her phone to call her friend yesterday?"
        }

        val exampleVerbFuture = ExampleVerb().apply {
            verbSpanish = "Usará"
            verbEnglish = "Will use"
            phraseAffirmativeSpanish = "Mañana, usará su cuaderno para tomar notas."
            phraseAffirmativeEnglish = "Tomorrow, she will use her notebook to take notes."
            phraseNegativeSpanish = "Mañana, ella no usará su cuaderno para tomar notas."
            phraseNegativeEnglish = "Tomorrow, she will not use her notebook to take notes."
            phraseQuestionSpanish = "¿Usará ella su cuaderno mañana?"
            phraseQuestionEnglish = "Will she use her notebook tomorrow?"
        }

        verb.exampleVerbPresent = exampleVerbPresent
        verb.exampleVerbPast = exampleVerbPast
        verb.exampleVerbFuture = exampleVerbFuture
        viewModel.fillDataBase(verb)
    }*/
















    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        if (binding!!.txtSearchVerb.hasFocus()) getVerbs(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {}
}