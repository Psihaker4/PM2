package com.medusa.plusminus2.`object`

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Group
import ktx.assets.invoke
import ktx.assets.pool
import java.util.*

class Field : Group() {

    companion object {
        val HORIZONTAL_GAP = 40f
        val HORIZONTAL_START_GAP = 40f
        val VERTICAL_GAP = 50f
        val VERTICAL_START_GAP = 40f
        val ROWS = 7
        val COLS = 6
    }

    private val startRows = 3

    private val field = Array(ROWS) { IntArray(COLS) { -1 } }

    private val tilesPool = pool((startRows+1)* COLS,(ROWS+1)* COLS) { Tile() }
    private val tiles: MutableList<Tile> = ArrayList()

    private val randomize = Random()

    init {
        initField()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    fun addTile() = tilesPool().also { tiles.add(it); addActor(it) }

    fun removeTile(tile : Tile) {
        tilesPool(tile)
        tiles.remove(tile)
        removeActor(tile)
    }

    fun resetField(startCols: Int = COLS, startRows: Int = ROWS) {
        for (i in 0 until startRows) for (j in 0 until startCols) field[i][j] = -1
    }

    fun initField() {
        for (i in 0 until ROWS) {
            for (j in 0 until COLS) {
                addTile().apply {
                    setPosition(i, j)
                    number = randomize.nextInt(11)
                }
            }
        }
    }

    fun setPosition(y: Float) {
        setPosition(x, y)
    }
}

