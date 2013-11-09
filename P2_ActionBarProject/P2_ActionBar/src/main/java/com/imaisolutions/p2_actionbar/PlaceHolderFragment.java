package com.imaisolutions.p2_actionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jesus_000 on 9/11/13.
 */
public class PlaceHolderFragment extends Fragment {
        int _id;
        public PlaceHolderFragment(int id) {
            _id=id;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(_id, container, false);
            return rootView;
        }

        public void setText(String text){
            TextView lbl=(TextView)this.getView().findViewById(R.id.lblres);
            lbl.setText(text);
        }
}
