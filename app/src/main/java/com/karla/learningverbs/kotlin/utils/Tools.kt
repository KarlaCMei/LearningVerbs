package com.karla.learningverbs.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.karla.learningverbs.kotlin.utils.constants.Constants
import id.zelory.compressor.Compressor
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

object Tools {
    private val preferences = LearningApplication2.getMyApplicationContext().getSharedPreferences(
        Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE
    )

    fun showSnackMessage(myActivity: View?, message: String?) {
        Snackbar.make(myActivity!!, message!!, Snackbar.LENGTH_SHORT).show()
    }

    fun showToastMessage(message: String?) {
        Toast.makeText(LearningApplication2.getMyApplicationContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    fun setStringPreference(name: String?, value: String?) {
        val editor = preferences.edit()
        editor.putString(name, value)
        editor.apply()
    }

    fun getStringPreference(name: String?): String? {
        return preferences.getString(name, "")
    }

    fun getImage(ctx: Context?, path: String?, width: Int, height: Int): ByteArray? {
        val file_thumb_path = File(path)
        var thumb_bitmap: Bitmap? = null
        var thumb_byte: ByteArray? = null
        try {
            thumb_bitmap = Compressor(ctx)
                .setMaxWidth(width)
                .setMaxHeight(height)
                .setQuality(75)
                .compressToBitmap(file_thumb_path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val baos = ByteArrayOutputStream()
        thumb_bitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        thumb_byte = baos.toByteArray()
        return thumb_byte
    }
}