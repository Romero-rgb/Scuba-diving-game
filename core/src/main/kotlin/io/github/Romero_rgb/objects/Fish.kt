package io.github.romero_rgb.objects

import io.github.romero_rgb.objects.WorldElement
import kotlin.math.sin

class Fish(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    dead: Boolean,
    private val velocity: Float,
    private val pointA: Float,
    private val pointB: Float,
    private val frequency: Float,
    private val amplitude: Float

) : WorldElement(x, y, width, height, dead) {

    private var time = 0f
    private var rightMovement = true

    override fun act(delta: Float) {
        oscillation(delta)
        if (rightMovement) {
            x += velocity * delta
            if (x >= pointA)
                rightMovement = false
        } else {
            x -= velocity * delta
            if (x <= pointB)
                rightMovement = true
        }

    }

    fun oscillation(delta: Float){

        time += delta
        val movement = sin(time * frequency) * amplitude

        setPosition(x, y + movement)
    }

}
