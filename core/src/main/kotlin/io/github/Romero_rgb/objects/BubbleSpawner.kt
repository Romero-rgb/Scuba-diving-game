package io.github.Romero_rgb.objects

import com.badlogic.gdx.scenes.scene2d.Group
import io.github.romero_rgb.objects.Bubble
import io.github.romero_rgb.objects.Fish
import io.github.romero_rgb.objects.Mine
import java.util.Random

class BubbleSpawner: Group() {

    var numBubbles: Int = 0
    val bubbles: MutableList<Bubble> = mutableListOf()

    val r: Random = Random()

}
