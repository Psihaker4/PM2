package com.medusa.plusminus2.engine

import com.badlogic.gdx.assets.AssetManager

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter
import com.medusa.plusminus2.engine.AssetManager.manager
import ktx.assets.getAsset
import ktx.assets.load

object AssetManager {
    val manager = AssetManager().apply {
        val resolver = InternalFileHandleResolver()
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
        setLoader(BitmapFont::class.java, FreetypeFontLoader(resolver))
    }

    fun initialize() {
        loadImages()
        loadFonts()
    }

    fun loadFonts() {
        Font.GillSansBoldItalic.load(FontStyle.White)
        Font.GillSansBold.load(FontStyle.White)
    }

    fun loadImages() {
        Image.values().forEach { it.load() }
    }

}

enum class Image {
    Background,
    BottomBar,
    TileBackground,
    TileOutline,
    ChooserAdd,
    ChooserCenter,
    ChooserOutline,
    Circle,
    Pause,
    Plus,
    Minus,
    TileCircle0,
    TileCircle1,
    TileCircle2,
    TileCircle3,
    TileCircle4,
    TileCircle5,
    TileCircle6,
    TileCircle7,
    TileCircle8,
    TileCircle9;

    private val path = "images/$name.png"
    operator fun invoke() = manager.getAsset<Texture>(path).apply { setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear) }

    fun load() = manager.load<Texture>(path)
    fun toSprite() = Sprite(this())

}

enum class FontStyle(val color: Color = Color.WHITE,
                val size: Int = 40,
                val borderColor: Color = Color.WHITE,
                val borderWidth: Float = 0f,
                val borderAlpha: Float = 1f) {
    White;

    override fun toString(): String {
        return "$color$size$borderWidth$borderAlpha"
    }
}

enum class Font {
    GillSansStd,
    GillSansBold,
    GillSansBoldItalic;

    private val path = "fonts/$name.otf"
    operator fun invoke(style: FontStyle) = manager.getAsset<BitmapFont>("$name$style.ttf").apply { region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear) }
    fun load(style: FontStyle) {
        manager.load<BitmapFont>("$name$style.ttf", FreeTypeFontLoaderParameter().apply {
            fontFileName = path
            fontParameters.apply {
                size = style.size
                color = style.color
                borderColor = style.borderColor.cpy().apply { a = style.borderAlpha }
                borderWidth = style.borderWidth
            }
        })
    }

}
