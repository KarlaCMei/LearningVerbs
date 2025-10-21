package com.karla.learningverbs.kotlin.utils

import android.app.Application
import android.content.Context

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
        get() = LearningApplication.getInstance().applicationInfo.loadLabel(baseContext.packageManager).toString()

    override fun onCreate() {
        super.onCreate()
        appInstance=this;
    }

}