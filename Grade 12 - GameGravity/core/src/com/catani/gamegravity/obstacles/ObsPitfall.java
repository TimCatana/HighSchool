package com.catani.gamegravity.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.catani.gamegravity.Constants;
import com.catani.gamegravity.SprChar;
import com.catani.gamegravity.screens.Screens;

public class ObsPitfall extends SprObstacle {
    public Rectangle hitbox;
    public boolean isPitfallFlipped;
    private Texture ceilingHole, floorHole;


    public ObsPitfall(String path, float xVel) {
        super(path, xVel);
        hitbox = new Rectangle(getX(), getY(), getWidth() - 100, Constants.FLOOR + 1);
        ceilingHole = new Texture("ceilinghole.png");
        floorHole = new Texture("floorhole.png");
    }

    public void render(SpriteBatch batch) {
        setX(getX() + vVel.x);
        hitbox.setPosition(getX() + 50, getY());
        draw(batch);
    }

    @Override
    public void reDraw() {
        setX(Constants.WORLDWIDTH + getWidth());
        isPitfallFlipped = MathUtils.randomBoolean();
        if (isPitfallFlipped) {
            setTexture(ceilingHole);
            setY(Constants.CEILING - 2);
        } else {
            setTexture(floorHole);
            setY(0);
        }
    }

    public boolean isHit(Rectangle player) {
        return Intersector.overlaps(hitbox, player);
    }

    public void deathAnimation(SprChar chrMain) {
        if (isPitfallFlipped) {
            chrMain.setYVel(20);
        } else {
            chrMain.setYVel(-20);
        }

        //for player hitting pitfall wall code
        if (chrMain.getX() >= hitbox.getX() + hitbox.getWidth() - 50) {
            chrMain.setX(hitbox.getX() + hitbox.getWidth() - 50);
        }
    }
}


