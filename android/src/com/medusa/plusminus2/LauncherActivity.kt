package com.medusa.plusminus2

import android.os.Bundle
import com.badlogic.gdx.ApplicationListener

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.medusa.plusminus2.engine.SceneManager

class LauncherActivity : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SceneManager.init {
            useAccelerometer = false
            useCompass = false
        }
    }

    private inline fun ApplicationListener.init(a: AndroidApplicationConfiguration.() -> Unit) {
        initialize(this, AndroidApplicationConfiguration().apply(a))
    }
}