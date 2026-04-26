package io.github.romero_rgb.objects

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

) : WorldElement(x, y, width, height, dead) {

    private var time = 0f
    private var dead = false

    override fun act(delta: Float) {
        oscillation(delta)
        y += velocity * delta
        if (x >= deadPoint) {
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


}
