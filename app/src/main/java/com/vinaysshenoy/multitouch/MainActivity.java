package com.vinaysshenoy.multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vinaysshenoy.multitouch.widget.playground.PlaygroundView;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Circle;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Square;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Triangle;

public class MainActivity extends AppCompatActivity {

    private PlaygroundView playgroundView;

    private View controlPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playgroundView = (PlaygroundView) findViewById(R.id.playground);
        controlPanel = findViewById(R.id.controlPanel);
        controlPanel.findViewById(R.id.btn_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Circle(64F));
            }
        });
        controlPanel.findViewById(R.id.btn_square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Square(48F));
            }
        });
        controlPanel.findViewById(R.id.btn_triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Triangle(72F));
            }
        });
    }
}
