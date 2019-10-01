package com.catani.gamegravity.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.catani.gamegravity.Constants;

/**
 * Created by am44_000 on 2018-09-26.
 */

public class SprObstacle extends Sprite {
    Vector2 vVel;

    public SprObstacle(String path, float xVel) {
        super(new Texture(path));
        vVel = new Vector2(xVel, 0);
        setPosition(0, 0);
        // setFlip(true,false);
    }

    public void render(SpriteBatch batch) {
        setPosition(getX() + vVel.x, getY() + vVel.y);
        draw(batch);
    }

    public void reDraw(){
        setX(Constants.WORLDWIDTH + getWidth());
        setY(MathUtils.random(Constants.FLOOR, Constants.CEILING - getHeight()));
        //setX(-300);
    }

    public boolean isHit(Rectangle player) {
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
    public void reset(){
        setPosition(-10,0);
    }
}
