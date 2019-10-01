class Animation {
  ArrayList<PImage> alExplosion = new ArrayList<PImage>(); // all of the images.
  PImage img, spriteSheet;
  int  W = 900, H= 900; // size of the image
  int i = 0, nX, nY;

  Animation() {
    spriteSheet = loadImage("bombexplosion.png"); // this is the sprite sheet.
    // since the spriteSheet is a 4x4 grid, I slice it up like this:
    W = spriteSheet.width/4;
    H = spriteSheet.height/4; 
    for (int x=0; x<4; x++) {
      for (int y=0; y<4; y++) {
        nX = x*W;
        nY = y*H;
        img = spriteSheet.get(nX, nY, W, H); // get the image of a single card from the sprite sheet
        alExplosion.add(img);
      }
    }
  }
  PImage getImage() {
    i++;
    if (i== alExplosion.size()) {
      i=0;
    }
    return alExplosion.get(i);
  }
}