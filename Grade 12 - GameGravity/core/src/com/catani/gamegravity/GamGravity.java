package com.catani.gamegravity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.catani.gamegravity.screens.ScrGame;
import com.catani.gamegravity.screens.ScrGameover;
import com.catani.gamegravity.screens.ScrMainMenu;
import com.catani.gamegravity.screens.Screens;

//terrible hack
public class GamGravity extends Game {
    ScrMainMenu scrMainMenu;
    ScrGame scrGame;
    ScrGameover scrGameover;

    public SpriteBatch batch;

    public OrthographicCamera camera;
    public StretchViewport viewport;

    public ScrollingBackground sbg;

    public Assets assets;

    public SprChar chrMain;


    @Override
    public void create() {
        assets = new Assets();
        assets.loadtextures();
        assets.loadsounds();
        assets.loadfonts();
        assets.manager.finishLoading();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(1920, 1080, camera);
        viewport.apply();
        camera.position.set(1920 / 2, 1080 / 2, 0);
        resize(1920, 1080);

        batch = new SpriteBatch();

        sbg = new ScrollingBackground();

        chrMain = new SprChar();

        scrMainMenu = new ScrMainMenu(this);
        scrGame = new ScrGame(this);
        scrGameover = new ScrGameover(this);
        changeScreen(Screens.SCRMAINMENU);
    }

    @Override
    public void render() {
        super.render();
    }

    public void changeScreen(Screens screen) {
        switch (screen) {
            case SCRGAME:
                setScreen(scrGame);
                break;
            case SCRMAINMENU:
                setScreen(scrMainMenu);
                break;
            case SCRGAMEOVER:
                setScreen(scrGameover);
        }
    }

    public void drawImage(String img, float fX, float fY) {
        batch.draw(assets.manager.get(img, Texture.class), fX, fY);
    }

    public void drawImage(String img, float fX, float fY, float fW, float fH) {
        batch.draw(assets.manager.get(img, Texture.class), fX, fY, fW, fH);
    }

    public void drawText(Assets.Fonts font, String text, float fX, float fY) {
        switch (font) {
            case SCORE:
                assets.manager.get("score.ttf", BitmapFont.class).draw(batch, text, fX, fY);
                break;
            case GAMEOVER:
                assets.manager.get("gameover.ttf", BitmapFont.class).draw(batch, text, fX, fY);
        }
    }
}
