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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ScrGame implements Screen, InputProcessor {

    Game game;
    Assets assets;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    //images
    private Vector3 vTouched;
    private Rectangle UArrowBounds, DArrowBounds, LArrowBounds, RArrowBounds;
    //arrays & arraylists
    ArrayList<ArrowsFalling> alArrFall = new ArrayList<ArrowsFalling>();
    ArrowsFalling AF;
    //integers & floats
    public static int nArrowDirection, nArrowSpeed = 37, nFallingArrowTimerCount;
    public static int nScore, nRange;
    static Music minecraftsong, thewaysong;

    public ScrGame(Game _game, Assets _assets) {
        nScore = 0;
        this.game = _game;
        this.assets = _assets;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(480, 800, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        batch = new SpriteBatch();
        //(hit) tapboxes
        RArrowBounds = new Rectangle(350, 80, 93, 75);
        LArrowBounds = new Rectangle(40, 80, 93, 75);
        UArrowBounds = new Rectangle(40, 250, 75, 93);
        DArrowBounds = new Rectangle(350, 260, 75, 93);
        //vector 3 for tap detection
        vTouched = new Vector3();

        minecraftsong = assets.manager.get("minecraftsong.mp3", Music.class);
        thewaysong = assets.manager.get("thewaymusic.mp3", Music.class);

        RandomArrow();
        resize(480, 800);


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        nArrowSpeed -= delta; //timer that controls how long before the next arrow in the array list is grabbed (not exact (yet))
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(assets.manager.get("ArrowGameBackground.png", Texture.class), 0, 0);

        drawtappableimages();


//        DrawScore(nScore);

        DrawConveyors();
        batch.end();

        if (nArrowSpeed <= 0) {
            RandomArrow();
            nArrowSpeed = nFallingArrowTimerCount;
        }

        //runs the falling arrow arraylist
        for (ArrowsFalling AF : alArrFall) {
            AF.render(camera);
            if (alArrFall.get(0).fY <= 0) {
                stopMusic();
                game.setScreen(new ScrMainMenu(game, assets));
            }
        }

        TouchDetection();
    }

    void tappableimages(String img1, String img2, String img3, String img4) {
        batch.draw(assets.manager.get(img1, Texture.class), 350, 80);
        batch.draw(assets.manager.get(img2, Texture.class), 40, 80);
        batch.draw(assets.manager.get(img3, Texture.class), 40, 250);
        batch.draw(assets.manager.get(img4, Texture.class), 350, 260);
    }

    void drawtappableimages() {
        if (ScrShop.isNormal && !ScrShop.isShrek && !ScrShop.isShapes && !ScrShop.isFood && !ScrShop.isTheWay) {
            tappableimages("rightarrow.png", "leftarrow.png", "uparrow.png", "downarrow.png");
        } else if (ScrShop.isShrek && !ScrShop.isShapes && !ScrShop.isNormal && !ScrShop.isFood && !ScrShop.isTheWay) {
            tappableimages("donkey.png", "Shrek.png", "gingerbreadman.png", "humptydumpty.png");
        } else if (ScrShop.isShapes && !ScrShop.isShrek && !ScrShop.isNormal && !ScrShop.isFood && !ScrShop.isTheWay) {
            tappableimages("orangeoctogon.png", "redtriangle.png", "greencircle.png", "bluesquare.png");
        } else if (ScrShop.isFood && !ScrShop.isShrek && !ScrShop.isNormal && !ScrShop.isShapes && !ScrShop.isTheWay) {
            tappableimages("pizza.png", "cake.png", "burger.png", "hotdog.png");
            minecraftsong.play();
        }else if (ScrShop.isTheWay && !ScrShop.isFood && !ScrShop.isShrek && !ScrShop.isNormal && !ScrShop.isShapes) {
            tappableimages("greenguy.png", "orangeguy.png", "blueguy.png", "pinkguy.png");
            thewaysong.play();
        }
    }

    //runs arraylist of falling arrows using random integers (nArrowDirection) which calls what sprite to draw
    void RandomArrow() {
        nArrowDirection = random(3);
        AF = new ArrowsFalling(nArrowDirection, assets);
        alArrFall.add(AF);
    }

    //Removes the arrows according to given requirement
    void RemovingArrows(int _nArrowDirection, int _nRange) {
        if (Gdx.input.justTouched()) {
            camera.unproject(vTouched.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (alArrFall.get(0).nArrowDirection == _nArrowDirection) {
                if (alArrFall.get(0).fY <= _nRange && alArrFall.get(0).fY >= 102) {
                    alArrFall.remove(0);
                    nScore += 1;
                } else {
                    stopMusic();
                    game.setScreen(new ScrMainMenu(game, assets));
                }
            } else {
                stopMusic();
                game.setScreen(new ScrMainMenu(game, assets));
            }

        }
    }

    //if you add a tappable button put it here
    void TouchDetection() {
        if (Gdx.input.justTouched()) {
            camera.unproject(vTouched.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (RArrowBounds.contains(vTouched.x, vTouched.y)) {
                RemovingArrows(0, nRange);
            }
            if (LArrowBounds.contains(vTouched.x, vTouched.y)) {
                RemovingArrows(1, nRange);
            }
            if (UArrowBounds.contains(vTouched.x, vTouched.y)) {
                RemovingArrows(2, nRange);
            }
            if (DArrowBounds.contains(vTouched.x, vTouched.y)) {
                RemovingArrows(3, nRange);
            }
        }

    }

    //Draws Green and red conveyerbelts when needed
    void DrawConveyors() {

        if (ScrMainMenu.nMode == 1) {
            if (alArrFall.get(0).fY >= nRange) {
                batch.draw(assets.manager.get("easymodegreenconveyer.png", Texture.class), 154, 102);
            } else {
                batch.draw(assets.manager.get("easymoderedconveyer.png", Texture.class), 154, 102);
            }
        }

        if (ScrMainMenu.nMode == 2) {
            if (alArrFall.get(0).fY >= nRange) {
                batch.draw(assets.manager.get("mediummodegreenconveyer.png", Texture.class), 154, 102);
            } else {
                batch.draw(assets.manager.get("mediummoderedconveyer.png", Texture.class), 154, 102);
            }
        }

        if (ScrMainMenu.nMode == 3) {
            if (alArrFall.get(0).fY >= nRange) {
                batch.draw(assets.manager.get("hardmodegreenconveyer.png", Texture.class), 154, 102);
            } else {
                batch.draw(assets.manager.get("hardmoderedconveyer.png", Texture.class), 154, 102);
            }
        }

    }

    //    void DrawScore(int _score) {
//        if (_score < 10) {
//            assets.manager.get("Fonts/scoreboard.ttf", BitmapFont.class).draw(batch, Integer.toString(nScore), 50, 750);
//        }
//        if (_score > 9 && _score < 100) {
//            assets.manager.get("Fonts/scoreboard.ttf", BitmapFont.class).draw(batch, Integer.toString(nScore), 25, 750);
//        }
//        if (_score > 100) {
//            assets.manager.get("Fonts/scoreboard.ttf", BitmapFont.class).draw(batch, Integer.toString(nScore), 10, 750);
//        }
//    }

    void stopMusic(){
        minecraftsong.stop();
        thewaysong.stop();
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

    //disposes so that there are no memory leakes, so no extra memory is used
    @Override
    public void dispose() {
        batch.dispose();
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
