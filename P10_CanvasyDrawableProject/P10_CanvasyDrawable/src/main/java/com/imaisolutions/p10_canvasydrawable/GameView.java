package com.imaisolutions.p10_canvasydrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View {

    private int _x;
    private int _y;
    private Bitmap bmp;
    private Path path;

    public GameView(Context context) {
        super(context);
        BitmapFactory BitmapFactory;
        bmp = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        path=new Path();
        _x=40;
        _y=40;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                // nothing to do
                break;
            default:
                return false;
        }
        // Anula vista y prepara llamada a OnDraw.
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Configuración de brochas.
        Paint p=new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        p.setTypeface(Typeface.SERIF);
        p.setTextSize(30);
        p.setAntiAlias(true);
        p.setStrokeWidth(6f);
        p.setStrokeJoin(Paint.Join.ROUND);


        Paint ps=new Paint();
        ps.setStyle(Paint.Style.FILL);
        ps.setColor(Color.GREEN);

        for (int i=0;i<300; i=i+10)
        {
            int radio=new Random().nextInt(200);
            Paint z=new Paint(Color.BLUE);
            z.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(300+i, 300+i, radio, z);
        }

        canvas.drawPath(path,p);

        canvas.drawCircle(50,50,40,ps);

        canvas.drawBitmap(bmp, 10, 10, null);

        canvas.drawText("HOLA",100,100,p);

        canvas.drawLine(200,200,0,400,p);

    }
}
