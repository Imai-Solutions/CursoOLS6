package com.imaisolutions.fragmentos;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jsc on 24/10/13.
 */
public class MiFragmento extends Fragment  {
    Button btn;
    TextView lblMensaje;

    private static final  String _keyMSJ="KEYMSJ";
    public  static final  String _paramNUM="KEYNUM";
    private String msj;
    private int numFr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msj = savedInstanceState != null ? savedInstanceState.getString(_keyMSJ):"";
        numFr = getArguments().getInt(_paramNUM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mifragmento, container, false);

        // Establecemos el evento al botón.
        btn = (Button) v.findViewById(R.id.btnPruebaFragmento);
        lblMensaje = (TextView) v.findViewById(R.id.lblPrueba);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msj=getResources().getText(R.string.mensaje_fragmento) + " FR#" +
                            numFr +":" +
                            new Date().toString();

                lblMensaje.setText(msj);
            }
        });

        lblMensaje.setText("FR#" + numFr + ":" +  msj);
       return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(_keyMSJ,msj);

    }


}