package com.imaisolutions.p2_actionbar.actionbar;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.imaisolutions.p2_actionbar.R;

/**
 * Created by jesus_000 on 10/11/13.
 */
 public class CustomActionProvider extends ActionProvider implements  OnCheckedChangeListener {

        private final Context mContext;

        public CustomActionProvider(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public View onCreateActionView() {
            // Inflate the action view to be shown on the action bar.
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.customprovider, null);
            TextView textview = (TextView) view.findViewById(R.id.text);
            textview.setText(getActionText());

            CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            checkbox.setOnCheckedChangeListener(this);

            return view;
        }

        public String getActionText() {
            return "prueba!:";
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.checkbox:
                    Toast.makeText(mContext,
                            isChecked ? "Presionado" : "No presionado",
                            Toast.LENGTH_SHORT).show();
            }

        }

         @Override
        public boolean onPerformDefaultAction() {
            //Cuando está en overflow se ejecuta esta parte.
            Toast.makeText(mContext, "CustomActionProvider", Toast.LENGTH_LONG).show();
            return true;
        }
}

