package io.github.romero_rgb.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.reflect.Method
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.StretchViewport
import io.github.romero_rgb.helpers.AssetManager
import io.github.romero_rgb.objects.Scubadiver
import io.github.romero_rgb.utils.Settings
import kotlin.collections.forEach
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener

class GameScreen : Screen {

    private val scubadiver = Scubadiver(
        Settings.SCUBADIVER_STARTX,
        Settings.SCUBADIVER_STARTY,
        Settings.SCUBADIVER_WIDTH.toFloat(),
        Settings.SCUBADIVER_HEIGHT.toFloat()
    )

    private val obstacleLayer = Group()
    private val itemLayer = Group()

    private val shapeRenderer = ShapeRenderer()
    private val camera = OrthographicCamera()
    private val viewport =
        FitViewport(Settings.GAME_WIDTH.toFloat(), Settings.GAME_HEIGHT.toFloat(), camera)
    private val stage = Stage(viewport)
    private val assets = AssetManager()

    private var mapRenderer: OrthogonalTiledMapRenderer? = null

    private val limitWorld = mutableListOf<TiledMapTileLayer>()

    override fun show() {
        Gdx.input.inputProcessor = stage

        assets.load()

        scubadiver.assets = assets

        assets.map?.let { map ->

            mapRenderer = OrthogonalTiledMapRenderer (map)

            map.layers.forEach { layer ->
                if (layer.properties.containsKey("solid") && layer is TiledMapTileLayer) {
                    limitWorld.add(layer)
                }
            }

        }


        stage.addActor(scubadiver)
        stage.addActor(obstacleLayer)
        stage.addActor(itemLayer)

        stage.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                scubadiver.targetX = x - scubadiver.width / 2
                scubadiver.targetY = y - scubadiver.height / 2
                return true
            }

            override fun touchDragged(event: InputEvent, x: Float, y: Float, pointer: Int) {
                scubadiver.targetX = x - scubadiver.width / 2
                scubadiver.targetY = y - scubadiver.height / 2
            }
        })
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val oldX = scubadiver.x
        val oldY = scubadiver.y

        stage.act(delta)

            if (assets.isColliding(scubadiver.collisionRect, limitWorld)) {
                scubadiver.setPosition(oldX, oldY)
                scubadiver.collisionRect.set(oldX, oldY + 3, scubadiver.width, scubadiver.height)

                scubadiver.targetX = oldX
                scubadiver.targetY = oldY
            }

        var camX = scubadiver.x + scubadiver.width /2
        var camY = scubadiver.y + scubadiver.height / 2

        val mapWidth = 60 * 32f
        val mapHeight = 60 * 32f

        val halfViewWidth = Settings.GAME_WIDTH / 2f
        val halfViewHeight = Settings.GAME_HEIGHT / 2f

        camX = camX.coerceIn(halfViewWidth, mapWidth - halfViewWidth)
        camY = camY.coerceIn(halfViewHeight, mapHeight - halfViewHeight)

        stage.camera.position.set(camX, camY, 0f)
        stage.camera.update()

        mapRenderer?.let { renderer ->
            renderer.setView(camera)
            renderer.render()
        }




        stage.draw()

        /*shapeRenderer.projectionMatrix = stage.camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.color = Color.RED

        for (actor in stage.actors) {
            shapeRenderer.rect(actor.x, actor.y, actor.width, actor.height)
        }
        shapeRenderer.end()*/
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        stage.dispose()
        shapeRenderer.dispose()
        mapRenderer?.dispose()
    }
}
