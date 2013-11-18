package com.imaisolutions.p9_ejemplodraganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private static final String IMAGEVIEW_TAG = "Android Logo";
    private ImageView ima;
    private android.widget.FrameLayout.LayoutParams layoutParams;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ima = (ImageView)findViewById(R.id.imageView);
        // Sets the tag
        ima.setTag(IMAGEVIEW_TAG);

        ima.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(),
                        mimeTypes, item);

                // Iniciamos el proceso de Drag&Drop
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(ima);

                // Starts the drag
                v.startDrag(dragData,  // Los datos a arrastrar
                        myShadow,  // El objeto a mover
                        null,
                        0
                );
                Toast.makeText(v.getContext(), "Empezamos", 2000).show();
                return true;
            }
        });

        // Establecemos el evento Drag & Drop
        ima.setOnDragListener( new View.OnDragListener(){
            @Override
            public boolean onDrag(View v,  DragEvent event){
                  switch(event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (FrameLayout.LayoutParams)
                                v.getLayoutParams();

                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED" + getXY(event));
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        // Entrada
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED" + getXY(event));

                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        //Salida
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED" + getXY(event));
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION  :

                             break;
                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED"  + getXY(event));

                        // Do nothing
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "ACTION_DROP event"  + getXY(event));
                        // Do nothing

                        break;
                    default: break;
                }
                return true;
            }
        });
    }

    private String getXY(DragEvent event)
    {
        return "Coords:"+  (int) event.getX() + "," + (int)  event.getY();
    }



}
