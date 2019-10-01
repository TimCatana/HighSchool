class Spike {
  PVector v1, v2;
  float fX, fY;
  boolean isLeft;
  float fLoc;
  int nEndAnimation;
  Spike(int _fLoc, boolean _isLeft) {
    isLeft = _isLeft;
    fLoc = _fLoc;
    fY = 75;
    fY += fLoc*75;
    if (isLeft) {
      fX = 0;
      v1 = new PVector(-35, fY - 39);
      v2 = new PVector(-35, fY + 39);
    }
    if (!isLeft) {
      fX = 600;
      v1 = new PVector(635, fY - 39);
      v2 = new PVector(635, fY + 39);
    }
  }
  public void update() {
    if (ballLine(nRad*2, v1.x, v1.y, fX, fY) == true) {
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
    if (ballLine(nRad*2, v2.x, v2.y, fX, fY)== true) {
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
    if (isLeft) {
      if (fX < 35) {
        v1.x += 2;
        v2.x += 2;
        fX += 2;
      }
    } else {
      if (fX > 565) {
        v1.x -= 2;
        v2.x -= 2;
        fX -= 2;
      }
    }
    fill(cSpike);
    triangle(v1.x, v1.y, fX, fY, v2.x, v2.y);
  }
  float fDistX, fDistY, fLineLength, fRad, fClosestX, fClosestY;
  float fDistToPointX, fDistToPointY, fDistToPoint;
  boolean ballLine(int fD, float fLineX1, float fLineY1, float fLineX2, float fLineY2) {
    // first get the length of the line using the Pythagorean theorem
    fDistX = fLineX1-fLineX2;
    fDistY = fLineY1-fLineY2;
    fLineLength = sqrt((fDistX*fDistX) + (fDistY*fDistY));
    // then solve for r
    fRad = (((ball.fX-fLineX1)*(fLineX2-fLineX1))+((ball.fY-fLineY1)*(fLineY2-fLineY1)))/pow(fLineLength, 2);

    // get x,y points of the fClosest point
    fClosestX = fLineX1 + fRad*(fLineX2-fLineX1);
    fClosestY = fLineY1 + fRad*(fLineY2-fLineY1);    //if (isLeft == true) {

    //Constrain the cloest coordinates so it only stays on the line segment and not infinite  
    if (isLeft == true) {
      fClosestX = constrain(fClosestX, fLineX1, fLineX2);
    } else {
      fClosestX = constrain(fClosestX, fLineX2, fLineX1);
    }
    if (fLineY1 <= fLineY2) {
      fClosestY = constrain(fClosestY, fLineY1, fLineY2);
    } else {
      fClosestY = constrain(fClosestY, fLineY2, fLineY1  );
    }
    // to get the length of the line, use the Pythagorean theorem again
    fDistToPointX = fClosestX - ball.fX;
    fDistToPointY = fClosestY - ball.fY;
    fDistToPoint = sqrt(pow(fDistToPointX, 2) + pow(fDistToPointY, 2));

    // for explanation purposes, fDraw a line to the ball fRadom the fClosest point
    //strokeWeight(1);
    //stroke(255, 0, 0);
    //line(fClosestX, fClosestY, fBallX, fBallY);
    //strokeWeight(3);

    // if that fDistance is less than the rafDius of the ball: collision
    if (fDistToPoint <= fD/2) {
      return true;
    } else {
      return false;
    }
  }
}