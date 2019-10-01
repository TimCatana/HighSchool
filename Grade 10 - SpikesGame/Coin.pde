
class Coin {
  public boolean isLeft;
  public float fX, fY, fDx, fDy;
  int fW, fH;
  String sFile;
  PImage img, img2;
  Coin( String _sFile, float _fX, float _fY, int  _fW, int _fH) {
    fX = _fX;
    fY = _fY;
    fW = _fW;
    fH = _fH;
    sFile = _sFile;
    img = loadImage(sFile);
    img2 = loadImage(sFile);
    img.resize(fW, fH);
    img2.resize(fW, fH);
  }

  public void update(float _fX, float _fY) {
    fX = _fX;
    fY = _fY;
    image(img, fX, fY);
    if (nMode == 3) {
      if (isHit()) {
        soundCoin.play();
        soundCoin.rewind();
        coin.isLeft = !coin.isLeft;
        coin.randBitcoin();
        nCoin += 1;
        nrunAnimation = 1;
        if (nScore > 24 && nScore < 50) {
          ncoinvalue = 2;
        }
        if (nScore > 49) {
          ncoinvalue = 3;
        }
        nControlAnimation = 1;
      }
    }
  }

  public void randBitcoin() {
    if (isLeft) {
      fX = 100;
    } else {
      fX = 500;
    }
    fY = random(107, 793);
    image(img, fX, fY);
  }
  boolean isHit() {
    float nH1, nW1, nH2, nW2, fX1, fY1, fX2, fY2;
    fX1 = ball.fX- nRad;
    fY1 = ball.fY- nRad; 
    fX2 = fX-24;
    fY2 = fY-24;
    nH1 = nRad*2;
    nW1 = nRad*2;
    nH2 = fW;
    nW2 = fH;
    if (
      ( ( (fX1 < fX2) && (fX1+nW1 > fX2) ) ||
      ( (fX1 > fX2) && (fX1 < fX2+nW2) ) )
      &&
      ( ( (fY1 < fY2) && (fY1+nH1 > fY2) ) ||
      ( (fY1 > fY2) && (fY1 < fY2+nH2) ) )
      )
      return (true) ;
    else
      return(false) ;
  }
}