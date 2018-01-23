package com.medusa.plusminus2.`object`

import android.util.Log
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Pool
import com.medusa.plusminus2.engine.Font
import com.medusa.plusminus2.engine.FontStyle
import com.medusa.plusminus2.engine.Image
import java.util.*

class Tile : Actor(), Pool.Poolable {

    companion object {
        val COLORS = arrayOf(
                Color.valueOf("3be7a8"),
                Color.valueOf("b0e73b"),
                Color.valueOf("3b3be7"),
                Color.valueOf("b43be7"),
                Color.valueOf("e7903b"),
                Color.valueOf("3bb8e7"),
                Color.valueOf("3fe73b"),
                Color.valueOf("e73ba0"),
                Color.valueOf("e7cd3b"),
                Color.valueOf("703be7"),
                Color.valueOf("e73b3b")
        )

        val CIRCLES_POSITIONS = arrayOf(
                Vector2(0f,0f),
                Vector2(38f,3f),
                Vector2(2f,40f),
                Vector2(79f,18f),
                Vector2(9f,89f),
                Vector2(77f,89f),
                Vector2(81f,28f),
                Vector2(10f,100f),
                Vector2(0f,62f),
                Vector2(61f,0f)
        )

        val CIRCLES_COUNT = 10

    }

    var i = 0
    var j = 0

    var number = 0
        set(value) {
            backgroundSprite.color = COLORS[value]
            outlineSprite.color = COLORS[value].cpy().apply { r *= 0.4f; g *= 0.4f; b *= 0.4f }
            numberText.setText(numberFont, if (value == 10) "X" else value.toString())
            (0 until value)
                    .filter { circleSprites[it] == null }
                    .forEach { circleSprites[it] = Image.valueOf("TileCircle$it").toSprite() }
            (value until CIRCLES_COUNT)
                    .forEach { circleSprites[it] = null }

            setCirclesPosition()
            Log.d("game", Arrays.toString(circleSprites))
        }

    private val numberText = GlyphLayout()
    private val numberFont = Font.GillSansBold(FontStyle.White)

    private val backgroundSprite = Image.TileBackground.toSprite()
    private val outlineSprite = Image.TileOutline.toSprite()
    private val circleSprites: Array<Sprite?> = Array(10) { null }

    init {
        setSize(backgroundSprite.width, backgroundSprite.height)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        outlineSprite.draw(batch, parentAlpha)
        backgroundSprite.draw(batch, parentAlpha)
        circleSprites.forEach { it?.draw(batch, parentAlpha) }
        numberFont.draw(batch, numberText, x + (width - numberText.width) / 2, y + (height + numberText.height) / 2)
    }

    override fun reset() {
        setPosition(0f, 0f)
    }

    fun setPosition(i: Int, j: Int) {
        this.i = i
        this.j = j
        val x = Field.HORIZONTAL_START_GAP + backgroundSprite.width * j + Field.HORIZONTAL_GAP * j
        val y = Field.VERTICAL_START_GAP + backgroundSprite.height * i + Field.VERTICAL_GAP * i
        setPosition(x, y)
        backgroundSprite.setPosition(x, y)
        outlineSprite.setPosition(x - 10, y - 10)
        setCirclesPosition()
    }

    private fun setCirclesPosition() {
        circleSprites.forEachIndexed { index, sprite ->
            val vec = CIRCLES_POSITIONS[index]
            sprite?.setPosition(x+vec.x, y+height-vec.y-sprite.height)
        }
    }

}