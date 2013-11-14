package com.imaisolutions.p12_surfaceviewysprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private GameState e;
    private int x = 0;

    public GameView(Context context) {
        super(context);

        gameLoopThread = new GameLoopThread(this);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    public void quitarExplosion(ISprite explosion){
        e.explosiones.remove(explosion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        if (e!=null){
            // Representamos el estado del juego.
            e.robot.onDraw(canvas);
            e.enemigo.onDraw(canvas);
            for(ISprite explosion : e.explosiones)
            {
               explosion.onDraw(canvas);
            }
        }
    }

    public void onDraw(Canvas c, GameState e){
        this.e=e;
        onDraw(c);
    }
}
