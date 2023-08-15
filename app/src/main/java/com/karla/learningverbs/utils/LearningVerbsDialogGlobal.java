package com.karla.learningverbs.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.karla.learningverbs.R;
import com.karla.learningverbs.databinding.ItemDialogCloseSesionBinding;

public class LearningVerbsDialogGlobal {

    public static void dialogGlobal(AppCompatActivity context, String title, String message,View.OnClickListener onClickListener){
        dialogGlobal(context,title,message,null,onClickListener,null);
    }

    public static void dialogGlobal(AppCompatActivity context, String title, String message,View.OnClickListener onClickListener,View.OnClickListener onCancelListener){
        dialogGlobal(context,title,message,null,onClickListener,onCancelListener);
    }
    public static void dialogGlobal(AppCompatActivity context, String title, String message,Integer image,View.OnClickListener onClickListener, View.OnClickListener onCancelListener){
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = context.getLayoutInflater();
        ItemDialogCloseSesionBinding binding = ItemDialogCloseSesionBinding.inflate(inflater);
        View view = binding.getRoot();
        dialog.setView(view);
        dialog.setCancelable(false);
        binding.titleDialog.setText(title);
        binding.msgDialog.setText(message);

        if(image != null){
            binding.imageView.setImageResource(image);
            binding.imageView.setVisibility(View.VISIBLE);
        }
        if(onCancelListener != null){
            binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCancelListener.onClick(view);
                    dialog.dismiss();
                }
            });
            binding.btnCancel.setVisibility(View.VISIBLE);
        }
        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showDialogAccept(AppCompatActivity context, View.OnClickListener onClickListener) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dialog_close_sesion, null);

        dialog.setView(view);
        view.findViewById(R.id.btnAccept).setOnClickListener(onClickListener);

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialogTakePhoto(AppCompatActivity context,OnClickDialogListener onClickDialogListener, OnClickDialogListener onClickListener) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_take_photo, null);
        dialog.setView(view);
        view.findViewById(R.id.btnOpenGalery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDialogListener.onClick(view, dialog);
            }
        });
        view.findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view, dialog);
            }
        });
        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public interface OnClickDialogListener {
        void onClick(View var1, AlertDialog dialog);
    }

}
