package com.example.learningverbs.tools;


import android.content.Context;
import android.content.SharedPreferences;

import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

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

    public static String getFormatUrlImage(Uri photoPath) {
        String photoPathCustom;
        if (photoPath != null && !photoPath.toString().isEmpty()) {
            photoPathCustom = photoPath.toString();
            String originalPieceOfUrl = "s96-c/photo.jpg";
            String originalPieceOfUrlTwitter = "_normal";
            String originalPieceOfUrlFacebook = "graph.facebook";
            String newPieceOfUrlToAdd = "s400-c/photo.jpg";

            if (photoPathCustom.contains(originalPieceOfUrl)) {
                photoPathCustom = photoPathCustom.replace(originalPieceOfUrl, newPieceOfUrlToAdd);
            } else if (photoPathCustom.contains(originalPieceOfUrlTwitter)) {
                photoPathCustom = photoPathCustom.replace(originalPieceOfUrlTwitter, "");
            } else if (photoPathCustom.contains(originalPieceOfUrlFacebook)) {
                photoPathCustom += "?height=500";
            }
        } else {
            photoPathCustom = "";
        }

        return photoPathCustom;
    }


}
