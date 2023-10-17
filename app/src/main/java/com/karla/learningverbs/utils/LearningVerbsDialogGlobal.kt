package com.karla.learningverbs.utils

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ItemDialogCloseSesionBinding

object LearningVerbsDialogGlobal {
    fun dialogGlobal(
        context: AppCompatActivity,
        title: String?,
        message: String?,
        onClickListener: View.OnClickListener
    ) {
        dialogGlobal(context, title, message, null, onClickListener, null)
    }

    fun dialogGlobal(
        context: AppCompatActivity,
        title: String?,
        message: String?,
        onClickListener: View.OnClickListener,
        onCancelListener: View.OnClickListener?
    ) {
        dialogGlobal(context, title, message, null, onClickListener, onCancelListener)
    }

    fun dialogGlobal(
        context: AppCompatActivity,
        title: String?,
        message: String?,
        image: Int?,
        onClickListener: View.OnClickListener,
        onCancelListener: View.OnClickListener?
    ) {
        val dialog = AlertDialog.Builder(context).create()
        val inflater = context.layoutInflater
        val binding = ItemDialogCloseSesionBinding.inflate(inflater)
        val view = binding.root
        dialog.setView(view)
        dialog.setCancelable(false)
        binding.titleDialog.text = title
        binding.msgDialog.text = message
        if (image != null) {
            binding.imageView.setImageResource(image)
            binding.imageView.visibility = View.VISIBLE
        }
        if (onCancelListener != null) {
            binding.btnCancel.setOnClickListener { view ->
                onCancelListener.onClick(view)
                dialog.dismiss()
            }
            binding.btnCancel.visibility = View.VISIBLE
        }
        binding.btnAccept.setOnClickListener { view ->
            onClickListener.onClick(view)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showDialogAccept(context: AppCompatActivity, onClickListener: View.OnClickListener?) {
        val dialog = AlertDialog.Builder(context).create()
        val inflater = context.layoutInflater
        val view = inflater.inflate(R.layout.item_dialog_close_sesion, null)
        dialog.setView(view)
        view.findViewById<View>(R.id.btnAccept).setOnClickListener(onClickListener)
        view.findViewById<View>(R.id.btnCancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    fun showDialogTakePhoto(
        context: AppCompatActivity?,
        onClickDialogListener: OnClickDialogListener,
        onClickListener: OnClickDialogListener
    ) {
        val dialog = AlertDialog.Builder(context).create()
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_take_photo, null)
        dialog.setView(view)
        view.findViewById<View>(R.id.btnOpenGalery)
            .setOnClickListener { view -> onClickDialogListener.onClick(view, dialog) }
        view.findViewById<View>(R.id.btnTakePhoto)
            .setOnClickListener { view -> onClickListener.onClick(view, dialog) }
        view.findViewById<View>(R.id.buttonCancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    interface OnClickDialogListener {
        fun onClick(var1: View, dialog: AlertDialog)
    }
}