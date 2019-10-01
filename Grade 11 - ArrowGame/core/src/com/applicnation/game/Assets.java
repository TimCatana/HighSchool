package com.applicnation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * Created by timi2 on 2018-01-03.
 */

public class Assets {

    AssetManager manager;

    public Assets() {
        manager = new AssetManager();
    }

    public void loadtextures() {
        //TEXTURES
        //main menu buttons
        manager.load("easybtn.png", Texture.class);
        manager.load("medbtn.png", Texture.class);
        manager.load("hardbtn.png", Texture.class);
        manager.load("settingsbtn.png", Texture.class);
        //game arrows
        manager.load("rightarrow.png", Texture.class);
        manager.load("leftarrow.png", Texture.class);
        manager.load("uparrow.png", Texture.class);
        manager.load("downarrow.png", Texture.class);
        //background
        manager.load("ArrowGameBackground.png", Texture.class);
        //greenconveyors
        manager.load("easymodegreenconveyer.png", Texture.class);
        manager.load("mediummodegreenconveyer.png", Texture.class);
        manager.load("hardmodegreenconveyer.png", Texture.class);
        //redconveyors
        manager.load("easymoderedconveyer.png", Texture.class);
        manager.load("mediummoderedconveyer.png", Texture.class);
        manager.load("hardmoderedconveyer.png", Texture.class);
        //shop textures
        manager.load("backarrow.png", Texture.class);
        manager.load("shopsquare.png", Texture.class);

        manager.load("donkey.png", Texture.class);
        manager.load("gingerbreadman.png", Texture.class);
        manager.load("humptydumpty.png", Texture.class);
        manager.load("Shrek.png", Texture.class);

        manager.load("bluesquare.png", Texture.class);
        manager.load("greencircle.png", Texture.class);
        manager.load("orangeoctogon.png", Texture.class);
        manager.load("redtriangle.png", Texture.class);

        manager.load("burger.png", Texture.class);
        manager.load("cake.png", Texture.class);
        manager.load("hotdog.png", Texture.class);
        manager.load("pizza.png", Texture.class);

        manager.load("blueguy.png", Texture.class);
        manager.load("greenguy.png", Texture.class);
        manager.load("orangeguy.png", Texture.class);
        manager.load("pinkguy.png", Texture.class);


        manager.load("shrektexture.png", Texture.class);
        manager.load("Normaltheme.png", Texture.class);
        manager.load("shapetheme.png", Texture.class);
        manager.load("diabetestheme.png", Texture.class);
        manager.load("uknowthewaytheme.png", Texture.class);


    }

    public void loadsounds(){
        manager.load("doyouknowthewat.mp3", Sound.class);
        manager.load("shreksound.mp3", Sound.class);
        manager.load("foodsound.mp3", Sound.class);
        manager.load("minecraftsong.mp3", Music.class);
        manager.load("thewaymusic.mp3", Music.class);


    }

    public void loadfonts() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        //scoreboard font
        FreetypeFontLoader.FreeTypeFontLoaderParameter Scoreparams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(Scoreparams, "scoreboard.ttf", 90, Color.WHITE, "score.ttf");
        //eaasytext
        FreetypeFontLoader.FreeTypeFontLoaderParameter easybtnparams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(easybtnparams, "Aaargh.ttf", 160, Color.GREEN, "easy.ttf");
        //mediumtext
        FreetypeFontLoader.FreeTypeFontLoaderParameter mediumbtnparams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(mediumbtnparams, "Aaargh.ttf", 130, Color.ORANGE, "medium.ttf");
        //hardtext
        FreetypeFontLoader.FreeTypeFontLoaderParameter hardbtnparams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(hardbtnparams, "Aaargh.ttf", 160, Color.RED, "hard.ttf");
        //settingstext
        FreetypeFontLoader.FreeTypeFontLoaderParameter settingsbtnparams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(settingsbtnparams, "Aaargh.ttf", 120, Color.YELLOW, "settings.ttf");

        FreetypeFontLoader.FreeTypeFontLoaderParameter paramaters = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        createtext(paramaters, "Aaargh.ttf", 160, Color.GREEN, "hi.ttf");

    }

    private void createtext(FreetypeFontLoader.FreeTypeFontLoaderParameter parameters, String filename, int nSize, Color color, String textname) {
        parameters.fontFileName = filename;
        parameters.fontParameters.size = nSize;
        parameters.fontParameters.color = color;
        manager.load(textname, BitmapFont.class, parameters);
    }
}

