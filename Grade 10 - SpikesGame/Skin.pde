//try using a single Fx and fY variable eg. rect: fX, fY, ball: fX - (fLength/2), fY - (fLength/2)
class Skin {
  float fRx, fRy, fLength, fBx, fBy, fDiam, fPrice;
  int nSpecial;
  color cBall, cRect;
  boolean isSpecial;
  String sImg;
  PImage imgBall;
  Skin(float _fRx, float _fRy, float _fLength, color _cBall, float _fDiam, float _fPrice, boolean _isSpecial, int _nSpecial, String _sImg) {
    fRx = _fRx;
    fRy = _fRy;
    fLength = _fLength;
    cBall = _cBall;
    fDiam = _fDiam;
    fPrice = _fPrice;
    isSpecial = _isSpecial;
    nSpecial = _nSpecial;
    sImg = _sImg;
    fBx = fRx +(fLength/2);
    fBy = fRy +(fLength/2);
    if (isSpecial) {
      imgBall = loadImage(sImg);
    }
    imageMode(CENTER);
  }
  void update() {
    fBx = fRx +(fLength/2);
    fBy = fRy +(fLength/2);
    fill(255);
    rect(fRx, fRy, fLength, fLength);
    if (isSpecial) {
      specialDraw();
    } else {
      fill(cBall);
      ellipse(fBx, fBy, fDiam, fDiam);
    }
    if (mousePressed) {
      if (isClicked()) {
        ball.cBall = cBall;
        if (isSpecial) {
          ball.isSpecial = isSpecial;
          ball.nSpecial = nSpecial;
          ball.sImg = sImg;
          ball.setImage();
        } else {
          ball.isSpecial = false;
        }
      }
    }
  }
  void specialDraw() {
    switch(nSpecial) {
    case 1:
      image(imgBall, fBx, fBy);
      break;
    case 2:
      noFill();
      stroke(50);
      ellipse(fBx, fBy, fDiam, fDiam);
      noStroke();
    }
  }

  float fXDist, fYDist, fDist;
  public boolean isClicked() { 
    fXDist = mouseX-fBx;                                   
    fYDist = mouseY-fBy;                                  
    fDist = sqrt((fXDist*fXDist) + (fYDist*fYDist)); 
    if (fDiam/2 > fDist) {
      return true;
    } else {           
      return false;
    }
  }
}