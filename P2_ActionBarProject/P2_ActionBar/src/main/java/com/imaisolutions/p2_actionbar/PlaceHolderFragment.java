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
        protected int _id;
        final String _KEYID="ID";
        public PlaceHolderFragment(int id) {
            _id=id;
        }

        public PlaceHolderFragment(){
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null)
            _id =savedInstanceState.getInt(_KEYID);
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(_id, container, false);
            return rootView;
        }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(_KEYID,_id);
    }

    public void setText(String text){
            TextView lbl=(TextView)this.getView().findViewById(R.id.lblres);
            lbl.setText(text);
        }
}
