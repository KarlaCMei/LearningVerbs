package com.karla.learningverbs.utils;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class LearningApplication2 extends Application {
  private static Context myApplicationContext;
  public static LearningApplication2 instance;

  public LearningApplication2(){
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

  public static LearningApplication2 getInstance(){
    if(instance == null )instance = new LearningApplication2();
    return instance;
  }

  public String getApplicationName() {
    return LearningApplication2.getInstance().getApplicationInfo().loadLabel(getBaseContext().getPackageManager()).toString();
  }

}
