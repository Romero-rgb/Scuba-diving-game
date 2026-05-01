package io.github.romero_rgb.objects

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.Pool
import io.github.romero_rgb.objects.Bubble
import io.github.romero_rgb.utils.Settings
import kotlin.random.Random

class BubbleSpawner(private val pool: Pool<Bubble>,private val itemLayer: Group) {

    /*val bubblePool = object : Pool<Bubble>() {
        override fun newObject(): Bubble? {
            return Bubble(0f, 0f, Settings.BUBBLES_WIDTH, Settings.BUBBLES_HEIGHT, false, Settings.BUBBLES_FREQUENCY,
                Settings.BUBBLES_AMPLITUDE, Settings.BUBBLES_VELOCITY, Settings.GAME_WIDTH.toFloat() )
        }
    }*/


    private val maxBubbles = Settings.BUBBLES_NUMBER_MAX
    private val minBubbles = Settings.BUBBLES_NUMBER_MIN
    private val spawnInterval = Settings.SPAWN_INTERVAL

    private var time = 0f
    private val activeBubbles = mutableListOf<Bubble>()

    fun spawn() {

        val bubble = pool.obtain()

        val randomX = MathUtils.random(0f, Settings.GAME_WIDTH.toFloat())
        val randomSizeWidth = MathUtils.random(Settings.BUBBLES_WIDTH)
        val randomSizeHeight = MathUtils.random(Settings.BUBBLES_HEIGHT)

        bubble.setup(randomX, randomSizeWidth, randomSizeHeight)

    }


    fun refresh(delta: Float) {
        time += delta

        val iterator = activeBubbles.iterator()
        while (iterator.hasNext()) {
            val bubble = iterator.next()
            if (bubble.dead){
                bubble.remove()
                pool.free(bubble)
                iterator.remove()
            }
        }

        if (activeBubbles.size < minBubbles) {
            spawn()
        }else if (time >= spawnInterval && activeBubbles.size < maxBubbles){
            spawn()
            time = 0f
        }
    }






}
