package io.github.romero_rgb.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import io.github.romero_rgb.helpers.AssetManager
import io.github.romero_rgb.utils.Settings
import com.badlogic.gdx.math.Rectangle
enum class ScubaPosition {
    IDLE, UP, DOWN, LEFT, RIGHT
}

class Scubadiver(startX: Float, startY: Float, width: Float, height: Float): Actor() {

    var targetX: Float = startX
    var targetY: Float = startY

    private var direction: ScubaPosition = ScubaPosition.IDLE

    lateinit var assets: AssetManager

    private var animationTime = 0f
    private var isLeft = false

    private var rotation = 0f

    var collisionRect: Rectangle = Rectangle()
        private set



    init {
        setPosition(startX, startY)
        setSize(width, height)
        collisionRect.set(x, y, width, height)
        touchable = Touchable.enabled
    }

    override fun act(delta: Float ) {
        super.act(delta)
        val velocity = Settings.SCUBADIVER_VELOCITY * delta
        val sinkVelocity = Settings.SCUBADIVER_SINK_VELOCITY * delta

        val distanceX = targetX - x
        val distanceY = targetY - y
        val distance = Math.sqrt((distanceX * distanceX + distanceY * distanceY).toDouble()).toFloat()

        if (distance > 5f) {
            val moveX = (distanceX / distance) * velocity
            val moveY = (distanceY / distance) * velocity
            this.setPosition(x + moveX, y + moveY)
            collisionRect.set(x, y, width, height)

            if (distanceX > 0) isLeft = false else if (distanceX < 0) isLeft = true

            direction = if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (distanceX > 0) ScubaPosition.RIGHT else ScubaPosition.LEFT
            } else {
                if (distanceY > 0) ScubaPosition.UP else ScubaPosition.DOWN
            }
        }else {
            direction = ScubaPosition.IDLE
            rotation = 0f

             if (y - sinkVelocity >= 0) y -= sinkVelocity
            targetY -= sinkVelocity
            targetX = x

        }

        rotation = when (direction) {
            ScubaPosition.UP -> -90f
            ScubaPosition.DOWN -> 90f
            else -> 0f
        }


        if (x < 0) x = 0f
        if (x > 1920 - width) x = 1920f - width
        if (y < 0) y = 0f
        if (y > 1920 - height) y = 1920f - height

        collisionRect.set(x, y + 3, width, height)
        setBounds(x, y, width, height)

        animationTime += delta
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {

        if (!this::assets.isInitialized) return

        val animation = if (direction == ScubaPosition.IDLE) {
            assets.scubadiverIdleAnim
        } else {
            assets.scubadiverSwim
        }

        val currentFrame = when (direction) {
            ScubaPosition.IDLE -> assets.scubadiverIdleAnim.getKeyFrame(animationTime)
            else -> assets.scubadiverSwim.getKeyFrame(animationTime)
        }

        if (currentFrame != null) {
            val flip = isLeft
            batch?.draw(currentFrame.texture,
                x, y,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                rotation,
                currentFrame.regionX, currentFrame.regionY,
                currentFrame.regionWidth, currentFrame.regionHeight,
                flip, false
            )
        }
    }

    /*fun goUp() { direction = ScubaPosition.UP }
    fun goDown() { direction = ScubaPosition.DOWN }
    fun goLeft() { direction = ScubaPosition.LEFT }
    fun goRight() { direction = ScubaPosition.RIGHT }
    fun stop() { direction = ScubaPosition.IDLE }*/
}
