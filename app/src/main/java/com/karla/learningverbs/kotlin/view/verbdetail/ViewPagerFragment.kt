package com.karla.learningverbs.kotlin.view.verbdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karla.learningverbs.databinding.FragmentViewPagerBinding
import com.karla.learningverbs.kotlin.utils.constants.Constants
import com.karla.learningverbs.model.ExampleVerb

class ViewPagerFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerBinding
    /* override fun createViewModel(): VerbDetailViewModel {
         return ViewModelProvider(this)[VerbDetailViewModel::class.java]
     }


     override fun createViewBinding(
         layoutInflater: LayoutInflater,
         container: ViewGroup
     ): FragmentViewPagerBinding {
         return FragmentViewPagerBinding.inflate(layoutInflater, container, false)
     }*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null && args.containsKey(Constants.ARG_OBJECT_VERB_EXAMPLE)) {
            val exampleVerb =
                arguments!!.getSerializable(Constants.ARG_OBJECT_VERB_EXAMPLE) as ExampleVerb?
            if (exampleVerb != null) {
                binding!!.verbSpanish.text = exampleVerb.verbSpanish
                binding!!.verbEnglish.text = exampleVerb.verbEnglish
                binding!!.txtExampleAffirmativeSpanish.text = exampleVerb.phraseAffirmativeSpanish
                binding!!.txtExampleAffirmativeEnglish.text = exampleVerb.phraseAffirmativeEnglish
                binding!!.txtExampleNegativeSpanish.text = exampleVerb.phraseNegativeSpanish
                binding!!.txtExampleNegativeEnglish.text = exampleVerb.phraseNegativeEnglish
                binding!!.txtExampleQuestionSpanish.text = exampleVerb.phraseQuestionSpanish
                binding!!.txtExampleQuestionEnglish.text = exampleVerb.phraseQuestionEnglish
            }
        } else {
            Log.e("NO Contiene llave", "NO Contiene la Key")
        }
    }
}