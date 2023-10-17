package com.karla.learningverbs.kotlin.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding, VM : BaseViewModel>: Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: BINDING

    protected abstract fun createViewModel(): VM

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater?, container: ViewGroup?): BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding(LayoutInflater.from(requireContext()), container!!)
        viewModel = createViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
        viewModel.loading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading == true) {
                showProgress()
            } else {
                hideProgress()
            }
        })

        viewModel.msgError.observe(requireActivity(), Observer { message ->
            if (activity is BaseActivity<*, *>) {
                (activity as BaseActivity<*, *>).showMessageError(message)
            }
        })
    }

    private fun showProgress() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showProgress()
        }
    }


    private fun hideProgress() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>?)!!.hideProgress()
        }
    }
}