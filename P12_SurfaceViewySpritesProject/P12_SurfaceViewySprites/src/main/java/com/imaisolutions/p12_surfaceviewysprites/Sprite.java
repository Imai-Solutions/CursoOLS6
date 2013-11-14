package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by jesus_000 on 13/11/13.
 */
public class Sprite implements ISprite{

    protected int x = 0;
    protected int y = 0;
    protected int xSpeed = 5;
    protected GameView gameView;
    protected Bitmap bmp;
    protected int currentFrame = 0;
    protected int width;
    protected int height;
    final int FIJO=20;
    int contador=0;
    int direccion=0;
    public boolean dead=false;

    int BMP_ROWS;
    int BMP_COLUMNS;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sprite(GameView gameView,int rows,int cols,Bitmap bmp){
        BMP_ROWS=rows;
        BMP_COLUMNS=cols;
        this.bmp=bmp;
        this.gameView = gameView;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    @Override
    public void update(){
        currentFrame = ++currentFrame % BMP_COLUMNS;
        contador++;
    }

    @Override
    public void onDraw(Canvas canvas){
        if (!isDead()){
            int srcX = currentFrame * width;
            int srcY = direccion * height;
            Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
            Rect dst = new Rect(x, y, x + width, y + height);
            canvas.drawBitmap(bmp, src, dst, null);
        }
    }

    @Override
    public boolean isCollision(ISprite sprite) {
        int x2=sprite.getX();
        int y2=sprite.getY();
        // Comprobar si las coordenadas están dentro del espacio del sprite.
        return x2 > x && x2 < x + width && y2 >= y && y2 < y + height;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void kill() {
        this.dead = true;
    }
}
