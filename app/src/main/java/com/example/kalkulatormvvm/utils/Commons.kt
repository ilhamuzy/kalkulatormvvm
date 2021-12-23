package com.example.kalkulatormvvm.utils

import android.os.SystemClock
import android.view.View
import java.util.*

object Commons {

    class ThrottledOnClickListener(private val onClick: (view: View) -> Unit, private val delayTimeMS: Long = 300L) : View.OnClickListener {
        private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

        override fun onClick(clickedView: View) {
            val previousClickTimestamp = lastClickMap[clickedView]
            val currentTimestamp = SystemClock.uptimeMillis()

            lastClickMap[clickedView] = currentTimestamp
            if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > delayTimeMS) {
                onClick.invoke(clickedView)
            }
        }
    }
}