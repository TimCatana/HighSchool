class Button {
  public float fX, fY, fW, fH;
  Button(float _fX, float _fY, float _fW, float _fH) {
    fX = _fX;
    fY = _fY;
    fW = _fW;
    fH = _fH;
  }

  public void update() {
    imageMode(CORNER);
    fill(#A59E9E);
    rect(fX, fY, fW, fH);
  }


  public boolean isClicked() {

    if (mouseX >=fX && mouseX <= fX+fW && mouseY >=fY && mouseY<= fY+fH) {

      return true;
    } else {
      return false;
    }
  }
}