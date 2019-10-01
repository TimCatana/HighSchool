package com.applicnation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ScrSettings implements Screen, InputProcessor {

    Game game;
    Assets assets;
    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    private Vector3 vTouched;
    Rectangle sprbackbtn;

    public ScrSettings(Game _game, Assets _assets) {
        this.game = _game;
        this.assets = _assets;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(1080, 1920, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        vTouched = new Vector3();
        resize(480, 800);
        batch = new SpriteBatch();

        sprbackbtn = new Rectangle(120, 550, 240, 100);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(230 / 255f, 220 / 255f, 200 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        batch.draw(assets.manager.get("easybtn.png", Texture.class), 120, 550);
        batch.draw(assets.manager.get("backarrow.png", Texture.class), 500, 550, 100, 100);


        batch.end();

        if (Gdx.input.justTouched()) {
            camera.unproject(vTouched.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (sprbackbtn.contains(vTouched.x, vTouched.y)) {
                game.setScreen(new ScrMainMenu(game, assets));
            }
        }

    }

    @Override
    public void resize
            (int width, int height

            ) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause


            () {
    }

    @Override
    public void resume


            () {
    }

    @Override
    public void hide


            () {
    }

    @Override
    public void dispose


            () {
    }

    @Override
    public boolean keyDown
            (int keycode

            ) {
        return true;
    }

    @Override
    public boolean keyUp
            (int keycode

            ) {
        return true;
    }

    @Override
    public boolean keyTyped
            (char character

            ) {
        return true;
    }

    @Override
    public boolean touchDown
            (int screenX, int screenY, int pointer, int button

            ) {
        return true;
    }

    @Override
    public boolean touchUp
            (int screenX, int screenY, int pointer, int button

            ) {
        return true;
    }

    @Override
    public boolean touchDragged
            (int screenX, int screenY, int pointer

            ) {
        return true;
    }

    @Override
    public boolean mouseMoved
            (int screenX, int screenY

            ) {
        return true;
    }

    @Override
    public boolean scrolled
            (int amount

            ) {
        return true;
    }
}

