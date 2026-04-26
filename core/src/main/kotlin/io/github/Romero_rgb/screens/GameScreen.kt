package io.github.romero_rgb.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import io.github.romero_rgb.objects.Scubadiver
import io.github.romero_rgb.utils.Settings


class GameScreen(): Screen {

    private val scubadiver = Scubadiver(Settings.SCUBADIVER_STARTX, Settings.SCUBADIVER_STARTY, Settings.SCUBADIVER_WIDTH, Settings.SCUBADIVER_HEIGHT)

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(Settings.GAME_WIDTH.toFloat(), Settings.GAME_HEIGHT.toFloat(), camera)

    private val stage = Stage(viewport)


    override fun show() {
        stage.addActor(scubadiver)
    }

    override fun render(delta: Float) {
        stage.draw()
        stage.act(delta)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }
}
