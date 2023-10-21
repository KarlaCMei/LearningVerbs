package com.karla.learningverbs.kotlin.utils

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.karla.learningverbs.utils.LearningApplication2

class LearningApplication: Application() {
    companion object {
        var myApplicationContext: Context? = null
        @JvmField
        var appInstance: LearningApplication? = null

        @JvmStatic fun getInstance(): LearningApplication {
            return appInstance as LearningApplication
        }
    }

    val applicationName: String
        get() = LearningApplication2.getInstance()!!.applicationInfo.loadLabel(baseContext.packageManager).toString()

    override fun onCreate() {
        super.onCreate()
        appInstance=this;
    }

}