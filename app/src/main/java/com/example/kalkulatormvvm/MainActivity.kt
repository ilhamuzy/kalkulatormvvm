package com.example.kalkulatormvvm

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.kalkulatormvvm.databinding.ActivityMainBinding
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onAppStart() {
                Logger.i("ILHAM ON_START")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onAppDestroy() {
                Logger.i("ILHAM ON_DESTROY")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onAppPause() {
                Logger.i("ILHAM ON_PAUSE")
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        Logger.i("On Destroy")
    }
}