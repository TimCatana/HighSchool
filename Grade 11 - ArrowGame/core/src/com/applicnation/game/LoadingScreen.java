package com.applicnation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by timi2 on 2018-01-03.
 */

public class LoadingScreen implements Screen {

    Game game;
    Assets assets;

    public LoadingScreen(Game game) {
        this.game = game;
        assets = new Assets();
        assets.loadtextures();
        assets.loadsounds();
        assets.loadfonts();
        assets.manager.finishLoading();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(230 / 255f, 220 / 255f, 200 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (assets.manager.update()) {
            System.out.println("RUN");
            game.setScreen(new ScrMainMenu(game, assets));
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
