package com.imaisolutions.p11_surfaceview;

import android.graphics.Canvas;

/**
 * Created by jsc on 13/11/13.
 */
public class GameLoopThread extends Thread {
    static final long FPS = 30;
    static final int xspeed=10;

    private GameView view;
    private GameState e;
    private GameLogic gl;
    private boolean running = false;

    public GameLoopThread(GameView view) {
        this.view = view;
        e=new GameState(10);
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        // INICIALIZAMOS JUEGO
        gl=new GameLogic(e,view.getTope());

        // COMENZAMOS BUCLE
        while (running) {
            startTime = System.currentTimeMillis();

            // 1. RECIBIMOS ENTRADAS.

            // 2. ACTUALIZAMOS MUNDO
            gl.Desplaza(xspeed);

            // 3. DIBUJAMOS
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c,e);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }

            // PAUSA SEGÚN FPS
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}

        }
    }
}
