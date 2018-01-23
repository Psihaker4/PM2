package com.medusa.plusminus2.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

enum class SceneName { Launch, Game, Menu, Pause }

abstract class Scene(viewport: Viewport, batch: SpriteBatch) : Stage(viewport, batch), Screen {

    companion object {
        val WIDTH = 1080f
        val HEIGHT = 1920f
    }
    protected var fps = 0f
    protected var background: Color = Color.WHITE

    override fun hide() = Unit
    override fun show() = Unit
    override fun pause() = Unit
    override fun resume() = Unit
    override fun resize(width: Int, height: Int) = Unit
    final override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = touch(screenX, screenY, pointer, 0)
    final override fun touchDragged(screenX: Int, screenY: Int, pointer: Int) = touch(screenX, screenY, pointer, 1)
    final override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = touch(screenX, screenY, pointer, 2)

    protected open fun update(delta: Float) = Unit
    protected open fun touch(x: Int, y: Int, pointer: Int, state: Int) = false

    override fun render(delta: Float) {
        fps = 1 / delta
        update(delta)
        clearBackground()
        act(delta)
        draw()
    }

    private fun clearBackground() = Gdx.gl.run {
        background.run { glClearColor(r, g, b, a) }
        glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

}