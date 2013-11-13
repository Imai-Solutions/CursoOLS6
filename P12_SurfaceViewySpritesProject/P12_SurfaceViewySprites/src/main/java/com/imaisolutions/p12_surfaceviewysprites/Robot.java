package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Robot {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;

    int direccion; //0 de abajo , 1 izquierda , 2 derecha , 3 arriba

    public Robot(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        direccion=2;
    }

    final int FIJO=20;
    int contador=0;

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
            case 1:
                if ((x -xSpeed) > 0) {
                    x = x - xSpeed;
                }
                break;
            case 2:
                if ((x + xSpeed+width) < gameView.getWidth()) {
                    x = x + xSpeed;
                }
                break;
            case 3:
                if ((y - xSpeed) > 0) {
                    y = y - xSpeed;
                }
                break;
            case 0:
                if ((y + xSpeed +height) < gameView.getHeight()){
                    y=y + xSpeed;
                }
                break;
        }

        currentFrame = ++currentFrame % BMP_COLUMNS;
        contador++;
    }

    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = direccion * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}