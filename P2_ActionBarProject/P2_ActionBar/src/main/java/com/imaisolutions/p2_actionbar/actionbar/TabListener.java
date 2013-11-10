package com.imaisolutions.p2_actionbar.actionbar;

import android.app.ActionBar;
import android.app.ActionBar.*;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by jesus_000 on 10/11/13.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    /** Constructor utilizado cada vez que se cree una pestaña.
     * @param activity  La actividad que aloja los fragmentos.
     * @param tag  El nombre de la pestaña
     * @param clz  La clase fragmento a instanciar.
     */
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }

    /* Callbacks de ActionBar.TabListener */
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // Preguntamos si el fragmento ha sido instanciado
        if (mFragment == null) {
            // Si no existe el fragmento se instancia y se añade a la actividad
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            // Si existe se adjunta.
            ft.attach(mFragment);
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // Quitamos el fragmento.
            ft.detach(mFragment);
        }
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // Ocurre si el usuario ha seleccionado la pestaña y vuelve a seleccionarla de nuevo.
        // Lo normal..no hacer nada.
    }
}
