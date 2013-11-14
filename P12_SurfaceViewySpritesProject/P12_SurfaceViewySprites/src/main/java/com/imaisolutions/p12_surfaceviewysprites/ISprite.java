package com.imaisolutions.p12_surfaceviewysprites;

import android.graphics.Canvas;

/**
 * Created by jesus_000 on 13/11/13.
 */
public interface ISprite {
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public void update() throws Throwable;
    public void onDraw(Canvas canvas);
    public boolean isCollision(ISprite sprite);

    public boolean isDead();
    public void kill();
}
