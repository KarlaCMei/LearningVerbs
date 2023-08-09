package com.example.learningverbs.utils;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class LearningApplication extends Application {
  private static Context myApplicationContext;
  public static LearningApplication instance;

  public LearningApplication (){
    instance = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    myApplicationContext = this;


  }
  public static Context getMyApplicationContext(){
    return myApplicationContext;
  }

  public static LearningApplication getInstance(){
    if(instance == null )instance = new LearningApplication();
    return instance;
  }

  public String getApplicationName() {
    return LearningApplication.getInstance().getApplicationInfo().loadLabel(getBaseContext().getPackageManager()).toString();
  }

}
