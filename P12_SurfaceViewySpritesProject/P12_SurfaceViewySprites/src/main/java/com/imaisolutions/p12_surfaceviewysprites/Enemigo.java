package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Enemigo extends Sprite {

    public Enemigo(GameView gameView) {
        super(gameView,4,3, BitmapFactory.decodeResource(gameView.getResources(), R.drawable.enemigo));
        direccion=1;
        x=gameView.getWidth()-width;
        y=gameView.getHeight()-height;
    }
    //0 de arriba , 1 izquierda , 2 derecha , 3 abajo

    @Override
    public void update() {
        if (contador>FIJO){
            Random rnd=new Random();
            direccion=rnd.nextInt(4);
            //reiniciamos contador
            contador=0;
            currentFrame=0;
        }

        switch (direccion)
        {
            case 2:
                if ((x -xSpeed) > 0) {
                    x = x - xSpeed;
                }
                break;
            case 1:
                if ((x + xSpeed+width) < gameView.getWidth()) {
                    x = x + xSpeed;
                }
                break;
            case 0:
                if ((y - xSpeed) > 0) {
                    y = y - xSpeed;
                }
                break;
            case 3:
                if ((y + xSpeed +height) < gameView.getHeight()){
                    y=y + xSpeed;
                }
                break;
        }

        super.update();
    }

    @Override
    public void onDraw(Canvas canvas) {
       super.onDraw(canvas);
    }
}