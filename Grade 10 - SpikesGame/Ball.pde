class Ball {
  public float fX, fY, fW, fH, fDx, fDy; 
  color cBall;
  boolean isSpecial;
  int nSpecial, nEndAnimation = 0;
  PImage imgBall;
  String sImg;
  Ball(float _fX, float _fY, float _fW, float _fH) {
    fX = _fX;
    fY = _fY;
    fW = _fW;
    fH = _fH;
    fDy = 7;
    fX = constrain(fX, nRad, width - nRad);
    cBall = #f92d4f;
    isSpecial = false;
    if (isSpecial) {
      imgBall = loadImage(sImg);
    }
    imageMode(CENTER);
  }

  public void update(float _fDx, float _fDy) {
    imageMode(CENTER);
    fDx = _fDx;
    fDy = _fDy;
    fX += fDx;

    fDy += 0.95;
    fY += fDy;

    fX = constrain(fX, nRad, width - nRad);
    fY = constrain(fY, nRad, height - nRad);
    noStroke();
    if (isSpecial) {
      specialDraw();
    } else {
      fill(cBall);
      ellipse(fX, fY, fW, fH);
    }
  }
  void update() {
    imageMode(CENTER);
    noStroke();
    if (isSpecial) {
      specialDraw();
    } else {
      fill(cBall);
      ellipse(fX, fY, fW, fH);
    }
  }
  public void sideCheck() {
    if (ball.fX == width - nRad) {
      ball.fDx = -7;
      nScore += 1;
    }
    if (ball.fX == nRad) {
      ball.fDx = 7;
      nScore += 1;
    }
    if (ball.fY <= 100) {
      if (ball.sImg == "bomb.png") {
        soundexplosion.play();
        soundexplosion.rewind();
        ball.fDx = 0;
        ball.fDy = -0.95;
        nCount += 1;
        if (nCount == 10) {
          img = explosion.getImage();
          nCount = 0;
          nEndAnimation++;
        }
        image(img, ball.fX, ball.fY);
        if (nEndAnimation == 3) {
          nMode = 4;
          nEndAnimation = 0;
        }
      } else {
        soundDeath.play();
        soundDeath.rewind();
        nMode = 4;
      }
    }
    if (ball.fY >= 800) {
      soundDeath.play();
      soundDeath.rewind();
      if (ball.sImg == "bomb.png") {
        soundexplosion.play();
        soundexplosion.rewind();
        ball.fDx = 0;
        ball.fDy = -0.95;
        nCount += 1;
        if (nCount == 10) {
          img = explosion.getImage();
          nCount = 0;
          nEndAnimation++;
        }
        image(img, ball.fX, ball.fY);
        if (nEndAnimation == 3) {
          nMode = 4;
          nEndAnimation = 0;
        }
      } else {
        nMode = 4;
      }
    }
  }

  boolean hitSide() {
    if (ball.fX == width - nRad) {
      return true;
    } else if (ball.fX == nRad) {
      return true;
    } else {
      return false;
    }
  }
  void specialDraw() {
    switch(nSpecial) {
    case 1:
      image(imgBall, fX, fY);
      break;
    case 2:
      noFill();
      stroke(50);
      ellipse(fX, fY, fW, fH);
      noStroke();
    }
  }
  void setImage() {
    imgBall = loadImage(sImg);
  }
  void reset() {
    fX = width/2;
    fY = height/2;
    fDy = 0;
    fDx = 8;
  }
}