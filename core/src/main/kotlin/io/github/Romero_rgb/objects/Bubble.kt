package io.github.romero_rgb.objects

import com.badlogic.gdx.utils.Pool
import kotlin.math.sin

class Bubble(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    dead: Boolean,
    private val frequency: Float,
    private val amplitude: Float,
    private val velocity: Float,
    private val deadPoint: Float

) : WorldElement(x, y, width, height, dead), Pool.Poolable {


    fun setup(newX: Float, newWidth: Float, newHeight: Float) {
        this.x = newX
        this.width = newWidth
        this.height = newHeight
        this.dead = false
        this.time = 0f
    }
    private var time = 0f
    var dead = false

    override fun act(delta: Float) {
        oscillation(delta)
        y += velocity * delta
        if (y >= deadPoint) {
            dead = true
            if (dead)
                remove()
        }
    }
    fun oscillation(delta: Float){

        time += delta
        val movement = sin(time * frequency) * amplitude

        setPosition(x + movement, y )
    }

    override fun reset() {
        time = 0f
        dead = false

    }


}
