package com.example.learningverbs.tools;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class LearningApplication extends Application {
  private static Context myApplicationContext;

  @Override
  public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    myApplicationContext = this;


  }
  public static Context getMyApplicationContext(){
    return myApplicationContext;
  }

}
