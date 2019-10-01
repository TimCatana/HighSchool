package com.catani.gamegravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SprCollectables extends Sprite {
    Vector2 vVel;

    public SprCollectables(String path, float xVel) {
        super(new Texture(path));
        vVel = new Vector2(xVel, 0);
        setPosition(1920 / 2, 1080 / 2);
    }

    public void render(SpriteBatch batch) {
        setPosition(getX() + vVel.x, getY() + vVel.y);
        draw(batch);
    }
    public void reDraw() {
        if (getX() < 0 - getWidth()) {
            setX(Constants.WORLDWIDTH);
            setY(MathUtils.random(Constants.FLOOR, Constants.CEILING - getHeight()));
        }
    }



    public boolean isHit(Rectangle player){
        if (player.overlaps(getBoundingRectangle())) {
            return true;
        }
        return false;
    }
    public float getXVel(){
        return vVel.x;
    }
    public void setXVel(float xVel){
        vVel.x = xVel;
    }
}

