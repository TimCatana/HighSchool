package com.applicnation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ScrMainMenu implements Screen, InputProcessor {

    Game game;
    Assets assets;
    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    private Vector3 vTouched;
    public static int nMode;
    Rectangle sprEasyBounds, sprMediumBounds, sprHardBounds, sprSettingsBounds;

    public ScrMainMenu(Game _game, Assets _assets) {
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



        sprEasyBounds = new Rectangle(240, 1350, 600, 250);
        sprMediumBounds = new Rectangle(240, 1000, 600, 250);
        sprHardBounds = new Rectangle(240, 650, 600, 250);
        sprSettingsBounds = new Rectangle(240, 300, 600, 250);

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

        batch.draw(assets.manager.get("easybtn.png", Texture.class), 240, 1350);
        batch.draw(assets.manager.get("medbtn.png", Texture.class), 240, 1000);
        batch.draw(assets.manager.get("hardbtn.png", Texture.class), 240, 650);
        batch.draw(assets.manager.get("settingsbtn.png", Texture.class), 240, 300);

        assets.manager.get("easy.ttf", BitmapFont.class).draw(batch, "EASY", 350, 1535);
        assets.manager.get("medium.ttf", BitmapFont.class).draw(batch, "MEDIUM", 290, 1180);
        assets.manager.get("hard.ttf", BitmapFont.class).draw(batch, "HARD", 325, 840);
        assets.manager.get("settings.ttf", BitmapFont.class).draw(batch, "SETTINGS", 270, 465);

        batch.end();

        Screens();
    }

    //changes screens based on sprite touched
    void Screens() {
        if (Gdx.input.justTouched()) {
            camera.unproject(vTouched.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (sprEasyBounds.contains(vTouched.x, vTouched.y)) {
                nMode = 1;
                ScrGame.nRange = 302;
                ScrGame.nArrowSpeed = 37;
                game.setScreen(new ScrGame(game, assets));
            }
            if (sprMediumBounds.contains(vTouched.x, vTouched.y)) {
                nMode = 2;
                ScrGame.nRange = 242;
                ScrGame.nArrowSpeed = 37;
                game.setScreen(new ScrGame(game, assets));
            }
            if (sprHardBounds.contains(vTouched.x, vTouched.y)) {
                nMode = 3;
                ScrGame.nRange = 202;
                ScrGame.nArrowSpeed = 37;
                game.setScreen(new ScrGame(game, assets));
            }
            if (sprSettingsBounds.contains(vTouched.x, vTouched.y)) {
                game.setScreen(new ScrShop(game, assets));
            }
        }
    }


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
}
