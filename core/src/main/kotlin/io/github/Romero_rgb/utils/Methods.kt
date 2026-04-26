package io.github.romero_rgb.objects

import kotlin.random.Random

fun randomFloat (min: Float, max: Float): Float {
    val r : Random = Random.Default

    return r.nextFloat() * (max - min) + min
}
