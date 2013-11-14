package com.imaisolutions.p12_surfaceviewysprites;

/**
 * Created by jsc on 13/11/13.
 */
public class GameLogic {
    GameState e;
    GameView v;

    public GameLogic(GameView V,GameState E){
        e=E;
        this.v=V;
        inicializar();
    }

    public void inicializar(){
        e.robot=new Robot(v);
        e.enemigo=new Enemigo(v);
    }

    int contadorUltimaExplosion=51;
    final int iteracionesEspera=50;

    public void actualizar(){
        e.robot.update();
        e.enemigo.update();
        for(ISprite exp:e.explosiones)
            ((Explosion)exp).update();

        if (e.robot.isCollision(e.enemigo)){
            if (contadorUltimaExplosion>iteracionesEspera)
            {
                e.explosiones.add(new Explosion(v,e.robot.getX(),e.robot.getY()));
                contadorUltimaExplosion=0;
            }
            else contadorUltimaExplosion++;
        }
    }

}
