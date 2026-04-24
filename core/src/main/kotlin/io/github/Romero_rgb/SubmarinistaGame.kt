package io.github.Romero_rgb

import com.badlogic.gdx.Game

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class SubmarinistaGame : Game() {

    override fun create() {
        load()
        setScreen(initScreen(this))
    }

    override fun dispose() {
        super.dispose()
        AssetManager.dispose()
    }
}
