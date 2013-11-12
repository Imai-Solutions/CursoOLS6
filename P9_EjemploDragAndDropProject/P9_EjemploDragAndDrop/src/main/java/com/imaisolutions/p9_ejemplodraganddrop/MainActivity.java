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

                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                        // Do nothing
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "ACTION_DROP event");
                        // Do nothing

                        break;
                    default: break;
                }
                return true;
            }
        });
    }



}
