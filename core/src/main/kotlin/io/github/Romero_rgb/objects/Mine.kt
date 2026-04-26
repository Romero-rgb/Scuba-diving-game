package io.github.romero_rgb.objects

import com.badlogic.gdx.math.Vector2
import io.github.romero_rgb.objects.WorldElement
import io.github.romero_rgb.utils.Settings
import kotlin.math.sin

class Mine(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    dead: Boolean,
    private val frequency: Float,
    private val amplitude: Float
) : WorldElement(x, y, width, height, dead) {

    private var time = 0f

    override fun act(delta: Float) {
        time += delta

        val movement = sin(time * frequency) * amplitude

        setPosition(x, y + movement)

    }

}
