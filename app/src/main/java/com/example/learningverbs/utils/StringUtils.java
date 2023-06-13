package com.example.learningverbs.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean validateEmail(CharSequence text) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return text != null && !text.toString().equals("") && pattern.matcher(text.toString().trim()).matches();
    }

    public static boolean validatePassword(CharSequence text) {
        return (text != null && !text.toString().equals("") && text.toString().length() > 6);
    }

    public static boolean validateSamePassword(CharSequence text, CharSequence text2) {
        return (text != null && !text.toString().equals("") && !text.equals(text2));
    }

}
