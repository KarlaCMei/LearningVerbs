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
}
