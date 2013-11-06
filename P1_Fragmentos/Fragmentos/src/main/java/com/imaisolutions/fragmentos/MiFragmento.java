package com.imaisolutions.fragmentos;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mifragmento, container, false);

        // Establecemos el evento al botón.
        btn = (Button) v.findViewById(R.id.btnPruebaFragmento);
        lblMensaje = (TextView) v.findViewById(R.id.lblPrueba);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblMensaje.setText(getResources().getText(R.string.mensaje_fragmento) + new Date().toString());
            }
        });

        return v;
    }

}