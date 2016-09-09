package ru.maxlord.kotlinandroidapp.util

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

class AsyncBus(enforcer: ThreadEnforcer, name: String) : Bus(enforcer, name) {

    private val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        mainThread.post(object : Runnable {
            override fun run() {
                super@AsyncBus.post(event)
            }
        })
    }

    fun postDelayed(event: Any, delayMs: Long) {
        mainThread.postDelayed(object : Runnable {
            override fun run() {
                super@AsyncBus.post(event)
            }
        }, delayMs)
    }
}
