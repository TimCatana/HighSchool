package com.catani.gamegravity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {

    public AssetManager manager;

    public Assets() {
        manager = new AssetManager();

    }

    public void loadtextures() {
//        manager.load("shopbutton.png", Texture.class); not using it for now
        manager.load("background1.png", Texture.class); //used for main menu screen only
        manager.load("logo.png", Texture.class);
        manager.load("Hero.png", Texture.class);
        manager.load("floorhole.png", Texture.class);
        manager.load("ceilinghole.png", Texture.class);
        manager.load("logo.png", Texture.class);

        manager.load("AniBtnPlaySprite.txt", TextureAtlas.class);
        manager.load("AniBtnPlaySprite.png", Texture.class);

    }

    public void loadsounds(){
        manager.load("electroman.mp3", Music.class);
    }

    public void loadfonts() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));


        FreetypeFontLoader.FreeTypeFontLoaderParameter ScoreParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(ScoreParams, "pixeldown.ttf", 150, Color.WHITE, "score.ttf");
        FreetypeFontLoader.FreeTypeFontLoaderParameter gameoverParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(gameoverParams,"pixeldown.ttf", 250, Color.BLACK, "gameover.ttf");
    }

    private void createtext(FreetypeFontLoader.FreeTypeFontLoaderParameter parameters, String filename, int nSize, Color color, String textname) {
        parameters.fontFileName = filename;
        parameters.fontParameters.size = nSize;
        parameters.fontParameters.color = color;
        manager.load(textname, BitmapFont.class, parameters);
    }
    public enum Fonts{
        SCORE,
        GAMEOVER
    }
}
