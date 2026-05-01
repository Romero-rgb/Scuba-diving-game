package io.github.romero_rgb.helpers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.utils.Array as GdxArray
class AssetManager {

    //Game Map
    var map: TiledMap? = null

    //Sprite Sheet
    var sheet: Texture? = null
    var sheetScubaIdle: Texture? = null

    //Scubadiver Moving
    var scubadiver1: TextureRegion? = null
    var scubadiver2: TextureRegion? = null
    var scubadiver3: TextureRegion? = null
    var scubadiver4: TextureRegion? = null
    var scubadiver5: TextureRegion? = null
    var scubadiver6: TextureRegion? = null
    var scubadiver7: TextureRegion? = null

    //Scubadiver Idle

    lateinit var scubadiverIdleAnim: Animation<TextureRegion>
    lateinit var scubadiverSwim: Animation<TextureRegion>



    fun load() {
        val loader = TmxMapLoader()
        map = loader.load("map/Map_01.tmx")

        sheet = Texture("player-swiming.png")
        sheet!!.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        val swimAnimFrames = GdxArray<TextureRegion>()
        scubadiver1 = TextureRegion(sheet, 0, 0, 80, 80)
        scubadiver2 = TextureRegion(sheet, 80, 0, 80, 80)
        scubadiver3 = TextureRegion(sheet, 160, 0, 80, 80)
        scubadiver4 = TextureRegion(sheet, 240, 0, 80, 80)
        scubadiver5 = TextureRegion(sheet, 320, 0, 80, 80)
        scubadiver6 = TextureRegion(sheet, 400, 0, 80, 80)
        scubadiver7 = TextureRegion(sheet, 480, 0, 80, 80)

        swimAnimFrames.add(scubadiver1)
        swimAnimFrames.add(scubadiver2)
        swimAnimFrames.add(scubadiver3)
        swimAnimFrames.add(scubadiver4)
        swimAnimFrames.add(scubadiver5)
        swimAnimFrames.add(scubadiver6)
        swimAnimFrames.add(scubadiver7)

        scubadiverSwim = Animation(0.5f, swimAnimFrames, Animation.PlayMode.LOOP)

        sheetScubaIdle = Texture("player-idle.png")
        sheetScubaIdle!!.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        val scubadiverIdle = GdxArray<TextureRegion>()
        for (i in 0..5) {
            scubadiverIdle.add (TextureRegion(sheetScubaIdle, i * 80, 0, 80, 80))
        }

        scubadiverIdleAnim = Animation(0.2f, scubadiverIdle, Animation.PlayMode.LOOP )


    }

    fun isColliding(rect: com.badlogic.gdx.math.Rectangle, limitWorld: List<com.badlogic.gdx.maps.tiled.TiledMapTileLayer> ): Boolean {
        for (layer in limitWorld) {
            val tileWidth = layer.tileWidth
            val tileHeight = layer.tileHeight

            val startX = (rect.x / tileWidth).toInt()
            val endX = ((rect.x + rect.width) / tileWidth).toInt()
            val startY = (rect.y / tileHeight).toInt()
            val endY = ((rect.y + rect.height) / tileHeight).toInt()

            for (x in startX..endX) {
                for (y in startY..endY) {
                    val cell = layer.getCell(x, y)
                    if (cell != null) return true
                }
            }
        }
        return false
    }

    fun dispose() {

        sheet?.dispose()
        sheetScubaIdle?.dispose()
        map?.dispose()

    }
}
