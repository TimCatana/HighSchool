//sound code //<>//
import ddf.minim.*;
Minim minim;
AudioPlayer soundexplosion, soundsadviolin, soundbob, soundShop, soundDeath, soundJump, soundCoin, soundAmeer;
String sText = "code", sInput = "Enter The Code";
PFont font, Origional;
PImage img, imgCircle;
//PFont Bold, Original;
String[] info;
String [] arHighScore;
String sScore;
Animation explosion;
Button btnBack;
ButtonImg btnMute, btnBackToHomeScreen, btnShop, btnSecret, btnInfo, btnFreeCoins, imgspacebar, btnBackArrow, btnInstagram, btnCoinsOn;
Ball ball;
Shop shop;
Spike s;
Coin coin, GameOverCoin;
int nRad, nrunAnimation = 0, nFadeColour = 250, nAnimDY, nControlAnimation, nSpawnCoin = 0, ncoinvalue = 1; //For "+1" fade animation
int nMode = 1, nScore, nStart, nHighScore = 0; //nMode runs the different screens
int nLoc;
int nFreeCoins;
int nOpac;
int nCoin = 0;
int nCount;
float fLoc;
boolean isLeft, coinOn, isMuted = false;
color cBack = #E0DCC5, cSpike = #A59E9E;
ArrayList<Spike> alSpikes= new ArrayList<Spike>();
ArrayList<Integer> alLocs= new ArrayList<Integer>();

void setup() {
  font = createFont("Sans Serif", 40);    
  Origional = createFont("Lucida Sans", 12);  
  textFont(font, 10);
  frame.requestFocus();
  smooth();
  frameRate(60);
  size(600, 900);
  minim = new Minim(this);
  soundShop = minim.loadFile("shop.mp3");
  soundDeath = minim.loadFile("death.mp3");
  soundJump = minim.loadFile("jump.wav");
  soundCoin = minim.loadFile("coin.mp3");
  soundAmeer = minim.loadFile("ameer.mp3");  
  soundbob = minim.loadFile("bob.mp3");
  soundsadviolin = minim.loadFile("sadviolin.mp3");
  soundexplosion = minim.loadFile("bombexploding.mp3");
  coin = new Coin("coin.png", 100, height / 2, 50, 50);
  GameOverCoin = new Coin("coin.png", 100, height / 2, 70, 70);
  btnBack = new Button(15, 20, 200, 70);
  btnBackToHomeScreen = new ButtonImg("home.png", 120, 404, 100, 100);
  btnShop = new ButtonImg("bitcoin.png", 30, 315, 65, 65);
  btnInfo = new ButtonImg("information icon.png", 63, 390, 60, 60);
  btnSecret =  new ButtonImg("Secret.png", 475, 387, 60, 60);
  btnFreeCoins = new ButtonImg("moneybag.png", 490, 305, 65, 65);
  btnInstagram = new ButtonImg("instagram.png", 36, 460, 65, 65);
  btnCoinsOn = new ButtonImg("moneybag.png", 500, 322, 65, 65);
  imgspacebar = new ButtonImg("spacebar.png", 45, 700, 200, 75);
  btnBackArrow = new ButtonImg("backarrow.png", 1, 53, 60, 60);
  btnMute = new ButtonImg("soundicon.png", 500, 463, 60, 60);
  imgCircle = loadImage("nocoinscircle.png");

  info = loadStrings("info.txt");
  arHighScore = loadStrings("score.txt");
  nRad = 32;
  ball = new Ball(width/2, height/2, nRad*2, nRad*2);
  shop = new Shop();
  ball.fDx = 7;
  nScore = 0;
  nStart = 1;
  coinOn = true;
  explosion = new Animation();
  img = explosion.getImage();
}

void draw() {
  nHighScore = int(arHighScore[0]);
  println(arHighScore[0]);
  if (nMode == 1) {
    textFont(Origional);
    gameBackground();
    ellipse(width/2, height/2, 300, 300);
    btnShop.update();
    btnInfo.update();
    btnInstagram.update();
    btnFreeCoins.update();
    btnMute.update();
    if (nFreeCoins == 23) {
      nCoin = 999999;
    }
    if (isMuted) {
      soundShop.mute();
      soundDeath.mute();
      soundJump.mute();
      soundCoin.mute();
      soundAmeer.mute();  
      soundbob.mute();
      soundsadviolin.mute();
      soundexplosion.mute();
      image(imgCircle, 488, 453, 85, 85);
    }
    else{
      soundShop.unmute();
      soundDeath.unmute();
      soundJump.unmute();
      soundCoin.unmute();
      soundAmeer.unmute();  
      soundbob.unmute();
      soundsadviolin.unmute();
      soundexplosion.unmute();
    }
    btnSecret.update();
    fill(cSpike);
    textSize(100);
    text("Spikes", width/2 - 160, 150); 
    textSize(50);
    text("Press Space To Play!", 62, 780);
    ball.update();
  }

  if (nMode == 3) {
    textSize(100);
    drawRoof(true);
    fill(#FAFCF7);
    ellipse(width/2, height/2, 300, 300);
    drawScore();
    ball.update(ball.fDx, ball.fDy);
    ball.sideCheck();

    if (ball.hitSide() || nStart == 0) {
      if (coinOn == true) {
        nSpawnCoin = 1;
      }
      nStart =1;

      alSpikes.clear();
      alLocs.clear();
      if (nScore <5) {
        randomSpikes(3);
      } else if (nScore < 15) {
        randomSpikes(4);
      } else if (nScore < 20) {
        randomSpikes(5);
      } else if (nScore < 30) {
        randomSpikes(6);
      } else if (nScore < 50) {
        randomSpikes(7);
      } else {
        randomSpikes(7);
      }
    }

    if (nSpawnCoin == 1) {
      coin.update(coin.fX, coin.fY);
    }

    for ( int i =0; i<alSpikes.size(); i++) {
      Spike s = (Spike) alSpikes.get(i);
      s.update();
    }
    coinAnimation();

    if (nScore == 0) {
      cBack = #E0DCC5;
      cSpike = #A59E9E;
    }
    if (nScore == 5) {
      cBack = #E0E3DA;
    }
    if (nScore == 10) {
      cBack = #DDEABD;
    }
    if (nScore == 15) {
      cBack = #E2C3F7;
    }
    if (nScore == 20) {
      cBack = #BDE5FA;
    }
    if (nScore == 25) {
      cBack = #A59E9E;
      cSpike = #E0DCC5;
    }
    if (nScore == 30) {
      cBack = #2D9AAD;
    }
    if (nScore == 35) {
      cBack = #292CC1;
    }
    if (nScore == 40) {
      cBack = #5C08FC;
    }
    if (nScore == 45) {
      cBack = #5C08FC;
    }
    if (nScore == 50) {
      cBack = #5C08FC;
    }
  }
  if (nMode == 4) {
    if (nScore > nHighScore) {
      arHighScore[0] = str(nScore);
    }
    gameBackground();
    ellipse(width/2, height/2, 300, 300);
    textSize(100);
    nOpac += 10;
    fill(ball.cBall, nOpac);
    rect(100, 250, 400, 150, 20);
    fill(#ffffff);
    rectMode(CORNERS);
    textSize(100);
    if (nScore > -1 && nScore < 10) {
      text(nScore, width/2 - 30, 340);
    }
    if (nScore < 100 && nScore > 9) {
      text(nScore, width/2 - 60, 340);
    }
    if (nScore > 99 && nScore < 1000) {
      text(nScore, width/2 - 90, 340);
    }

    textSize(44);
    text("POINTS", width /2 - 77, 380);
    fill(ball.cBall, nOpac);
    rectMode(CORNER);
    rect(100, 410, 400, 90, 20);
    rect(100, 510, 400, 140, 20);
    textSize(80);
    fill(#ffffff, nOpac);
    textSize(40);
    fill(#A59E9E, nOpac);
    text("Press Space To Play Again", 50, 700);

    fill(#FFFFFF);
    textSize(60);
    text("High Score", 145, 560);
    textSize(85);
    if (nHighScore > -1 && nHighScore < 10) {
      text(nHighScore, width/2 - 30, 640);
    }
    if (nHighScore < 100 && nHighScore > 9) {
      text(nHighScore, width/2 - 60, 640);
    }
    if (nHighScore > 99 && nHighScore < 1000) {
      text(nHighScore, width/2 - 90, 640);
    }
    GameOverCoin.update(240, 754);
    fill(#ff9900);
    textSize(70);
    if (nFreeCoins < 23) {
      text(nCoin, 295, 780);
    }
    if (nFreeCoins > 23) {
      text(nCoin, 295, 780);
    }
    if (nFreeCoins == 23) {
      text("∞", 290, 775);
    }
  }

  if (nMode == 5) {
    shop.run();

    soundShop.play();
    soundbob.pause();
    soundAmeer.pause();
    soundsadviolin.pause();
    btnBackArrow.update();
    fill(#050205);
    text("Use Arrow Keys To Scroll", 0, 887);
  } else {
    soundShop.pause();
    soundShop.rewind();
  }

  if (nMode == 6) {
    gameBackground();
    fill(#43423b);
    textSize(70);
    textAlign(LEFT);
    text("How To Play", width/2 - 200, 130);
    for (int i=0; i < info.length; i++) {
      textSize(25);
      textAlign(CENTER);
      text(info[i], 2, 160, 600, 700);
    }
    textAlign(LEFT);
    textSize(75);
    text("Controls", width/2 - 160, 650);
    textSize(100);
    imgspacebar.update();
    textSize(100);
    text("=", 275, 765);
    textSize(60);
    text("Jump", 390, 750);
    btnBackArrow.update();
  }

  if (nMode == 7) {
    background(#282929);
    drawRoof(false);
    textSize(30);
    fill(#178E10);
    textAlign(LEFT);
    text(sInput, width/2 - 100, height/2 - 30, width, height);
    text(sText, width/2 - 27, height/2 + 5, width, height);
    btnBackArrow.update();
  }
}

void resetGame() {
  ncoinvalue = 1;
  nSpawnCoin = 0;
  coin.fX = 100;
  coin.isLeft = true;
  nrunAnimation = 0;
  ball.reset();
  isLeft = false;
  alSpikes.clear();
  nScore = 0;
}
void drawRoof(boolean bBack) {
  fill(cSpike);
  if (bBack) {
    background(cBack);
  }
  //draw ceiling
  rect(0, 0, width, 20); 
  noStroke();
  triangle(25, 20, 60, 70, 95, 20);
  triangle(105, 20, 140, 70, 175, 20);
  triangle(185, 20, 220, 70, 255, 20);
  triangle(265, 20, 300, 70, 335, 20);
  triangle(345, 20, 380, 70, 415, 20);
  triangle(425, 20, 460, 70, 495, 20);
  triangle(505, 20, 540, 70, 575, 20);
  //draw floor with spikes
  rect(0, 880, 600, 900); 
  noStroke();
  triangle(25, height - 20, 60, height - 70, 95, height - 20);
  triangle(105, height - 20, 140, height - 70, 175, height - 20);
  triangle(185, height - 20, 220, height - 70, 255, height - 20);
  triangle(265, height - 20, 300, height - 70, 335, height - 20);
  triangle(345, height - 20, 380, height - 70, 415, height - 20);
  triangle(425, height - 20, 460, height - 70, 495, height - 20);
  triangle(505, height - 20, 540, height - 70, 575, height - 20);
}
void drawScore() { 
  fill(cBack);
  if (nScore <= 9) {
    text(nScore, 270, 480);
  }
  if (nScore >= 10 && nScore <= 99) {
    text(nScore, 238, 480);
  }
  if (nScore >= 100) {
    text(nScore, 203, 480);
  }
}

void mousePressed() {
  if (nMode == 1) {
    if (btnShop.isClicked()) {
      nMode = 5;
      if (nFreeCoins != 23) {
        nFreeCoins = 0;
      }
    }
    if (btnInfo.isClicked()) {
      nMode = 6;
      if (nFreeCoins != 23) {
        nFreeCoins = 0;
      }
    }
    if (btnInstagram.isClicked()) {
      link("https://www.instagram.com/ameericle_/");
    }
    if (btnFreeCoins.isClicked()) {
      nFreeCoins++;
    }
    if (btnSecret.isClicked()) {
      nMode = 7;
    }
    if (btnMute.isClicked()) {
      isMuted = !isMuted;
    }
  }
  if (nMode == 5) {
    if (btnBackArrow.isClicked()) {
      nMode = 1;
    }
  }
  if (nMode == 6) {
    if (btnBackArrow.isClicked()) {
      nMode = 1;
    }
  }
  if (nMode == 7) {
    if (btnBackArrow.isClicked()) {
      nMode = 1;
      sText = ("code");
    }
  }
}

void keyPressed() {
  if (nMode == 1) {
    if (key == ' ') {
      resetGame();
      nMode = 3;
      if (nFreeCoins != 23) {
        nFreeCoins = 0;
      }
    }
  }
  if (nMode == 3) {
    ball.fDy = -13.5;
  }
  if (nMode == 2) {
    if (key == ' ') {

      nMode = 3;
      ball.fDx = 5;
    }
  }
  if (nMode == 4) {
    if (key == ' ') {
      saveStrings("score.txt", arHighScore);
      arHighScore = loadStrings("score.txt");
      textSize(100);
      nScore = 0;
      ball.fX = width / 2;
      ball.fY = height / 2;
      nOpac = 0;
      nMode = 1;
    }
  }
  if (nMode == 7) {
    if (keyCode == BACKSPACE) {
      if (sText.length() > 0) {
        sText = sText.substring(0, sText.length()-1);
      }
    } else if (keyCode == DELETE) {
      sText = "";
    } else if (keyCode != SHIFT) {
      if (sText.trim().equals("code") == true) {
        sText = "";
      }
      sText = sText + key;
    }
  }

  if (keyCode == ENTER) {
    if (sText.trim().equals("ameericle") == true) {
      ball.cBall = #a59e9e;

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "Ameer.png";
      ball.setImage();
      soundAmeer.play();
      soundbob.pause();
      soundsadviolin.pause();
      soundbob.rewind();
      soundsadviolin.rewind();
      nMode = 1;
    }

    if (sText.trim().equals("bomb") == true) {
      ball.cBall = #a59e9e;

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "bomb.png";
      ball.setImage();
      soundbob.pause();
      soundAmeer.pause();
      soundsadviolin.pause();
      soundbob.rewind();
      soundAmeer.rewind();
      soundsadviolin.rewind();
      nMode = 1;
    }

    if (sText.trim().equals("cookie") == true) {
      ball.cBall = #a59e9e;

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "nervous.png";
      ball.setImage();
      soundbob.pause();
      soundAmeer.pause();
      soundsadviolin.pause();
      soundbob.rewind();
      soundAmeer.rewind();
      soundsadviolin.rewind();
      nMode = 1;
    }

    if (sText.trim().equals("copperplane") == true) {
      ball.cBall = #a59e9e;

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "Constructor.png";
      ball.setImage();
      soundbob.play();
      soundAmeer.pause();
      soundsadviolin.pause();
      soundAmeer.rewind();
      soundsadviolin.rewind();
      nMode = 1;
    }

    if (sText.trim().equals("ballislife") == true) {

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "baseball.png";
      ball.setImage();
      soundbob.pause();
      soundAmeer.pause();
      soundsadviolin.pause();
      soundbob.rewind();
      soundAmeer.rewind();
      soundsadviolin.rewind();
      nMode = 1;
    }

    if (sText.trim().equals("SWAHILI") == true) {

      ball.isSpecial = true;
      ball.nSpecial = 1;
      ball.sImg = "Powerup.png";
      ball.setImage();
      soundbob.pause();
      soundAmeer.pause();
      soundsadviolin.play();
      soundbob.rewind();
      soundAmeer.rewind();
      nMode = 1;
    }

    sText = "";
  }
}




void randomSpikes(int nAmnt) {
  isLeft = !isLeft;
  for (int i = 0; i<nAmnt; i++) {
    fLoc = random(11);
    nLoc= int(fLoc); 
    while (alLocs.contains(nLoc)) {
      fLoc = random(11); 
      nLoc= int(fLoc);
    }
    nLoc= int(fLoc); 
    alLocs.add(nLoc);
    s = new Spike(nLoc, isLeft);
    alSpikes.add(s);
  }
}

void coinAnimation() {
  if (nrunAnimation == 1) {
    textSize(60);
    fill(#333333, nFadeColour);
    text("+" + ncoinvalue, ball.fX - 40, ball.fY - nAnimDY);
    if (nControlAnimation == 1) {
      nAnimDY += 4;
    }
    nFadeColour -= 10;
    if (nFadeColour == 0) {
      nControlAnimation = 0;
      nAnimDY = 0;
      nFadeColour = 250;
      nrunAnimation = 0;
    }
  }
}

void gameBackground() {
  background(cBack);
  fill(#FAFCF7);
  drawRoof(true);
  textSize(100);
  fill(#FAFCF7);
}