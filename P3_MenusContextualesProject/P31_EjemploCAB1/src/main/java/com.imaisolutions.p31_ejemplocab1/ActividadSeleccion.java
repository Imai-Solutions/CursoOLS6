package com.imaisolutions.p31_ejemplocab1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ActividadSeleccion extends ActionBarActivity
       implements android.widget.AdapterView.OnItemClickListener {

    ListView soListView;
    AdaptadorSistemasOperativos adaptadorSO;
    ActionMode mActionMode;

    String[] listavalores = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seleccion);
        soListView = (ListView) findViewById(R.id.listaValores);

        adaptadorSO = 
                new AdaptadorSistemasOperativos
                    (this, R.layout.listitem, new ArrayList<String>(Arrays.asList(listavalores)));
        
        soListView.setAdapter(adaptadorSO);
        soListView.setOnItemClickListener(this);
        soListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                onListItemSelect(position);
                return true;
            }
        });

    }

    private void onListItemSelect(int position) {
        adaptadorSO.anadirSeleccion(position);
        boolean hasCheckedItems = adaptadorSO.getNumeroElementosSeleccionados() > 0;

        if (hasCheckedItems && mActionMode == null)
        // Si seleccionamos un elemento , iniciamos el ActionMode
        {
            ActionModeCallBack callbacks = new ActionModeCallBack(adaptadorSO, mActionMode);
            mActionMode = startSupportActionMode(callbacks);
        }
        else if (!hasCheckedItems && mActionMode != null)
        {
        // Si no hay elementos seleccionamos terminamos el modo de trabajo
            mActionMode.finish();
        }
        if (mActionMode != null)
            mActionMode.setTitle("Seleccionados: " + String.valueOf(adaptadorSO.getNumeroElementosSeleccionados()));
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mActionMode == null) {
            /*no items selected, so perform item click actions
             * like moving to next activity */
            Toast toast = Toast.makeText(getApplicationContext(), "Elemento "
                    + (i + 1) + ": " + adaptadorSO.getItem(i),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        } else
            // add or remove selection for current list item
            onListItemSelect(i);
    }

}
