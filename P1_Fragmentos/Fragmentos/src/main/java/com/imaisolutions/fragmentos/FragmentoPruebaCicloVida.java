package com.imaisolutions.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jesus_000 on 6/11/13.
 */
public class FragmentoPruebaCicloVida extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentopruebaciclovida, container, false);
        return v;
    }


 }
