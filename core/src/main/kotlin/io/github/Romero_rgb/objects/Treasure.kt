package io.github.romero_rgb.objects

import io.github.romero_rgb.objects.WorldElement

class Treasure(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    dead: Boolean,
) : WorldElement(x, y, width, height, dead) {

    override fun act(delta: Float) {
    }
}
