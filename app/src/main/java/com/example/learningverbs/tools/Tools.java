package com.example.learningverbs.tools;


import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import id.zelory.compressor.Compressor;


public class Tools {
    private static final String PREFERENCE_FILE_KEY = "learningPreference";
    private static SharedPreferences preferences = com.example.learningverbs.tools.LearningApplication.getMyApplicationContext().getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

    public static void showSnackMessage(View myActivity, String message) {
        Snackbar.make(myActivity, message, Snackbar.LENGTH_SHORT).show();
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
        // if(path == null) return null;
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

        //if (thumb_bitmap != null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        thumb_byte = baos.toByteArray();



        return thumb_byte;

    }
}
