package com.catani.gamegravity.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.catani.gamegravity.Assets;
import com.catani.gamegravity.Constants;
import com.catani.gamegravity.GamGravity;

/**
 * Created by am44_000 on 2018-09-28.
 */
public class ScrGameover implements Screen {
    GamGravity game;

    public ScrGameover(GamGravity game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(230 / 255f, 220 / 255f, 200 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.sbg.staticImage(game.batch);
        game.drawText(Assets.Fonts.GAMEOVER, "Game over", Constants.WORLDWIDTH/2- 500, Constants.CEILING/2 + 250);

//        animator.AnmFadePlayBtn(batch, 630, 730);
//        batch.draw(assets.manager.get("Mario.png", Texture.class), 500, 128, 75, 150);

        game.batch.end();
        if(Gdx.input.isTouched()){
            game.changeScreen(Screens.SCRMAINMENU);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
