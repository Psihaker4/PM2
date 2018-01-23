package com.medusa.plusminus2.scene

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.medusa.plusminus2.engine.AssetManager
import com.medusa.plusminus2.engine.SceneManager
import com.medusa.plusminus2.engine.Scene
import com.medusa.plusminus2.engine.SceneName

class LaunchScene(viewport: Viewport, batch: SpriteBatch) : Scene(viewport, batch) {

    init {
        AssetManager.initialize()
    }

    override fun update(delta: Float) {
        background = Color.BLACK.cpy().lerp(Color.valueOf("9f9f9f"),AssetManager.manager.progress)
        if(AssetManager.manager.update()){
            SceneManager.setScene(SceneName.Game)
        }
    }

}
