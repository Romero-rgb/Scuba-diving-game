package io.github.romero_rgb.objects

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.random.Random

fun randomFloat (min: Float, max: Float): Float {
    val r : Random = Random.Default

    return r.nextFloat() * (max - min) + min
}


