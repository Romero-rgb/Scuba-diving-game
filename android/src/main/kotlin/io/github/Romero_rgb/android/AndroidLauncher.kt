package io.github.Romero_rgb.android

import android.os.Bundle
import android.view.WindowManager

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import io.github.Romero_rgb.SubmarinistaGame

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(SubmarinistaGame(), AndroidApplicationConfiguration().apply {
            // Application configuration.
            useImmersiveMode = true;
            useAccelerometer = false;
            useCompass = false;


        })

        //To keep the screen on
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
