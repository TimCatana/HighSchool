package com.catani.gamegravity.obstacles;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.catani.gamegravity.Constants;

/**
 * Created by am44_000 on 2018-09-26.
 */

public class ObsFlying extends SprObstacle {
    Vector2 vVel;
    Circle hitbox;
    public ObsFlying(String path, float xVel){
        super(path, xVel);
        setPosition(1920/2,1080/2);
        setSize(200,200);
        //setFlip(true,false);
        hitbox = new Circle(getX(),getY(),getWidth()/2);
    }
    @Override
    public void render(SpriteBatch batch){
        setX(getX() + super.vVel.x);
        setY((MathUtils.sin(getX()/1300*MathUtils.PI2)*500)+500);
        hitbox.setX(getX()+getWidth()/2);
        hitbox.setY(getY()+getHeight()/2);
        draw(batch);
    }


    @Override
    public boolean isHit(Rectangle player) {
        return Intersector.overlaps(hitbox, player);
    }
}
