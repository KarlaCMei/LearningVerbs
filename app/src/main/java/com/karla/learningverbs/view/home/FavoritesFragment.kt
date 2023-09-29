package com.karla.learningverbs.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.karla.learningverbs.databinding.FragmentFavoritesBinding
import com.karla.learningverbs.model.Verb
import com.karla.learningverbs.utils.base.BaseFragment
import com.karla.learningverbs.utils.constants.Constants
import com.karla.learningverbs.view.home.adapters.VerbAdapter
import com.karla.learningverbs.view.verd_detail.VerbDetailActivity
import com.karla.learningverbs.viewmodel.FavoritesViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding?, FavoritesViewModel?>() {
    private var adapterListVerbs: VerbAdapter? = null
    override fun createViewModel(): FavoritesViewModel {
        return ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    override fun createViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseDatabase.getInstance().reference.keepSynced(true)
        listFavoriteVerbDataBase
        observers()
    }

    private val listFavoriteVerbDataBase: Unit
        private get() {
            viewModel!!.getFavoriteVerbListElement()
        }

    private fun observers() {
        viewModel?.listResultsFavoriteVerbs?.observe(this, Observer { verbs ->
            if (verbs != null && verbs.isNotEmpty()) {
                binding?.msgEmptyListVerb?.visibility = View.GONE
                binding?.imgEmptyVerbs?.visibility = View.GONE
                binding?.listVerbsFavorites?.visibility = View.VISIBLE
                adapterListVerbs = VerbAdapter(verbs) { verb ->
                    val detailActivity = Intent(requireActivity(), VerbDetailActivity::class.java)
                    detailActivity.putExtra(Constants.VERB, verb)
                    startActivity(detailActivity)
                }
                binding?.listVerbsFavorites?.adapter = adapterListVerbs
            } else {
                binding?.msgEmptyListVerb?.visibility = View.VISIBLE
                binding?.imgEmptyVerbs?.visibility = View.VISIBLE
                binding?.listVerbsFavorites?.visibility = View.GONE
            }
        })
    }

}