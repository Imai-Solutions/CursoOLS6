package com.imaisolutions.p11_surfaceview;

/**
 * Created by jsc on 13/11/13.
 */
public class GameLogic {
    GameState e;
    // restricciones;
    int tope;

    public GameLogic(GameState E, int limite){
        e=E;
        tope=limite;
    }

    public void Desplaza(int incremento){
        int x=e.getX();
        if (x < tope) {
            e.setX(x+incremento);
        }
    }
}
