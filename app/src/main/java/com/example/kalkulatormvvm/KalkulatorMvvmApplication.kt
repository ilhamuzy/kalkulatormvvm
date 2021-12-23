package com.example.kalkulatormvvm

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class KalkulatorMvvmApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.i("APP OnCreate")
    }
}