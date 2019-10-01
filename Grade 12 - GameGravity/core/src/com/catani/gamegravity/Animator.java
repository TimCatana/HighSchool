package com.catani.gamegravity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator { //changed it back to this, its just simpler to read...  -_-
    Assets assets;
    TextureRegion[] txtRegAnimationFrames;
    Animation AniBtnFade;
    float ElapsedTime;
    Animation<TextureRegion> AnmCreateAnimation;


    public Animator(Assets _assets) {
        this.assets = _assets;
        readyFade(2, 6, 0);
    }

    //ready the fade animation
    void readyFade(int nColumns, int nRows, int index) {
        Texture SpriteSheet = assets.manager.get("AniBtnPlaySprite.png", Texture.class);
        TextureRegion[][] tmp = TextureRegion.split(SpriteSheet, SpriteSheet.getWidth() / nColumns, SpriteSheet.getHeight() / nRows);
        txtRegAnimationFrames = new TextureRegion[nColumns * nRows];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                txtRegAnimationFrames[index++] = tmp[i][j];
            }
        }

        AnmCreateAnimation = new Animation<TextureRegion>(0.10f, txtRegAnimationFrames);

        AniBtnFade = new Animation(0.10f, txtRegAnimationFrames);
    }

    public void drawAni(SpriteBatch batch, int x, int y) { // nImgNum = 1
        ElapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) AniBtnFade.getKeyFrame(ElapsedTime, true), x, y);
    }
}
