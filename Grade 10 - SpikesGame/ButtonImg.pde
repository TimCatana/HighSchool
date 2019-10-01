class ButtonImg {
  public float fX, fY;
  int nW, nH;
  String sFile;
  PImage img;
  ButtonImg(String _sFile, float _fX, float _fY, int _nW, int _nH) {
    fX = _fX;
    fY = _fY;
    nW = _nW;
    nH = _nH;
    sFile = _sFile;
    img = loadImage(sFile);
    img.resize(nW, nH);
  }

  public void update() {
    imageMode(CORNER);
    image(img, fX, fY);
    img.resize(nW, nH);
  }

  public void DifferentPlacement(int _fX, int _fY) {
    fX = _fX;
    fY = _fY;
    image(img, fX, fY);
    img.resize(nW, nH);
  }

  public boolean isClicked() {

    if (mouseX >=fX && mouseX <= fX+nW && mouseY >=fY && mouseY<= fY+nH) {

      return true;
    } else {
      return false;
    }
  }
}