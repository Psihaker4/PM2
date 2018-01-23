package com.medusa.plusminus2.scene

import android.util.Log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.medusa.plusminus2.`object`.Field
import com.medusa.plusminus2.`object`.BottomArc
import com.medusa.plusminus2.engine.Font
import com.medusa.plusminus2.engine.FontStyle
import com.medusa.plusminus2.engine.Scene

class GameScene(viewport: Viewport, batch: SpriteBatch) : Scene(viewport, batch){

    private val field = Field()
    private val bar = BottomArc()

    private val fpsFont = Font.GillSansBoldItalic(FontStyle.White)

    init {
        background = Color.valueOf("9f9f9f")
        Gdx.input.inputProcessor = this
        addActor(field)
        addActor(bar)
        field.setPosition(HEIGHT - field.height - 300)
        bar.setPosition(field.y - bar.height - 20)
    }

    override fun draw() {
        super.draw()
        batch.begin()
        fpsFont.draw(batch,fps.toString(),0f,100f)
        batch.end()
    }

    override fun touch(x: Int, y: Int, pointer: Int, state: Int): Boolean {
        Log.d("game", "TOUCH: $x $y $pointer $state")
        return false
    }

}