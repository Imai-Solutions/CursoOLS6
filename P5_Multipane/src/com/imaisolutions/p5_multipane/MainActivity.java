/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imaisolutions.p5_multipane;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity 
        implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);

        // Verificar el tipo de layout que ha dispuesto el MVP
        // Si es el layout-large existirá el contenedor para colocar el contenido del fragmento.
        if (findViewById(R.id.fragment_container) != null) {

            // Si se restaura de otro estado no es necesario cargar el fragmento de nuevo.
            if (savedInstanceState != null) {
                return;
            }

            HeadlinesFragment firstFragment = new HeadlinesFragment();

            // Si la actividad recibe algún parámentro es buena técnica pasarsela a los
            // fragmentos que puedan utilizarlo. Aquí dejamos este ejemplo.
            firstFragment.setArguments(getIntent().getExtras());

            // Ejecutamos la transacción para cargar en el layout-large el fragmento
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onArticleSelected(int position) {
        // Cargamos el fragmento que muestra el contenido del artículo para reemplazar su texto
        ArticleFragment articleFrag = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            // Si el fragmento artículo existe, reemplazamos el texto
            articleFrag.updateArticleView(position);

        } else {
            // Si el fragmento no está disponible hemos de ejecutar la transacción de reemplazar
            // la vista.

            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();
        }
    }
}