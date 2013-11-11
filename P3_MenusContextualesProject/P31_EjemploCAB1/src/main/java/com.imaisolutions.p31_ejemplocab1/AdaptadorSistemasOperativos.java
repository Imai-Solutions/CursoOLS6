package com.imaisolutions.p31_ejemplocab1;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus_000 on 11/11/13.
 */
public class AdaptadorSistemasOperativos extends ArrayAdapter<String> {

        Activity context;
        private ActionMode mModo;

        ArrayList<String> mListaSistemasOperativos;

        private SparseBooleanArray mIdsElementosSeleccionados;

        public AdaptadorSistemasOperativos(Activity context, int resId, ArrayList<String> valores) {
            super(context, resId, valores);
            mIdsElementosSeleccionados = new SparseBooleanArray();
            this.context = context;
            this.mListaSistemasOperativos=valores;
        }

        private class ViewHolder {
            TextView lblTxt;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitem, null);
                holder = new ViewHolder();
                holder.lblTxt = (TextView) convertView
                        .findViewById(R.id.lblNombreSistemaOperativo);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String valor= getItem(position);
            holder.lblTxt.setText(valor);
            convertView
                    .setBackgroundColor(mIdsElementosSeleccionados.get(position) ? 0x9934B5E4
                            : Color.TRANSPARENT);

            return convertView;
        }

        @Override
        public void add(String so) {
            mListaSistemasOperativos.add(so);
            notifyDataSetChanged();
            Toast.makeText(context, "Añadido:" + so.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void remove(String value) {
            mListaSistemasOperativos.remove(value);
            this.notifyDataSetChanged();
        }

        public List<String> getSOs() {
            return mListaSistemasOperativos;
        }

        public void anadirSeleccion(int position) {
            seleccionarVista(position, !mIdsElementosSeleccionados.get(position));
        }

        public void quitarSeleccion() {
            mIdsElementosSeleccionados = new SparseBooleanArray();
            notifyDataSetChanged();
        }

        public void seleccionarVista(int position, boolean value) {
            if (value)
                mIdsElementosSeleccionados.put(position, value);
            else
                mIdsElementosSeleccionados.delete(position);

            notifyDataSetChanged();
        }

        public int getNumeroElementosSeleccionados() {
            return mIdsElementosSeleccionados.size();
        }

        public SparseBooleanArray getSelectedIds() {
            return mIdsElementosSeleccionados;
        }
    }

