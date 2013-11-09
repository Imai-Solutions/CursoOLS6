package com.imaisolutions.fragmentos.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.imaisolutions.fragmentos.FragmentoPruebaCicloVida;
import com.imaisolutions.fragmentos.MiFragmento;
import com.imaisolutions.fragmentos.R;

import java.util.Stack;

public class CargadorDinamicoFragmentos extends ActionBarActivity {
    LinearLayout lst;
    int nFragmentosTipoMiFragmento=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdf);
        lst=(LinearLayout)this.findViewById(R.id.container);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menucdf , menu);
        return true;
    }
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Stack<Fragment> pilaFragmentos=new Stack<Fragment>();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.nuevofragmento:
                //Añadimos un fragmento de forma dinámica.
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentoPruebaCicloVida fragment = new FragmentoPruebaCicloVida();

                FrameLayout  container = new FrameLayout(this);
                        container.setLayoutParams(
                            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                       ViewGroup.LayoutParams.WRAP_CONTENT));
                container.setId(View.generateViewId());
                lst.addView(container);
                fragmentTransaction.add(container.getId(), fragment,"FReloj");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                pilaFragmentos.push(fragment);
                break;

            case R.id.quitar:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                //Fragment f=fragmentManager.findFragmentByTag("FReloj"); Forma de localizar.
                if (!pilaFragmentos.empty())
                {
                   Fragment f= pilaFragmentos.peek();
                   if (f!=null) fragmentTransaction.remove(f);
                }
                fragmentTransaction.commit();
                if (!pilaFragmentos.empty()) pilaFragmentos.pop();

                break;

            case R.id.reemplazar:
                if (!pilaFragmentos.empty())
                {
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    MiFragmento f1=new MiFragmento();
                    // Parámetros del fragmento.
                    Bundle args = new Bundle();
                    args.putInt(MiFragmento._paramNUM, nFragmentosTipoMiFragmento++);
                    f1.setArguments(args);

                    lst=(LinearLayout)this.findViewById(R.id.container);

                    fragmentTransaction.setCustomAnimations(
                        R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in, R.anim.card_flip_left_out);

                    Fragment fr=pilaFragmentos.peek();
                    if(fr!=null)
                    {
                        fragmentTransaction.replace(((View)(fr.getView().getParent())).getId(),f1);
                        fragmentTransaction.addToBackStack(null);
                    }

                    fragmentTransaction.commit();
                    pilaFragmentos.push(f1);

                }
                break;

            case R.id.popBackStack:
                  getFragmentManager().popBackStack();
                  pilaFragmentos.pop();

                break;
            case R.id.lanzarpilafragmentos:
                Intent actividadPilaFragmentos=new Intent(this,PilaFragmentos.class);
                this.startActivity(actividadPilaFragmentos);

        }
        return super.onOptionsItemSelected(item);
    }



}
