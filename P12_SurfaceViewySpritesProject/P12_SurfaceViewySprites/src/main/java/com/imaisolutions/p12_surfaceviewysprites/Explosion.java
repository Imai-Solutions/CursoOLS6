package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


public class Explosion extends Sprite {
    public Explosion(GameView gameView,int x,int y) {
        super(gameView,5,5, BitmapFactory.decodeResource(gameView.getResources(), R.drawable.explosion));
        this.x=x;
        this.y=y;
        direccion=0;
        contador=0;
    }

    @Override
    public void update(){
        if (contador<(BMP_ROWS*BMP_COLUMNS)){
            Log.d("EXPLOSION","PINTANDO EXPLOSION [" + contador + "]");
            direccion=contador/BMP_ROWS;
            super.update();
        }else {
            gameView.quitarExplosion(this);
            kill();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!isDead()){
              super.onDraw(canvas);
         }
    }
}
