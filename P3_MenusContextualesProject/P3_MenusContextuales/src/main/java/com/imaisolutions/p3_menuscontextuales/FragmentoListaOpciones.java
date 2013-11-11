package com.imaisolutions.p3_menuscontextuales;


import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;


/**
 * Created by jesus_000 on 10/11/13.
 */
public class FragmentoListaOpciones extends ListFragment{

    String[] listavalores = new String[] { "Android", "iPhone", "WindowsMobile",
          "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
          "Linux", "OS/2" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listavalores);
        setListAdapter(adapter);
        this.registerForContextMenu(getListView());
    }

    /** Cargar el menú contextual */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menucontextual , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_edit:
                Toast.makeText(this.getActivity(),
                        "Editar : " + listavalores[info.position], Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this.getActivity(), 
                        "Borrar : " + listavalores[info.position]  , Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this.getActivity(),
                        "Compartir: " + listavalores[info.position]  , Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

}