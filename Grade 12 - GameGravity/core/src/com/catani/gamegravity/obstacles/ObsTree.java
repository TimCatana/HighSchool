package com.catani.gamegravity.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.catani.gamegravity.Constants;

public class ObsTree extends SprObstacle {
   // public Rectangle hitbox;
    public boolean isTreeFlipped;
    private Polygon hitbox;

    public ObsTree(String path, float xVel) {
        super(path, xVel);
        hitbox = new Polygon(new float[]{getX(),getY()+78,getX()+49,getHeight(),getX()+68,getHeight(),getWidth(),getY()+78});
    }

    public void render(SpriteBatch batch) {
        setX(getX() + vVel.x);
        hitbox.setPosition(getX(), getY());
        draw(batch);
    }

    @Override
    public void reDraw() {
        setX(Constants.WORLDWIDTH + getWidth());
        isTreeFlipped = MathUtils.randomBoolean();
        if (isTreeFlipped) {
            if (!isFlipY()) {
                flip(false, true);
            }
            setY(Constants.CEILING - getHeight());
        } else if (!isTreeFlipped) {
            if (isFlipY()) {
                flip(false, true);
            }
            setY(Constants.FLOOR + 3);
        }
    }

    //Collison function adapted from Stackoverflow user 1337ingDisorder https://stackoverflow.com/questions/28522313/java-libgdx-how-to-check-polygon-collision-with-rectangle-or-circle
    @Override
    public boolean isHit(Rectangle r) {
        Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width,
                r.height, 0, r.height });
        rPoly.setPosition(r.x, r.y);
        return Intersector.overlapConvexPolygons(rPoly, hitbox);
    }


}