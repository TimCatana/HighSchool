package com.applicnation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ScrShop implements Screen, InputProcessor, GestureDetector.GestureListener {

    Game game;
    OrthographicCamera camera;
    Assets assets;
    Viewport viewport;
    private Vector3 vTouched;
    DragListener dragger;
    static boolean isShrek = false, isShapes = false, isFood = false, isTheWay = false, isNormal = true;
    SpriteBatch batch;
    Rectangle Shrek, classic, shapes, food,TheWay, backarrow;
    Sound sndTheWay, sndShrek, sndFood;

    public ScrShop(Game _game, Assets assets) {
        game = _game;
        this.assets = assets;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(1080, 1920, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        vTouched = new Vector3();
        dragger = new DragListener();
        batch = new SpriteBatch();

        sndTheWay = assets.manager.get("doyouknowthewat.mp3", Sound.class);
        sndShrek = assets.manager.get("shreksound.mp3", Sound.class);
        sndFood = assets.manager.get("foodsound.mp3", Sound.class);

        Shrek = new Rectangle(580, 1380, 400, 400);
        classic = new Rectangle(100, 1380, 400, 400);
        shapes = new Rectangle(100, 800, 400, 400);
        food = new Rectangle(580, 800, 400, 400);
        TheWay = new Rectangle(100, 230, 400, 400);

        backarrow = new Rectangle(0, 0, 200, 200);

    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(this);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(assets.manager.get("backarrow.png", Texture.class), 0, 0, 200, 200);
        batch.draw(assets.manager.get("shrektexture.png", Texture.class), 580, 1380, 400, 400);
        batch.draw(assets.manager.get("Normaltheme.png", Texture.class), 100, 1380, 400, 400);
        batch.draw(assets.manager.get("shapetheme.png", Texture.class), 100, 800, 400, 400);
        batch.draw(assets.manager.get("diabetestheme.png", Texture.class), 580, 800, 400, 400);
        batch.draw(assets.manager.get("uknowthewaytheme.png", Texture.class), 100, 230, 400, 400);

        if (Gdx.input.justTouched()) {
            camera.unproject(vTouched.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (backarrow.contains(vTouched.x, vTouched.y)) {
                game.setScreen(new ScrMainMenu(game, assets));
            }
            if (Shrek.contains(vTouched.x, vTouched.y)) {
                isShrek = true;
                isNormal = false;
                isShapes = false;
                isFood = false;
                isTheWay = false;
                sndShrek.play();

            }
            if (classic.contains(vTouched.x, vTouched.y)) {
                isNormal = true;
                isShrek = false;
                isShapes = false;
                isFood = false;
                isTheWay = false;

            }
            if (shapes.contains(vTouched.x, vTouched.y)) {
                isShapes = true;
                isNormal = false;
                isShrek = false;
                isFood = false;
                isTheWay = false;

            }
            if (food.contains(vTouched.x, vTouched.y)) {
                isFood = true;
                isShrek = false;
                isNormal = false;
                isShapes = false;
                isTheWay = false;
                sndFood.play();
            }
            if (TheWay.contains(vTouched.x, vTouched.y)) {
                isTheWay = true;
                isFood = false;
                isShrek = false;
                isNormal = false;
                isShapes = false;
                sndTheWay.play();

            }
        }
        batch.end();
    }
//try using an array of booleans so you can return them...
//    boolean change(Rectangle rect, boolean isTrue) {
//        if (rect.contains(vTouched.x, vTouched.y)) {
//            isTrue = true;
//        }
//        return isTrue;
//    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2
            pointer1, Vector2 pointer2) {
        return true;
    }

    @Override
    public void pinchStop() {
        return;
    }
}
