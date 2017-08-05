package com.vinaysshenoy.multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vinaysshenoy.multitouch.widget.playground.PlaygroundView;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Circle;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Square;
import com.vinaysshenoy.multitouch.widget.playground.shapes.Triangle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private PlaygroundView playgroundView;

    private View controlPanel;

    private int currentShapeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playgroundView = (PlaygroundView) findViewById(R.id.playground);
        controlPanel = findViewById(R.id.controlPanel);
        controlPanel.findViewById(R.id.btn_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Circle(String.format(Locale.US, "circle_%d", ++currentShapeIndex), 64F));
            }
        });
        controlPanel.findViewById(R.id.btn_square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Square(String.format(Locale.US, "square_%d", ++currentShapeIndex), 128F));
            }
        });
        controlPanel.findViewById(R.id.btn_triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playgroundView.addShape(new Triangle(String.format(Locale.US, "triangle_%d", ++currentShapeIndex), 128F));
            }
        });
    }
}
