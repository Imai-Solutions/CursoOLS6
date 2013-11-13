package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by jsc on 13/11/13.
 */
public class GameLogic {
    GameState e;
    GameView v;
    private Bitmap bmp;

    public GameLogic(GameView V,GameState E){
        e=E;
        this.v=V;
        inicializar();
    }

    public void inicializar(){
        bmp = BitmapFactory.decodeResource(v.getResources(), R.drawable.robot);
        e.robot=new Robot(v,bmp);
    }

    public void actualizar(){
        e.robot.update();
    }
}
