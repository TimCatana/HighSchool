class Shop { //<>//
  int fLoc = 0;
  float fX, fX2, fY, fY2, fW, fDiam, fH;
  boolean isStarted, isSpecial;
  PImage imgBall;
  Ball ball;
  int nRad = 32;
  Skin skin;
  Skin [] arSkins = new Skin[24];
  Shop() {
    fX = 15;
    fX2 = 105;
    fY = 100;
    fY2 = 192;
    fW = 180;
    fDiam = 64;
    fH = 180;
    isStarted = false;
    isSpecial = false;
    imgBall = loadImage("dennis.png");
     arSkins [0] = new Skin(15, 60, 180, #f92d4f, 64, 0, false, 0, "");
    arSkins [1] = new Skin(215, 60, 180, #2B8665, 64, 0, false, 0, "");
    arSkins [2] = new Skin(415, 60, 180, #6910E0, 64, 0, false, 0, "");
    arSkins [3] = new Skin(615, 60, 180, #185D71, 64, 0, false, 0, "");
    arSkins [4] = new Skin(815, 60, 180, #2df9d7, 64, 0, false, 0, "");
    arSkins [5] = new Skin(1015, 60, 180, #0B8B95, 64, 0, false, 0, "");
    arSkins [6] = new Skin(15, 260, 180, #7E99D3, 64, 0, false, 0, "");
    arSkins [7] = new Skin(215, 260, 180, #37D883, 64, 0, false, 0, "");
    arSkins [8] = new Skin(415, 260, 180, #0B9B95, 64, 0, false, 0, "");
    arSkins [9] = new Skin(615, 260, 180, #708155, 64, 0, false, 0, "");
    arSkins [10] = new Skin(815, 260, 180, #198FBC, 64, 0, false, 0, "");
    arSkins [11] = new Skin(1015, 260, 180, #7E1CE8, 64, 0, false, 0, "");
    arSkins [12] = new Skin(15, 460, 180, #a59e9e, 64, 0, true, 2, "");
    arSkins [13] = new Skin(215, 460, 180, #F76C1B, 64, 0, false, 0, "");
    arSkins [14] = new Skin(415, 460, 180, #0F1317, 64, 0, false, 0, "");
    arSkins [15] = new Skin(615, 460, 180, #9FA9B7, 64, 0, false, 0, "");
    arSkins [16] = new Skin(815, 460, 180, #D88CB9, 64, 0, false, 0, "");
    arSkins [17] = new Skin(1015, 460, 180, #F50290, 64, 0, false, 0, "");
    arSkins [18] = new Skin(15, 660, 180, #a59e9e, 64, 0, true, 1, "fidget.png");
    arSkins [19] = new Skin(215, 660, 180, #a59e9e, 64, 0, true, 1, "rainbow.png");
    arSkins [20] = new Skin(415, 660, 180, #a59e9e, 64, 0, true, 1, "camo.png");
    arSkins [21] = new Skin(615, 660, 180, #a59e9e, 64, 0, true, 1, "flappybird.png");
    arSkins [22] = new Skin(815, 660, 180, #a59e9e, 64, 0, true, 1, "dennis.png");
    arSkins [23] = new Skin(1015, 660, 180, #a59e9e, 64, 0, true, 1, "Bird.png");
  }
  void run() {
    imageMode(CENTER);
    background(cBack);
    for (Skin skin : arSkins) {
      skin.update();
    }
    drawRoof(false);
    skin = arSkins[0];
    if (skin.fRx <= 15) {
      for (Skin skin : arSkins) {
        if (keyPressed == true) {
          if (keyCode== LEFT) {
            skin.fRx += 10;
          }
        }
      }
    }
    skin = arSkins[23];
    if (skin.fRx >= 402) {
      for (Skin skin : arSkins) {
        if (keyPressed == true) {
          if (keyCode== RIGHT) {
            skin.fRx -= 10;
          }
        }
      }
    }
  }
}