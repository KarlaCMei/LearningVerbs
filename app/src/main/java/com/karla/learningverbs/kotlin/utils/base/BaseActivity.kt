package com.karla.learningverbs.kotlin.utils.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.karla.learningverbs.R

abstract class BaseActivity<BINDING : ViewBinding, VM : BaseViewModel> :AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: BINDING
    private lateinit var dialog: Dialog


    protected abstract fun createViewModel(): VM

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater?): BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = createViewModel()
        dialog = Dialog(this@BaseActivity, R.style.customLottie)
        observers()
    }

    private fun observers() {
        viewModel.loading.observe(this, object : Observer<Boolean?> {
            override fun onChanged(isLoading: Boolean?) {
                if (isLoading!!) {
                    showProgress()
                } else {
                    hideProgress()
                }            }
        })
        viewModel.msgError.observe(this, object : Observer<String?> {
            override fun onChanged(msgError: String?) {
                showMessageError(msgError!!)
            }
        })
    }

    open fun showProgress() {
        dialog!!.setContentView(R.layout.loading_view)
        if (!dialog!!.isShowing) dialog!!.show()
    }

    open fun hideProgress() {
        if (dialog!!.isShowing) dialog!!.dismiss()
    }


    open fun showMessageError(msgError: String?) {
        Toast.makeText(this@BaseActivity, msgError, Toast.LENGTH_SHORT).show()
    }

}