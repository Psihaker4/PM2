package com.medusa.plusminus2.engine

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.medusa.plusminus2.engine.Scene.Companion.HEIGHT
import com.medusa.plusminus2.engine.Scene.Companion.WIDTH
import com.medusa.plusminus2.scene.GameScene
import com.medusa.plusminus2.scene.LaunchScene

object SceneManager : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        setScene(SceneName.Launch)
    }

    override fun dispose() {
        batch.dispose()
        screen?.dispose()
        AssetManager.manager.dispose()
    }

    fun setScene(sceneName: SceneName) {
        screen?.dispose()
        val camera = OrthographicCamera(WIDTH, HEIGHT)
        val viewport = FitViewport(WIDTH, HEIGHT, camera)
        setScreen(when (sceneName) {
            SceneName.Launch -> LaunchScene(viewport, batch)
            SceneName.Game -> GameScene(viewport, batch)
            SceneName.Menu -> TODO()
            SceneName.Pause -> TODO()
        })
    }
}
