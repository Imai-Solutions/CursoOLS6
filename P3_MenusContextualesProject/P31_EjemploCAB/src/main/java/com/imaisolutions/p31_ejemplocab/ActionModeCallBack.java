package com.imaisolutions.p31_ejemplocab;

import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.imaisolutions.p3_menuscontextuales.R;

/**
 * Created by jesus_000 on 11/11/13.
 */
public class ActionModeCallBack implements ActionMode.Callback {

    ListAdapter mElementos;

    public ActionModeCallBack(ArrayAdapter<String> elementos){
        mElementos=elementos;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // inflate contextual menu
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
                // retrieve selected items and delete them out
                SparseBooleanArray selected = mElementos.getSelectedIds();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Laptop selectedItem = laptopListAdapter
                                .getItem(selected.keyAt(i));
                        laptopListAdapter.remove(selectedItem);
                    }
                }
                mode.finish(); // Action picked, so close the CAB
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // remove selection
        laptopListAdapter.removeSelection();
        mActionMode = null;
    }
}