package io.github.romero_rgb.objects

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class WorldElement(x: Float, y: Float, width: Float,  height: Float, dead: Boolean): Actor() {

    protected val position = Vector2(x, y)

    init {
        setBounds(position.x, position.y, width, height)
    }

    abstract override fun act(delta: Float)
}
