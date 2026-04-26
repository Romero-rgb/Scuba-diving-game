package io.github.romero_rgb

import com.badlogic.gdx.Game
import io.github.romero_rgb.helpers.AssetManager
import io.github.romero_rgb.screens.GameScreen

class SubmarinistaGame : Game() {
    private lateinit var assetManager: AssetManager

    override fun create() {
        assetManager.load()
        setScreen(GameScreen())
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }
}
