package com.imaisolutions.P17_Handlers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class BarraProgresoFragment extends Fragment {
    private ProgressBar progress;
    private TextView text;
    private Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        progress = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        text = (TextView) rootView.findViewById(R.id.textView1);
        btn=(Button)rootView.findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProgress(view);
            }
        });
        return rootView;

    }

    public void startProgress(View view) {
        // Arrancamos proceso
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    doWork();
                    progress.post(new Runnable() {
                        @Override
                        public void run() {
                            text.setText("Actualizando");
                            progress.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    // Simulamos un proceso costoso.
    private void doWork() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
