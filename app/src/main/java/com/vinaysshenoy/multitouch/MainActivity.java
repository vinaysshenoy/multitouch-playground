package com.vinaysshenoy.multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vinaysshenoy.multitouch.widget.playground.PlaygroundView;

public class MainActivity extends AppCompatActivity {

    private PlaygroundView playgroundView;

    private View controlPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlPanel = findViewById(R.id.controlPanel);
        controlPanel.findViewById(R.id.btn_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        controlPanel.findViewById(R.id.btn_square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        controlPanel.findViewById(R.id.btn_triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
