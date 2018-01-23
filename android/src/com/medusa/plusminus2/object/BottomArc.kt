package com.medusa.plusminus2.`object`

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Group
import com.medusa.plusminus2.engine.Image
import com.medusa.plusminus2.engine.Scene.Companion.HEIGHT
import com.medusa.plusminus2.engine.Scene.Companion.WIDTH

class BottomArc : Group(){

    private val barSprite = Image.BottomBar.toSprite()

    init{
        setSize(barSprite.width,barSprite.height)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        barSprite.draw(batch,parentAlpha)
        super.draw(batch, parentAlpha)
    }

    fun setPosition(y: Float) {
        val x = (WIDTH - HEIGHT) / 2
        barSprite.setPosition(x, y)
        setPosition(x, y)
    }

}