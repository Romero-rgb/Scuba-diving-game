package io.github.romero_rgb.utils

object Settings {
    //Game Size

    const val GAME_WIDTH: Int = 100

    const val GAME_HEIGHT: Int = 100

    //Scubadiver properties
    const val SCUBADIVER_VELOCITY: Float = 50f
    const val SCUBADIVER_SINK_VELOCITY: Float = 30f
    const val SCUBADIVER_WIDTH: Int = 36
    const val SCUBADIVER_HEIGHT: Int = 15
    const val SCUBADIVER_STARTX: Float = 40f
    const val SCUBADIVER_STARTY: Float = (GAME_HEIGHT / 2 - SCUBADIVER_HEIGHT / 2).toFloat()

    //Mine properties
    const val MINE_AMPLITUDE = 5f
    const val MINE_FREQUENCY = 1f

    const val MINE_WIDTH = 10f
    const val MINE_HEIGHT = 10f

    //Fish properties
    const val FISH_WIDTH = 10f
    const val FISH_HEIGHT = 10f

    const val FISH_VELOCITY = 20f
    //Bubbles properties
    const val BUBBLES_WIDTH = 10f
    const val BUBBLES_HEIGHT = 10f

    const val BUBBLES_VELOCITY = 10f


}
