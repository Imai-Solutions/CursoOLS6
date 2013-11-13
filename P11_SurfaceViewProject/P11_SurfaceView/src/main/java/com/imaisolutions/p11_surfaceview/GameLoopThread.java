package com.imaisolutions.p11_surfaceview;

import android.graphics.Canvas;

/**
 * Created by jsc on 13/11/13.
 */
public class GameLoopThread extends Thread {
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
        // INICIALIZAMOS JUEGO
        gl=new GameLogic(e,view.getTope());

        // COMENZAMOS BUCLE
        while (running) {
            // 1. RECIBIMOS ENTRADAS.

            // 2. ACTUALIZAMOS MUNDO
            gl.Desplaza(1);

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
        }
    }
}
