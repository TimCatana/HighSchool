package com.catani.gamegravity.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.catani.gamegravity.Constants;

/**
 * Created by am44_000 on 2018-09-26.
 */

public class ObsSpinning extends SprObstacle {
    float fRotation;
    Polygon hitbox;
    public ObsSpinning(String path, float xVel, float fRotation){
        super(path, xVel);
        setOriginCenter();
        this.fRotation = fRotation;
        //have to use get methods for it to work correctly
        hitbox = new Polygon(new float[]{getX(),getY(),getX()+getWidth(),getY(),getX()+getWidth(),getY()+getHeight(),getX(),getY()+getHeight()});
        hitbox.setPosition(getX(),getY());
        hitbox.setOrigin(getX()+(getWidth()/2),getY()+ (getHeight()/2));

    }
    @Override
    public void render(SpriteBatch batch){
        setPosition(getX() + vVel.x, getY() + vVel.y);
        hitbox.setPosition(getX(),getY());
        rotate(fRotation);
        hitbox.rotate(fRotation);
        draw(batch);
    }


    //Collison function adapted from Stackoverflow user 1337ingDisorder https://stackoverflow.com/questions/28522313/java-libgdx-how-to-check-polygon-collision-with-rectangle-or-circle
    @Override
    public boolean isHit(Rectangle r) {
        Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width,
                r.height, 0, r.height });
        rPoly.setPosition(r.x, r.y);
        return Intersector.overlapConvexPolygons(rPoly, hitbox);
    }

    @Override
    public void setXVel(float xVel){
        vVel.x = xVel + (vVel.x*0.25f);
    }
    public Polygon getHitbox(){
        return hitbox;
    }
}