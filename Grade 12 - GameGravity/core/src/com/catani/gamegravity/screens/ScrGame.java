package com.catani.gamegravity.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.catani.gamegravity.Assets;
import com.catani.gamegravity.Constants;
import com.catani.gamegravity.GamGravity;
import com.catani.gamegravity.obstacles.ObsFlying;
import com.catani.gamegravity.obstacles.ObsPitfall;
import com.catani.gamegravity.obstacles.ObsSpinning;
import com.catani.gamegravity.ScrollingBackground;
import com.catani.gamegravity.SprCollectables;
import com.catani.gamegravity.obstacles.ObsTree;
import com.catani.gamegravity.obstacles.SprObstacle;
import com.sun.org.apache.bcel.internal.classfile.Constant;

public class ScrGame implements Screen, InputProcessor {
    GamGravity game;

    ShapeRenderer sr;

    SprObstacle obsShip;
    ObsFlying obsFlying;
    ObsSpinning obsSpinning;
    ObsPitfall obsPitfall;
    ObsTree obsTree;

    SprCollectables sprCoin;
    Array<SprObstacle> obstacles;
    int nObsAvoided, nObstacle;

    Music musElectro;

    int nScore, ncoinCounter = 0;
    float fGameSpeed, fPixelsRan;

    public ScrGame(GamGravity _game) {

        this.game = _game;

        Gdx.input.setInputProcessor(this);

        sr = new ShapeRenderer();

        obsShip = new SprObstacle("ship.png", -18);
        obsPitfall = new ObsPitfall("floorhole.png", -13);
        obsFlying = new ObsFlying("Ameer.png", -5);
        obsSpinning = new ObsSpinning("Axe.png", -13, 1);
        obsTree = new ObsTree("Tree.png", -10);
        obstacles = new Array<SprObstacle>(new SprObstacle[]{obsPitfall});

        sprCoin = new SprCollectables("coin.png", -10);

        musElectro = game.assets.manager.get("electroman.mp3", Music.class);
        musElectro.setLooping(true);
        nScore = 0;
        nObsAvoided = 0;
        fGameSpeed = 10;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        nScore = 0;

        resetObstacles();

        fGameSpeed = 10;
        game.sbg.setSrcollSpeed(fGameSpeed);
        fPixelsRan = 0;

        ncoinCounter = 0;
        game.chrMain.reset();
        musElectro.play();
    }

    public void resetObstacles() {
        obstacles.clear();
        obsPitfall.setX(Constants.WORLDWIDTH + obsPitfall.getWidth());
        obsPitfall.setY(0);
        obsPitfall.setTexture(game.assets.manager.get("floorhole.png", Texture.class));
        obstacles.add(obsPitfall);
        obsPitfall.isPitfallFlipped = false;
        obsTree.isTreeFlipped = false;
    }


    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(230 / 255f, 220 / 255f, 200 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.sbg.render(game.batch);

        fPixelsRan += fGameSpeed;
        nScore = (int) ((fPixelsRan / Constants.WORLDWIDTH) * 3); //convert pixels ran to score units
        if (nScore > 2) {
            for (SprObstacle obstacle : obstacles) {
                obstacle.render(game.batch);
                obstacle.setXVel(fGameSpeed * -1);

                if (obstacle.getX() < -300) {
                    redrawObstacles(obstacle);
                }

                if (obstacle.isHit(game.chrMain.getBoundingRectangle())) {
                    deathAnimations(obstacle);
                }

                System.out.println(obstacle);

            }
        }

        sprCoin.render(game.batch);
        sprCoin.setXVel(fGameSpeed * -1);
        sprCoin.reDraw();
        CoinHit();

        game.chrMain.render(game.batch);
        game.chrMain.constrain();

        text();

        game.batch.end();


        //everything past this point is bug testing stuff
//        System.out.println(game.chrMain.getY());
//        System.out.println("HTSRT " + game.chrMain.getYVel());
        System.out.println("SCROLL SPEED " + fGameSpeed);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(game.camera.combined);
//        sr.setColor(Color.RED);
//        sr.rect(obsPitfall.hitbox.getX(), obsPitfall.hitbox.getY(), obsPitfall.hitbox.getWidth(), obsPitfall.hitbox.getHeight());

//        sr.setColor(Color.GOLD);
//        sr.rect(game.chrMain.getX(), game.chrMain.getY(), game.chrMain.getWidth(), game.chrMain.getHeight());
//        sr.setColor(Color.RED);
//        sr.rect(obsTree.hitbox.getX(), obsTree.hitbox.getY(), obsTree.hitbox.getWidth(), obsTree.hitbox.getHeight());
      //  sr.polygon(obsTree.);
        //sr.polygon(obsSpinning.getHitbox().getTransformedVertices());
//        sr.setColor(Color.BLACK);
//        sr.line(0, FLOOR, 1920, FLOOR);
//        sr.setColor(Color.BLACK);
//        sr.line(0, CEILING, 1920, CEILING);
        sr.end();
//        System.out.println("HITSSS " + obsTree.isFlipY());
        System.out.println(obstacles);
    }

    public void redrawObstacles(SprObstacle obstacle) {

        obstacles.clear();

        if (nScore < 15) {
            nObstacle = MathUtils.random(0, 1);
        } else if (nScore >= 15 && nScore < 30) {
            nObstacle = MathUtils.random(0, 2);
        } else if (nScore >= 30 && nScore < 50) {
            nObstacle = MathUtils.random(0, 3);
        } else if (nScore >= 50) {
            nObstacle = MathUtils.random(0, 4);
        }

        obstacle.setX(Constants.WORLDWIDTH + 200);

        if (nObstacle == 0) {
            obstacles.add(obsPitfall);
            obsPitfall.reDraw();
        } else if (nObstacle == 1) {
            obstacles.add(obsTree);
            obsTree.reDraw();
        } else if (nObstacle == 2) {
            obstacles.add(obsSpinning);
            obsSpinning.reDraw();
        } else if (nObstacle == 3) {
            obstacles.add(obsShip);
            obsShip.reDraw();
        } else if (nObstacle == 4) {
            obstacles.add(obsFlying);
            obsFlying.reDraw();
        }

        nObsAvoided++;

        if (nObsAvoided % 1 == 0 && nObsAvoided != 0 && fGameSpeed < 32) {
            fGameSpeed += 0.85;
        }
    }


    public void deathAnimations(SprObstacle obstacle) {
        if (obstacle == obsPitfall) {
            obsPitfall.deathAnimation(game.chrMain);
            if (game.chrMain.getY() + game.chrMain.getHeight() <= 10 || game.chrMain.getY() >= Constants.CEILING) {
                game.changeScreen(Screens.SCRGAMEOVER);
            }
        } else {
            game.changeScreen(Screens.SCRGAMEOVER);
        }
    }


    public void CoinHit() {
        if (sprCoin.isHit(game.chrMain.getBoundingRectangle())) {
            ncoinCounter += 1;
            sprCoin.setX(Constants.WORLDWIDTH);
            sprCoin.setY(MathUtils.random(Constants.FLOOR, Constants.CEILING - sprCoin.getHeight()));
        }
    }

    private void text() {
        game.drawText(Assets.Fonts.SCORE, Integer.toString(nScore) + "m", 0, 1065);

        if (ncoinCounter < 10) {
            game.drawText(Assets.Fonts.SCORE, Integer.toString(ncoinCounter), 1845, 1065);
        } else if (ncoinCounter >= 10 && ncoinCounter < 100) {
            game.drawText(Assets.Fonts.SCORE, Integer.toString(ncoinCounter), 1775, 1065);
        } else if (ncoinCounter >= 100 && ncoinCounter < 1000) {
            game.drawText(Assets.Fonts.SCORE, Integer.toString(ncoinCounter), 1705, 1065);
        } else if (ncoinCounter >= 1000 && ncoinCounter < 10000) {
            game.drawText(Assets.Fonts.SCORE, Integer.toString(ncoinCounter), 1635, 1065);
        }
    }


    @Override
    public void hide() {
        musElectro.stop();
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


    //disposes so that there are no memory leakes, so no extra memory is used
    @Override
    public void dispose() {
        game.batch.dispose();
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
        if (game.chrMain.getY() == Constants.FLOOR || game.chrMain.getY() == Constants.CEILING - game.chrMain.getHeight()) {
            if (character == ' ') {
                game.chrMain.flipY();
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("MouseX: " + screenX + " Mouse Y: " + screenY);

        if (game.chrMain.getY() == Constants.FLOOR) {
            game.chrMain.setYVel(20);
        } else if (game.chrMain.getY() == Constants.CEILING - game.chrMain.getHeight()) {
            game.chrMain.setYVel(-20);
        }

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