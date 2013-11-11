package com.imaisolutions.p31_ejemplocab1;

import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by jesus_000 on 11/11/13.
 */
public class ActionModeCallBack implements ActionMode.Callback {

    private AdaptadorSistemasOperativos mAdaptadorSO;
    private ActionMode mModo;

    public ActionModeCallBack(AdaptadorSistemasOperativos adaptador, ActionMode modo){
        mAdaptadorSO =adaptador;
        mModo=modo;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Menu contextual
        mode.getMenuInflater().inflate(R.menu.menucontextual, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                // recogemos los elementos seleccionados y los borramos.
                SparseBooleanArray selected = mAdaptadorSO.getSelectedIds();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        String selectedItem = mAdaptadorSO.getItem(selected.keyAt(i));
                        mAdaptadorSO.remove(selectedItem);
                    }
                }
                mode.finish(); // Acción realizada, cerramos la CAB
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // quitamos la selección.
        mAdaptadorSO.quitarSeleccion();
        mModo= null;
    }
}