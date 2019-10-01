package com.catani.gamegravity.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.catani.gamegravity.Animator;
import com.catani.gamegravity.Constants;
import com.catani.gamegravity.GamGravity;
import com.sun.org.apache.bcel.internal.classfile.Constant;


public class ScrMainMenu implements Screen, InputProcessor {
    GamGravity game;
    Animator animator;

    boolean touchDown;

    public ScrMainMenu(GamGravity _game) {
        this.game = _game;
        animator = new Animator(game.assets);
    }

    @Override
    public void show() {
        if (Gdx.input.isTouched()) {
            touchDown = true;
        }
        Gdx.input.setInputProcessor(this);
        game.chrMain.setX(-100);
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(230 / 255f, 220 / 255f, 200 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.sbg.staticImage(game.batch);

        if (game.chrMain.getX() < 500) {
            game.chrMain.MenuAnimation(game.batch);
        } else {
            game.drawImage("Hero.png", 500, Constants.FLOOR);
            game.drawImage("logo.png", 1920 / 2, 540, 1000, 600);
            animator.drawAni(game.batch, 700, 600);

            if (Gdx.input.justTouched() && !touchDown) {
                game.changeScreen(Screens.SCRGAME);
            }
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
        game.camera.position.set(game.camera.viewportWidth / 2, game.camera.viewportHeight / 2, 0);
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

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (touchDown) {
            touchDown = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
}

