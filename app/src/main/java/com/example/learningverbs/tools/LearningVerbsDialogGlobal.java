package com.example.learningverbs.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.learningverbs.R;

public class LearningVerbsDialogGlobal {

    public static void showDialogAccept(AppCompatActivity context, View.OnClickListener onClickListener) {

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dialog_close_sesion, null);
        dialog.setView(view);
        view.findViewById(R.id.btnTakePhoto).setOnClickListener(onClickListener);
        view.findViewById(R.id.btnOpenGalery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialogChange(Fragment fragment, View.OnClickListener onClickListener,View.OnClickListener onClickListenerIrregular ) {
        Context context = fragment.requireContext();
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_dialog_change_verb, null);
        dialog.setView(view);
        view.findViewById(R.id.btnRegular).setOnClickListener(onClickListener);
        view.findViewById(R.id.btnIrregular).setOnClickListener(onClickListenerIrregular);
        dialog.show();
    }

    public static void showDialogTakePhoto(AppCompatActivity context, View.OnClickListener onClickListener,OnClickDialogListener onClickListenerIrregular ) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_take_photo, null);
        dialog.setView(view);
        view.findViewById(R.id.btnOpenGalery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onClickListenerIrregular.on(onClickListener);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListenerIrregular.onClick(view,dialog);
            }
        });
        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
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
