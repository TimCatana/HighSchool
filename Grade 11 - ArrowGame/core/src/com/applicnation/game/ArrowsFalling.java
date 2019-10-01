package com.applicnation.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ArrowsFalling {


    SpriteBatch batch;
    Assets assets;
    Sprite sArrow;
    int nArrowDirection; // chooses which arrow to draw
    float fY = 950, fX;

    public ArrowsFalling(int _nArrow, Assets _assets) {
        batch = new SpriteBatch();
        assets = _assets;
        if (ScrShop.isNormal) {
            DrawArrow(_nArrow, "rightarrow.png", "leftarrow.png", "uparrow.png", "downarrow.png");
        } else if (ScrShop.isShrek) {
            DrawArrow(_nArrow, "donkey.png", "Shrek.png", "gingerbreadman.png", "humptydumpty.png");
        } else if (ScrShop.isShapes) {
            DrawArrow(_nArrow, "orangeoctogon.png", "redtriangle.png", "greencircle.png", "bluesquare.png");
        } else if (ScrShop.isFood) {
            DrawArrow(_nArrow, "pizza.png", "cake.png", "burger.png", "hotdog.png");
        }  else if (ScrShop.isTheWay) {
            DrawArrow(_nArrow, "greenguy.png", "orangeguy.png", "blueguy.png", "pinkguy.png");
        }
    }

    //takes nArrow and based on it it draws the certan sprite and the exact location so that the art looks good
    public Sprite DrawArrow(int _nArrow, String img1, String img2, String img3, String img4) {
        nArrowDirection = _nArrow;
        if (_nArrow == 0) {
            fX = 195;
            sArrow = new Sprite(assets.manager.get(img1, Texture.class));
        } else if (nArrowDirection == 1) {
            fX = 195;
            sArrow = new Sprite(assets.manager.get(img2, Texture.class));
        } else if (nArrowDirection == 2) {
            fX = 206;
            sArrow = new Sprite(assets.manager.get(img3, Texture.class));
        } else if (nArrowDirection == 3) {
            fX = 206;
            sArrow = new Sprite(assets.manager.get(img4, Texture.class));
        }
        return sArrow;
    }

    public void render(OrthographicCamera camera) {
        Speed();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(sArrow, fX, fY);
        batch.end();
    }

    public void Speed() {
        if (ScrGame.nScore >= 0 && ScrGame.nScore < 10) {
            fY -= 5;
            ScrGame.nFallingArrowTimerCount = 37;
        }
        if (ScrGame.nScore >= 10 && ScrGame.nScore < 20) {
            fY -= 5.5;
            ScrGame.nFallingArrowTimerCount = 35;
        }
        if (ScrGame.nScore >= 20 && ScrGame.nScore < 30) {
            fY -= 6;
            ScrGame.nFallingArrowTimerCount = 33;
        }
        if (ScrGame.nScore >= 30 && ScrGame.nScore < 40) {
            fY -= 6.5;
            ScrGame.nFallingArrowTimerCount = 31;
        }
        if (ScrGame.nScore >= 40 && ScrGame.nScore < 50) {
            fY -= 7;
            ScrGame.nFallingArrowTimerCount = 29;
        }
        if (ScrGame.nScore >= 50 && ScrGame.nScore < 60) {
            fY -= 7.5;
            ScrGame.nFallingArrowTimerCount = 27;
        }
        if (ScrGame.nScore >= 60 && ScrGame.nScore < 70) {
            fY -= 8;
            ScrGame.nFallingArrowTimerCount = 25;
        }
        if (ScrGame.nScore >= 70 && ScrGame.nScore < 80) {
            fY -= 8.5;
            ScrGame.nFallingArrowTimerCount = 24;
        }
        if (ScrGame.nScore >= 80 && ScrGame.nScore < 90) {
            fY -= 9;
            ScrGame.nFallingArrowTimerCount = 22;
        }
        if (ScrGame.nScore >= 90 && ScrGame.nScore < 100) {
            fY -= 9.5;
            ScrGame.nFallingArrowTimerCount = 20;
        }
        if (ScrGame.nScore >= 100 && ScrGame.nScore < 150) {
            fY -= 10;
            ScrGame.nFallingArrowTimerCount = 18;
        }
        if (ScrGame.nScore >= 150 && ScrGame.nScore < 200) {
            fY -= 10.5;
            ScrGame.nFallingArrowTimerCount = 17;
        }
        if (ScrGame.nScore >= 200 && ScrGame.nScore < 300) {
            fY -= 11;
            ScrGame.nFallingArrowTimerCount = 16;
        }
        if (ScrGame.nScore >= 300 && ScrGame.nScore < 400) {
            fY -= 11.5;
            ScrGame.nFallingArrowTimerCount = 14;
        }
        if (ScrGame.nScore >= 400) {
            fY -= 12;
            ScrGame.nFallingArrowTimerCount = 12;
        }

    }
}
