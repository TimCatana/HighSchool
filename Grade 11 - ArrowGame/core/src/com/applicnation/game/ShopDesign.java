package com.applicnation.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by timi2 on 2018-01-03.
 */

public class ShopDesign {

    SpriteBatch batch;
    Assets assets;
    public int fRectX = 40, fRectY = 1000, fSkinX = 125, fSkinY = 570;

    public ShopDesign(int _location, Assets assets) {
        batch = new SpriteBatch();
        this.assets = assets;
        XLocation(_location);
    }

    public void RenderSquares(OrthographicCamera camera) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(assets.manager.get("shopsquare.png", Texture.class), fRectX, fRectY);
        batch.end();
    }

//    public void RenderSkins(OrthographicCamera camera) {
//        batch.begin();
//        batch.setProjectionMatrix(camera.combined);
//        batch.draw(assets.manager.get("leftarrow.png", Texture.class), fSkinX, fSkinY);
//        batch.end();
//    }

    public int XLocation(int nLocation){
        if(nLocation % 2 == 1){
            fRectX = 50;
        }
        if(nLocation % 2 == 0){
            fRectX = 580;
        }
        return fRectX;
    }
}

