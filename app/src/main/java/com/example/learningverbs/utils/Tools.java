package com.example.learningverbs.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.learningverbs.utils.constants.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;


public class Tools {
    private static SharedPreferences preferences = LearningApplication.getMyApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

    public static void showSnackMessage(View myActivity, String message) {
        Snackbar.make(myActivity, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void showToastMessage(String message) {
        Toast.makeText(LearningApplication.getMyApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void setStringPreference(String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getStringPreference(String name) {
        return preferences.getString(name, "");
    }

    public static byte[] getImage(Context ctx, String path, int width, int height) {
        final File file_thumb_path = new File(path);

        Bitmap thumb_bitmap = null;
        byte[] thumb_byte = null;
        try {
            thumb_bitmap = new Compressor(ctx)
                    .setMaxWidth(width)
                    .setMaxHeight(height)
                    .setQuality(75)
                    .compressToBitmap(file_thumb_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        thumb_byte = baos.toByteArray();


        return thumb_byte;

    }
}
