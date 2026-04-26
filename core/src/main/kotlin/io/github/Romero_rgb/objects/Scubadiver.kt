package io.github.romero_rgb.objects

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import io.github.romero_rgb.utils.Settings

enum class ScubaPosition {
    //Scubadiver positions and states
    IDLE, UP, DOWN, LEFT, RIGHT
}

class Scubadiver(x: Float, y: Float, private val width: Float, private val height: Float): Actor() {

    protected var direction: ScubaPosition



    init {
        direction = ScubaPosition.IDLE
    }

    override fun act(delta: Float ) {
        super.act(delta)
        val velocity = Settings.SCUBADIVER_VELOCITY * delta
        val sinkVelocity = Settings.SCUBADIVER_SINK_VELOCITY * delta

        if (direction != ScubaPosition.UP || direction != ScubaPosition.DOWN) {
            if (y - sinkVelocity >= 0) y -= sinkVelocity
        }


        when (direction) {

            ScubaPosition.UP -> if (y + height + velocity <= Settings.GAME_HEIGHT) y += velocity

            ScubaPosition.DOWN -> if (y - velocity >= 0) y -= velocity

            ScubaPosition.RIGHT -> if (x + width + velocity <= Settings.GAME_WIDTH) x += velocity

            ScubaPosition.LEFT -> if (x - velocity >= 0) x -= velocity

            ScubaPosition.IDLE -> {}

        }
    }

    //Scubadiver goes up, down, left, right or wait
    fun goUp() {
        direction = ScubaPosition.UP
    }

    fun goDown() {
        direction = ScubaPosition.DOWN
    }

    fun goLeft() {
        direction = ScubaPosition.LEFT
    }

    fun goRight() {
        direction = ScubaPosition.RIGHT
    }

    fun stop() {
        direction = ScubaPosition.IDLE
    }


}
