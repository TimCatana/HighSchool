package com.catani.gamegravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


//** ScrollingBackground Class to be used in ScrGame Class **\\
public class ScrollingBackground {

    Texture[] txtBackgrounds = new Texture[3];

    public float getX, getX2;
    float fX = 0, fX2 = 1920, fY = 0;
    float fScrollSpeed;
    private int nImage = 0, nImage2 = 0;
    private int nScore = 0;
    private int nChooseImage;

    public ScrollingBackground() {
        txtBackgrounds[0] = new Texture("background1.png");
        txtBackgrounds[1] = new Texture("backgroundimage2.jpg");
        txtBackgrounds[2] = new Texture("backgroundimage3.jpg");
    }

    public void render(SpriteBatch batch) {
        getX = fX;
        getX2 = fX2;

        batch.draw(txtBackgrounds[nImage], fX, fY, Constants.WORLDWIDTH, Constants.WORLDHEIGHT);
        batch.draw(txtBackgrounds[nImage], fX2, fY, Constants.WORLDWIDTH, Constants.WORLDHEIGHT);
        scroll();
        // Change();
    }

    public void staticImage(SpriteBatch batch){
        batch.draw(txtBackgrounds[nImage], fX, fY, Constants.WORLDWIDTH, Constants.WORLDHEIGHT);
        batch.draw(txtBackgrounds[nImage], fX2, fY, Constants.WORLDWIDTH, Constants.WORLDHEIGHT);
    }

    private void scroll() {
        //System.out.println("X1 = "+ fX + " X2 = " + fX2);
        fX -= fScrollSpeed;
        fX2 -= fScrollSpeed;
        if (fX <= Constants.WORLDWIDTH * -1) { //if off screen to the left
            fX = fX2 + Constants.WORLDWIDTH; //move to off screen to the right
        }
        if (fX2 <= Constants.WORLDWIDTH * -1) {
            fX2 = fX + Constants.WORLDWIDTH;
        }
    }

    private void Change() { //** NEEDS MAJOR EDITING WHEN WE START USING THIS **
        nScore++;
        if (nScore % 500 == 0) {
            nChooseImage = MathUtils.random(0, 2);
        }
        if (fX == Constants.WORLDWIDTH) { //change image when image is off screen
            nImage = nChooseImage;
        }
        if (fX2 == Constants.WORLDWIDTH) {
            nImage2 = nChooseImage;
        }
    }

    public float getScrollSpeed() {
        return fScrollSpeed;
    }

    public void setSrcollSpeed(float scollSpeed) {
        fScrollSpeed = scollSpeed;
    }
}

