package com.imaisolutions.p2_actionbar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.imaisolutions.p2_actionbar.actionbar.TabListener;
import com.imaisolutions.p2_actionbar.fragmentos.f1;
import com.imaisolutions.p2_actionbar.fragmentos.f2;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ESTABLECEMOS EL MODO DE NAVEGACIÓN
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)
        actionBar.setDisplayShowTitleEnabled(true);

        // AÑADIR LOS TABS
        Tab tab1 = actionBar.newTab()
                .setText(R.string.hello_world_f1)
                .setTabListener(
                         new TabListener<f1> (this, "FR1", f1.class)
                 );
        actionBar.addTab(tab1);

        Tab tab2 = actionBar.newTab()
                .setText(R.string.hello_world_f2)
                .setTabListener(
                        new TabListener<f2>
                                (this, "FR2", f2.class)
                );
        actionBar.addTab(tab2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);



        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,Task1_Activity.class);
            this.startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
